package com.ssafy.test.controller;

import java.util.ArrayList;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Message;
import com.ssafy.test.model.dto.User;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.service.MessageService;
import com.ssafy.test.model.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/message")
public class MessageController {

	private static final Logger logger = LoggerFactory.getLogger(MessageController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private MessageService mService;
	
	@Autowired
	private UserInfoService uService;

//    @ApiOperation(value = "모든 메시지 정보를 반환한다.", response = List.class)
//	@GetMapping
//	public ResponseEntity<List<Message>> retrieveBoard() throws Exception {
//		logger.debug("retrieveBoard - 호출");
//		return new ResponseEntity<List<Message>>(mService.selectAll(), HttpStatus.OK);
//	}

//    @ApiOperation(value = "메시지번호에 해당하는 메시지 정보를 반환한다.", response = Message.class)    
//	@GetMapping("{mnum}")
//	public ResponseEntity<Message> detailBoard(@PathVariable int mnum) {
//		logger.debug("detailBoard - 호출");
//		return new ResponseEntity<Message>(mService.select(mnum), HttpStatus.OK);
//	}
	 @ApiOperation(value = "두개의 유저 아이디를 받아서 둘이 대화한 메시지들을 가져온다", response = Message.class)    
	@RequestMapping(value = "/message/{id}/{other}", method = RequestMethod.GET)
	public ResponseEntity<List<Message>> getContacts(@PathVariable("id") String id, @PathVariable("other") String other) throws Exception {
	    List<Message> msg = mService.getContacts(id,other);
	    if (msg.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	    return new ResponseEntity<List<Message>>(msg, HttpStatus.OK);
	}

    
    @ApiOperation(value = "받는사람이 id에 해당하는 메시지 정보를 반환한다.", response = Message.class)    
    @GetMapping("{id}")
    public ResponseEntity<List<Message>> GetMessageById(@PathVariable String id) {
    	List<Message> list = mService.selectById(id);
    //	System.out.println();
    	List<String> arr = new ArrayList<>();
    	List<Message> msg = new ArrayList<>();
    //	System.out.println(list.size());
    	for(int i=0;i<list.size();i++) {
    		Message tmp = list.get(i);
    	//	System.out.println(tmp);
    		if(tmp.getFromUser().equals(id)) {
    			if(arr.contains(tmp.getToUser())) {
    				continue;
    			}else {
    				msg.add(tmp);
    				arr.add(tmp.getToUser());
    			}
    		}else if(tmp.getToUser().equals(id)) {
    			if(arr.contains(tmp.getFromUser())) {
    				continue;
    			}else {
    				msg.add(tmp);
    				arr.add(tmp.getFromUser());
    			}
    		}
    	}
    	//System.out.println("---msg---");
    //	System.out.println(msg.size());
    return new ResponseEntity<List<Message>>(msg, HttpStatus.OK);
    	
    }


    @ApiOperation(value = "새로운 메시지 정보를 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> SendMessage(@RequestBody Message mnum) {
		//logger.debug("writeQnA - 호출");
		if (mService.insert(mnum) ==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}



    @ApiOperation(value = "메시지번호에 해당하는메시지의 정보를 삭제한다. ", response = String.class)
	@DeleteMapping("{mnum}")
	public ResponseEntity<String> deleteBoard(@PathVariable int mnum) {
		logger.debug("deleteBoard - 호출");
		if (mService.delete(mnum) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    @ApiOperation(value = "message 정보를 수정한다", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateUser(@RequestBody Message m) {
		
		if (mService.update(m) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
}