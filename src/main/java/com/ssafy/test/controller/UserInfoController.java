package com.ssafy.test.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @ApiOperation(value = "모든 유저의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<UserInfo>> getAllUsers() throws Exception {
		logger.debug("getAllUsers - 호출");
		return new ResponseEntity<List<UserInfo>>(uiService.selectAll(), HttpStatus.OK);
	}

    @ApiOperation(value = "로그인 기능으로 할 것/ 일단 아이디에 맞는 거 반환 를 반환한다.", response = UserInfo.class)    
	@GetMapping("{id}")
	public ResponseEntity<UserInfo> login(@PathVariable String id) {
		logger.debug("login - 호출");
		return new ResponseEntity<UserInfo>(uiService.select(id), HttpStatus.OK);
	}

    @ApiOperation(value = "회원가입기능, 새로운 유저를 추가한다. ", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertUser(@RequestBody UserInfo q) {
		logger.debug("insertUser - 호출");
		boolean emailTest = checkRex(q.getId(), "id");
		boolean pwTest = checkRex(q.getPw(), "password");
		boolean nameTest = checkRex(q.getName(), "name");
		boolean nicknameTest = checkRex(q.getNickname(), "nickname");
		boolean phoneTest = checkRex(q.getPhone(), "phone");
		boolean resTest = false;
		String rep = q.getResponsibility();
		if(rep == "개발" || rep == "기획" || rep=="디자인") resTest = true;
		if (emailTest && pwTest && nameTest && nicknameTest && phoneTest && resTest) {
			uiService.insert(q);
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT); //에러를 바꿔줘야할것같아여 ㅠㅠ
	}

    @ApiOperation(value = "유저 정보를 수정한다", response = String.class)
	@PutMapping("{no}")
	public ResponseEntity<String> updateUser(@RequestBody UserInfo q) {
		logger.debug("updateUser - 호출");
		logger.debug("" + q);
		
		if (uiService.update(q) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

    @ApiOperation(value = "회원탈퇴, 회원 삭제기능", response = String.class)
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable String id) {
		logger.debug("deleteUser - 호출");
		if (uiService.delete(id) == 1) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
    
    public boolean checkRex(String input, String option) {
    	Pattern p=Pattern.compile(" ");
    	if(option == "id") {
    		p = Pattern.compile("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");
    	}else if( option =="name") {
    		p = Pattern.compile("^[가-힣]*$");
    	}else if(option =="phone") {
    		p = Pattern.compile("^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$");
    	}else if(option == "password") {
    		p = Pattern.compile("^[a-z0-9_]{8,20}$");
    	}else if(option == "nickname") {
    		p = Pattern.compile("^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\\*]+$");
    	}
    	
    	Matcher m = p.matcher(input);
    	if(m.find()) {
    		return true;
    	}
    	
    	System.out.println( option + "틀렸음 ㅡㅡ ");
    	
    	return false;
    }
    
    
}