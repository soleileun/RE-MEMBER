package com.ssafy.test.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Tag;
import com.ssafy.test.model.service.TagService;

import io.swagger.annotations.ApiOperation;


@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/tag")
public class TagController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private TagService Service;
	

	@ApiOperation(value = "모든 tag의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Tag>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Tag>>(Service.selectAll(), HttpStatus.OK);
	}
	

	@ApiOperation(value = "새로운 태그 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody String k) {
		if (Service.insert(k) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("delete/{tagvalue}")
	public ResponseEntity<String> deleteBoard(@PathVariable String tagvalue) {
		
		Service.delete(tagvalue);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
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
