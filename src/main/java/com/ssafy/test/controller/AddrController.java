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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Addr;
import com.ssafy.test.model.service.AddrService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/addr")
public class AddrController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private AddrService Service;


	@ApiOperation(value = "모든 주소 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Addr>> selectAll() throws Exception {
		return new ResponseEntity<List<Addr>>(Service.selectAll(), HttpStatus.OK);
	}
	

	@ApiOperation(value = "특정 주소에 대한 정보를 반환한다.", response = List.class)
	@GetMapping("sido={sido}&gugun={gugun}&dong={dong}")
	public ResponseEntity<Addr> select(@PathVariable String sido,@PathVariable String gugun,@PathVariable String dong) throws Exception {
		Addr v = new Addr(sido, gugun, dong);
		return new ResponseEntity<Addr>(Service.select(v), HttpStatus.OK);
	}


	@ApiOperation(value = "특정 번호의 주소를 반환한다.", response = List.class)
	@GetMapping("anum={anum}")
	public ResponseEntity<Addr> selectByAnum(@PathVariable int anum) throws Exception {
		return new ResponseEntity<Addr>(Service.selectByAnum(anum), HttpStatus.OK);
	}


	@ApiOperation(value = "시도에 대한 정보를 반환합니다", response = List.class)
	@GetMapping("getSido")
	public ResponseEntity<List<String>> getSido() throws Exception {
		return new ResponseEntity<List<String>>(Service.getSido(), HttpStatus.OK);
	}


	@ApiOperation(value = "특정 시도에 속한 구군을 반환합니다.", response = List.class)
	@GetMapping("getGugun/sido={sido}")
	public ResponseEntity<List<String>> getGugun(@PathVariable String sido) throws Exception {
		Addr v = new Addr();
		v.setSido(sido);
		return new ResponseEntity<List<String>>(Service.getGugun(v), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 시도의 특정 구군에 속한 동을 반환합니다.", response = List.class)
	@GetMapping("getDong/sido={sido}&gugun={gugun}")
	public ResponseEntity<List<String>> getDong(@PathVariable String sido, @PathVariable String gugun) throws Exception {
		Addr v = new Addr();
		v.setSido(sido);
		v.setGugun(gugun);
		return new ResponseEntity<List<String>>(Service.getDong(v), HttpStatus.OK);
	}
	
	
	
	@ApiOperation(value = "insert문입니다..", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Addr v) {
		if (Service.insert(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "delete입니다.", response = String.class)
	@DeleteMapping
	public ResponseEntity<String> deleteBoard(@RequestBody Addr v) {

		if (Service.delete(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	
	@ApiOperation(value = "글 정보를 읽었음으로 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping
	public ResponseEntity<String> updateRead(@RequestBody Addr v) {

		if (Service.update(v) != 0) {
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
