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

import com.ssafy.test.model.dto.TagList;
import com.ssafy.test.model.dto.TagResult;
import com.ssafy.test.model.dto.Usertag;
import com.ssafy.test.model.service.UsertagService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/usertag")
public class UsertagController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private UsertagService Service;

	@ApiOperation(value = "특정 유저의 모든 tagvalue 정보를 반환한다.", response = List.class)
	@GetMapping("selet/uid={uid}")
	public ResponseEntity<List<Usertag>> retrieveBoard(@PathVariable String uid) throws Exception {
		return new ResponseEntity<List<Usertag>>(Service.selectAll(uid), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 유저가 가진 특정 tagvalue의 정보를 반환한다.", response = Usertag.class)
	@GetMapping("no/tagvalue={tagvalue}&uid={uid}")
	public ResponseEntity<Usertag> detailnoBoard(@PathVariable String tagvalue, @PathVariable String uid) {
		Usertag v = new Usertag();
		v.setTagvalue(tagvalue);
		v.setUid(uid);
		return new ResponseEntity<Usertag>(Service.select(v), HttpStatus.OK);
	}


	@ApiOperation(value = "새로운 유저태그 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Usertag v) {
		if (Service.insert(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	@ApiOperation(value = "특정 유저의 특정 tagvalue를 삭제합니다.. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("delete/tagvalue={tagvalue}&uid={uid}")
	public ResponseEntity<String> deleteBoard(@PathVariable String tagvalue,@PathVariable String uid) {
		Usertag v = new Usertag();
		v.setTagvalue(tagvalue);
		v.setUid(uid);
		Service.delete(v);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);

//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
//	이부분해야합니다 ㅎㅎㅎ
	/*
	@ApiOperation(value = "완전히 일치하는 유저의 id를 반환한다.", response = Usertag.class)
	@GetMapping("selectSame/{tag}")
	public ResponseEntity<List<String>> selectSame(@PathVariable String tag) {
		TagList v = new TagList();
		String a = tag.split(",");
		for(int i = 0; i < tag.length; i++) {
			v.setTag + i(tag1);
		}
		return new ResponseEntity<List<String>>(Service.selectSame(v), HttpStatus.OK);
	}
	
	@ApiOperation(value = "특정 유저가 가진 특정 tagvalue의 정보를 반환한다.", response = Usertag.class)
	@GetMapping("selectSimilar")
	public ResponseEntity<List<TagResult>> selectSimilar(@RequestBody TagList v) {
		return new ResponseEntity<List<TagResult>>(Service.selectSimilar(v), HttpStatus.OK);
	}

*/

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