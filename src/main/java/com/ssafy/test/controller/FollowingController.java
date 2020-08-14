package com.ssafy.test.controller;

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
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Following;
import com.ssafy.test.model.service.FollowingService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/following")
public class FollowingController {

	private static final Logger logger = LoggerFactory.getLogger(FollowingController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private FollowingService fService;

    @ApiOperation(value = "모든 following의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Following>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Following>>(fService.selectAll(), HttpStatus.OK);
	}

//    @ApiOperation(value = "해당하는 following 정보를 반환한다.", response = Following.class)    
//	@GetMapping("{id}")
//	public ResponseEntity<Following> detailBoard(@PathVariable Following f) {
//		logger.debug("detailBoard - 호출");
//		return new ResponseEntity<Following>(fService.select(f), HttpStatus.OK);
//	}
    
    @ApiOperation(value = "id를 입력받고 id가 팔로우하는 계정을 반환함", response = Following.class)    
	@GetMapping("{id}")
	public ResponseEntity<List<Following>> getFollowings(@PathVariable String id) {
		logger.debug("getFollowings - 호출");
		return new ResponseEntity<List<Following>>(fService.selectById(id), HttpStatus.OK);
	}
    
    @ApiOperation(value = "해당하는 following 정보를 반환한다.", response = Following.class)    
	@GetMapping("/target/{target}")
	public ResponseEntity<List<Following>> getFollowers(@PathVariable String target) {
		logger.debug("getFollowers - 호출");
		return new ResponseEntity<List<Following>>(fService.selectByTarget(target), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 following 정보를 입력한다. ", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Following q) {
		//logger.debug("writeQnA - 호출");
		if (fService.insert(q) ==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

//    @ApiOperation(value = "해당하는 following 정보를 수정한다.", response = String.class)
//	@PutMapping("{no}")
//	public ResponseEntity<String> updateBoard(@RequestBody Following q) {
//		logger.debug("updateQnA - 호출");
//		logger.debug("" + q);
//		
//		if (fService.update(q) != 0) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}

    @ApiOperation(value = "following 해당하는 게시글의 정보를 삭제한다.", response = String.class)
	@DeleteMapping("/delete/{uid}/{target}")
	public ResponseEntity<String> deleteBoard(@PathVariable String uid, @PathVariable String target) {
		Following f = new Following();
		f.setUid(uid);
		f.setTarget(target);
    	logger.debug("deleteBoard - 호출");
		if (fService.delete(f) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}