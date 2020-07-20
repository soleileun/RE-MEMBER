package com.ssafy.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Interest;
import com.ssafy.test.model.service.InterestService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/interest")
public class InterestController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private InterestService iService;

	@ApiOperation(value = "모든 관심 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Interest>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Interest>>(iService.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "정보에 해당하는 관심 정보를 반환한다.", response = Interest.class)
	@PostMapping("/select")
	public ResponseEntity<Interest> detailBoard(@RequestBody Interest i) {
		return new ResponseEntity<Interest>(iService.select(i), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 관심정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Interest i) {
		if (iService.insert(i) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

//	@ApiOperation(value = "관심 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
//	@PutMapping("{pid}")
//	public ResponseEntity<String> updateBoard(@RequestBody Interest i) {
//
//		if (iService.update(i) != 0) {
//			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
//		}
//		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
//	}

	@ApiOperation(value = "관심정보의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)

	@DeleteMapping
	public ResponseEntity<String> deleteBoard(@RequestBody Interest i) {

		if (iService.delete(i) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
