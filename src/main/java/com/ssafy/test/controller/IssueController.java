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

import com.ssafy.test.model.dto.Board;
import com.ssafy.test.model.dto.Issue;
import com.ssafy.test.model.service.IssueService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/issue")
public class IssueController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private IssueService Service;

	@ApiOperation(value = "특정 프로젝트의 모든 이슈 정보를 반환한다", response = List.class)
	@GetMapping("pid/{pid}")
	public ResponseEntity<List<Issue>> retrieveIssue(@PathVariable int pid) throws Exception {
		return new ResponseEntity<List<Issue>>(Service.selectAll(pid), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 프로젝트의 특정 상태의 이슈 정보를 반환한다", response = List.class)
	@GetMapping("search/pid={pid}&issuestate={issuestate}")
	public ResponseEntity<List<Issue>> selectStateAll(@PathVariable int pid,@PathVariable String issuestate) throws Exception {
		Issue v = new Issue();
		v.setPid(pid);
		v.setIssuestate(issuestate);	
		return new ResponseEntity<List<Issue>>(Service.selectStateAll(v), HttpStatus.OK);
	}

	@ApiOperation(value = "이슈번호에 해당하는 이슈의 정보를 반환한다.", response = Issue.class)
	@GetMapping("no/{issueid}")
	public ResponseEntity<Issue> detailIssue(@PathVariable int issueid) {
		return new ResponseEntity<Issue>(Service.select(issueid), HttpStatus.OK);
	}

	
	@ApiOperation(value = "새로운 이슈 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeIssue(@RequestBody Issue v) {
		if (Service.insert(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "특정 이슈를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("delete/{issueid}")
	public ResponseEntity<String> deleteBoard(@PathVariable int issueid) {

		if (Service.delete(issueid) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "특정 프로젝트의 모든 이슈를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("deletebyproject/pid={pid}")
	public ResponseEntity<String> deleteByProject(@PathVariable int pid) {

		if (Service.deleteByProject(pid) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "이슈번호에 해당하는 이슈의 상태정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("changestate/issueid={issueid}&issuestate={issuestate}")
	public ResponseEntity<String> updateState(@PathVariable int issueid, @PathVariable String issuestate) {

		Issue v = new Issue();
		v.setIssueid(issueid);
		v.setIssuestate(issuestate);
		v.setChangeDay(new Date());
		
		if (Service.updateState(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "이슈번호에 해당하는 이슈의 상태정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("updateByState")
	public ResponseEntity<String> updateByState(@RequestBody Issue v) {
			
		if (Service.updateByState(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "이슈번호에 해당하는 이슈의 담당자정 보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("changeresponse/issueid={issueid}&response={response}")
	public ResponseEntity<String> updateResponse(@PathVariable int issueid, @PathVariable String response) {

		Issue v = new Issue();
		v.setIssueid(issueid);
		v.setResponse(response);
		v.setChangeDay(new Date());
		
		if (Service.updateResponse(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "이슈 수정. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("update")
	public ResponseEntity<String> update(@RequestBody Issue v) {
		if (Service.update(v) != 0) {
			System.out.println(v.toString());
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}


	@ApiOperation(value = "이슈번호에 해당하는 이슈의 중요도를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("changestate/issueid={issueid}&priority={priority}")
	public ResponseEntity<String> updatePriority(@PathVariable int issueid, @PathVariable String priority) {

		Issue v = new Issue();
		v.setIssueid(issueid);
		v.setPriority(priority);
		v.setChangeDay(new Date());
		
		if (Service.updatePriority(v) != 0) {
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
