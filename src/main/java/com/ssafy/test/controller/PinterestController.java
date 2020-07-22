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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Pinterest;
import com.ssafy.test.model.service.PinterestService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/pinterest")
public class PinterestController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PinterestService Service;

	@ApiOperation(value = "모든 프로젝트 관심사의 정보를 반환한다.", response = List.class)
	@GetMapping()
	public ResponseEntity<List<Pinterest>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Pinterest>>(Service.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "pid를 가진 프로젝트에 해당하는 모든 관심사의 정보를 반환한다.", response = List.class)
	@GetMapping("interests/{pid}")
	public ResponseEntity<List<Pinterest>> getInterest(@PathVariable int pid) {
		return new ResponseEntity<List<Pinterest>>(Service.select(pid), HttpStatus.OK);
	}
	

	@ApiOperation(value = "특정 pid와 특정 관심사를 가진 정보를 조회한다.", response = Pinterest.class)
	@GetMapping("search/pid={pid},interest={interest}")
	public ResponseEntity<Pinterest> detailBoard(@PathVariable int pid, @PathVariable String interest) {
		Pinterest v;
		v = new Pinterest(pid,interest);
		//v.setPid(pid);
		//v.setInterest(interest);
		return new ResponseEntity<Pinterest>(Service.pick(v), HttpStatus.OK);
	}
	

	@ApiOperation(value = "새로운 관심사의 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Pinterest v) {
		if (Service.insert(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


	@ApiOperation(value = "특정 관심사와 특정 pid를 가진 관심사의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> deleteBoard(@RequestBody Pinterest v) {

		if (Service.delete(v) != 0) {
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
