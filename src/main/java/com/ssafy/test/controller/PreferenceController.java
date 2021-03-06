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

import com.ssafy.test.model.dto.Preference;
import com.ssafy.test.model.service.PreferenceService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/preference")
public class PreferenceController {

	private static final Logger logger = LoggerFactory.getLogger(PreferenceController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private PreferenceService pService;

    @ApiOperation(value = "모든 선호 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Preference>> retrieveBoard() throws Exception {
		logger.debug("retrieveBoard - 호출");
		return new ResponseEntity<List<Preference>>(pService.selectAll(), HttpStatus.OK);
	}

    @ApiOperation(value = "번호에 해당하는 선호 정보를 반환한다.", response = Preference.class)    
	@GetMapping("{pno}")
	public ResponseEntity<Preference> detailBoard(@PathVariable String pno) {
		logger.debug("pno : "+ pno);
		int pnum = Integer.parseInt(pno);
		return new ResponseEntity<Preference>(pService.select(pnum), HttpStatus.OK);
	}

    @ApiOperation(value = "새로운 선호 정보를 입력한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Preference p) {
		//logger.debug("writeQnA - 호출");
		if (pService.insert(p) ==1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "번호에 해당하는 선호 정보를 삭제한다. ", response = String.class)
	@DeleteMapping("{pno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int pno) {
		logger.debug("deleteBoard - 호출");
		if (pService.delete(pno) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}