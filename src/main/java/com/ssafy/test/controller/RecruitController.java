package com.ssafy.test.controller;

import java.util.List;

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

import com.ssafy.test.model.dto.Recruit;
import com.ssafy.test.model.service.RecruitService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/recruit")
public class RecruitController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private RecruitService rService;

	@ApiOperation(value = "모든 구인구직 게시판의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Recruit>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Recruit>>(rService.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 구인구직 게시판의 정보를 반환한다.", response = Recruit.class)
	@GetMapping("{rnum}")
	public ResponseEntity<Recruit> detailBoard(@PathVariable int rnum) {
		return new ResponseEntity<Recruit>(rService.select(rnum), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 구인구직 게시판 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Recruit r) {
		if (rService.insert(r) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{rnum}")
	public ResponseEntity<String> updateBoard(@RequestBody Recruit r) {

		if (rService.update(r) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{rnum}")
	public ResponseEntity<String> deleteBoard(@PathVariable int rnum) {
		
		if (rService.delete(rnum) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
