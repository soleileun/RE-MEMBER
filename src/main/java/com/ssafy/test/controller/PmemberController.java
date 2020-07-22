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

import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.Pmember;
import com.ssafy.test.model.service.ProjectService;
import com.ssafy.test.model.service.PmemberService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/project_member")
public class PmemberController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PmemberService pmService;
	
	@Autowired
	private ProjectService pService;

	@ApiOperation(value = "모든 프로젝트 멤버의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Pmember>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Pmember>>(pmService.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "정보에 해당하는 프로젝트 멤버의 정보를 반환한다.", response = Pmember.class)
	@PostMapping("/select")
	public ResponseEntity<Pmember> detailBoard(@RequestBody Pmember pm) {
		return new ResponseEntity<Pmember>(pmService.select(pm), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 프로젝트 멤버정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Pmember pm) {
		if (pmService.insert(pm) != 0) {
			Project p = pService.select(pm.getPid());
			int cnt = p.getPjtMemberCnt();
			p.setPjtMemberCnt(cnt + 1);
			pService.update(p);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트 멤버의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{pid}")
	public ResponseEntity<String> updateBoard(@RequestBody Pmember pm) {

		if (pmService.update(pm) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)

	@DeleteMapping
	public ResponseEntity<String> deleteBoard(@RequestBody Pmember pm) {

		if (pmService.delete(pm) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

}