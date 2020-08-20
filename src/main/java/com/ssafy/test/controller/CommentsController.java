package com.ssafy.test.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.ssafy.test.model.dto.Comments;
import com.ssafy.test.model.dto.Message;
import com.ssafy.test.model.service.BoardService;
import com.ssafy.test.model.service.CommentsService;
import com.ssafy.test.model.service.MessageService;
import com.ssafy.test.model.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/comments")
public class CommentsController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private CommentsService Service;

	@Autowired
	private MessageService mService;
	@Autowired
	private BoardService bService;
	
	@Autowired
	private UserInfoService uiService;

	@ApiOperation(value = "모든 커멘트의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Comments>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Comments>>(Service.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "커멘트 번호에 해당하는 커멘트의 정보를 반환한다.", response = Comments.class)
	@GetMapping("no/{cno}")
	public ResponseEntity<Comments> detailBoard(@PathVariable int cno) {
		return new ResponseEntity<Comments>(Service.select(cno), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 게시글에 채택된 글을 반환한다.", response = Comments.class)
	@GetMapping("selected/{bno}")
	public ResponseEntity<Comments> selectedComments(@PathVariable int bno) {
		return new ResponseEntity<Comments>(Service.selectedComments(bno), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 사용자가 작성한 모든 comments를 반환한다.", response = List.class)
	@GetMapping("search/{bwriter}")
	public ResponseEntity<List<Comments>> searchById(@PathVariable String bwriter) throws Exception {
		return new ResponseEntity<List<Comments>>(Service.searchById(bwriter), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 사용자가 작성한 comments 중, 채택된 comments를 반환한다.", response = List.class)
	@GetMapping("searchselectedcomments/{bwriter}")
	public ResponseEntity<List<Comments>> searchSelectedComments(@PathVariable String bwriter) throws Exception {
		return new ResponseEntity<List<Comments>>(Service.searchSelectedComments(bwriter), HttpStatus.OK);
	}
	
	@ApiOperation(value = "새로운 커멘트의 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Comments v) {
		if (Service.insert(v) != 0) {
			
			if(v.getCwriter() != bService.select(v.getBno()).getBwriter()) {
			Message msg = new Message();
			msg.setFromUser("admin");
			msg.setToUser(bService.select(v.getBno()).getBwriter());
			
			msg.setContent("[SYSTEM:댓글] :  (" + bService.select(v.getBno()).getBtitle() + ")글에 " + uiService.select(v.getCwriter()).getNickname() +"님이 댓글을 달았습니다.");
			msg.setRead(false);
			msg.setTime(new Date());
			//mService.insert(msg)
			
			mService.insert(msg);
			}
			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 커멘트의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("change/{cno}")
	public ResponseEntity<String> updateBoard(@RequestBody Comments v) {
		
		if (Service.update(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	@ApiOperation(value = "글번호에 해당하는 커멘트의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("select/{cno}")
	public ResponseEntity<String> pick(@PathVariable int cno) {
		if (Service.pick(cno) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 커멘트의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("delete/{cno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int cno) {

		if (Service.delete(cno) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	private ResponseEntity<Map<String, Object>> handleSuccess(Object data) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", true);
		resultMap.put("data", data);
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.OK);
	}

	private ResponseEntity<Map<String, Object>> handleException(Exception e) {
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("status", false);
		resultMap.put("data", e.getMessage());
		return new ResponseEntity<Map<String, Object>>(resultMap, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
