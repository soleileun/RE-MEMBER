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

import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {

	private static final Logger logger = LoggerFactory.getLogger(UserInfoController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UserInfoService uiService;

//    @ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
//	@GetMapping
//	public ResponseEntity<List<UserInfo>> retrieveBoard() throws Exception {
//		logger.debug("retrieveBoard - 호출");
//		return new ResponseEntity<List<UserInfo>>(uiService.selectAll(), HttpStatus.OK);
//	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = UserInfo.class)    
	@GetMapping("{id}")
	public ResponseEntity<UserInfo> detailBoard(@PathVariable String id) {
		logger.debug("detailBoard - 호출");
		return new ResponseEntity<UserInfo>(uiService.select(id), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 게시글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody UserInfo q) {
		//logger.debug("writeQnA - 호출");
		if (uiService.insert(q) ==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateBoard(@RequestBody UserInfo q) {
		logger.debug("updateQnA - 호출");
		logger.debug("" + q);
		
		if (uiService.update(q) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteBoard(@PathVariable String id) {
		logger.debug("deleteBoard - 호출");
		if (uiService.delete(id) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}