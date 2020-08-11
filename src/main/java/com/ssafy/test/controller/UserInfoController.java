package com.ssafy.test.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
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
import com.ssafy.test.model.dto.Email;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.User;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.UserSimple;
import com.ssafy.test.model.service.BoardService;
import com.ssafy.test.model.service.EmailService;
import com.ssafy.test.model.service.JwtService;
import com.ssafy.test.model.service.MailHandler;
import com.ssafy.test.model.service.MailTempKey;
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
	
	@Autowired
	private JwtService jwtService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private EmailService eService;
	
	@Autowired
	private BoardService bService;
	
	@ApiOperation(value = "모든 검색어 통합 검색하는 것.", response = UserInfo.class)
	@GetMapping("searchAll/tag={tag}&addr={addr}&keyword={keyword}")
	public ResponseEntity<List<UserInfo>> search(@PathVariable String tag, @PathVariable String addr,
			@PathVariable String keyword) {
		SearchParameter sp = new SearchParameter();
		String b[] = addr.split(",");
		if (tag.equals("null")) {
			// tag 기술 스택이 없는 경우
			sp.setKeyword(keyword);
			sp.setDong(b[0]);
			sp.setGugun(b[1]);
			sp.setSido(b[2]);
			sp.setCnt(0);

			return new ResponseEntity<List<UserInfo>>(uiService.searchAll(sp), HttpStatus.OK);

		} else {
			// 기술 스택 태그가 있는 경우
			String a[] = tag.split(",");
			if (a.length > 0)
				sp.setTag1(a[0]);
			if (a.length > 1)
				sp.setTag2(a[1]);
			if (a.length > 2)
				sp.setTag3(a[2]);
			if (a.length > 3)
				sp.setTag4(a[3]);
			if (a.length > 4)
				sp.setTag5(a[4]);
			sp.setCnt(a.length);
			sp.setSido(b[0]);
			sp.setGugun(b[1]);
			sp.setDong(b[2]);
			sp.setKeyword(keyword);
			// 어차피 널이 들어감.
			return new ResponseEntity<List<UserInfo>>(uiService.searchAll(sp), HttpStatus.OK);

		}
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<Map<String,Object>> signin(@RequestBody User user , HttpServletResponse response){
		//System.out.println("test1 : " + user.getId() + " : " + user.getPw());
		
		Map<String, Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			//System.out.println(user.getId() + " : " + user.getPw());
			UserInfo loginUser = uiService.signin(user.getId(), user.getPw());
	
			//로그인했다면 토큰생성
			String token = jwtService.create(loginUser);
			//토큰 정보는 request 헤더로 보내고 나머지는 map에 담음
			response.setHeader("jwt-auth-token", token);
			resultMap.put("status", true);
			resultMap.put("data", loginUser);
			status = HttpStatus.ACCEPTED;
		}catch (RuntimeException e) {
			logger.error("로그인 안됨",e);
			resultMap.put("message", e.getMessage());
			status = HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return new ResponseEntity<Map<String,Object>>(resultMap,status);
	}
	
	@GetMapping("/getCurrList")
	public ResponseEntity<List<UserInfo>> getCurrList(){
		List<UserInfo> list = uiService.getCurrList();		
		return new ResponseEntity<List<UserInfo>>(list, HttpStatus.OK);
	}
	
	@PostMapping("/info")
	public ResponseEntity<Map<String,Object>> getInfo(HttpServletRequest request, @RequestBody UserInfo userinfo){
		Map<String,Object> resultMap = new HashMap<>();
		HttpStatus status = null;
		try {
			String info = uiService.getServerInfo();
			resultMap.putAll(jwtService.get(request.getHeader("jwt-auth-token")));
			resultMap.put("status", true);
			resultMap.put("info", info);
			resultMap.put("request_body",userinfo);
			
		}catch (RuntimeException e) {
			logger.error("어흐 정보조회 실패 ",e);
			resultMap.put("message",e.getMessage());
			status=HttpStatus.INTERNAL_SERVER_ERROR;
			
		}
		return new ResponseEntity<Map<String,Object>>(resultMap,status);
		
	}

    @ApiOperation(value = "모든 유저의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<UserSimple>> getAllUsers() throws Exception {
		logger.debug("getAllUsers - 호출");
		List<UserInfo> all = uiService.selectAll();
		List<UserSimple> users = new ArrayList<>();
		for(int i=0;i<all.size();i++) {
			UserInfo tmp = all.get(i);
			users.add(new UserSimple(tmp.getId(), tmp.getNickname(), tmp.getGit(),tmp.getLastDate(), tmp.isState(), tmp.getResponsibility(), tmp.isLeaveUser()));
		}
		return new ResponseEntity<List<UserSimple>>(users, HttpStatus.OK);
	}
    @ApiOperation(value = "모든 유저의 정보를 반환한다.", response = List.class)
	@GetMapping("/search/{id}")
	public ResponseEntity<UserInfo> getSelectedUser(@PathVariable String id) throws Exception {
		logger.debug("getSelectedUser - 호출");
		
		return new ResponseEntity<UserInfo>(uiService.select(id), HttpStatus.OK);

	}

    @ApiOperation(value = "해당 지역에 거주하는 유저의 정보를 반환한다..", response = List.class)
	@GetMapping("/addr/sido={sido}&gugun={gugun}&dong={dong}")
	public ResponseEntity<List<UserInfo>> selectByAddr(@PathVariable String sido,@PathVariable String gugun,@PathVariable String dong) throws Exception {
		logger.debug("selectByAddr - 호출");
		Addr v = new Addr();
		v.setDong(dong);
		v.setGugun(gugun);
		v.setSido(sido);
		return new ResponseEntity<List<UserInfo>>(uiService.selectByAddr(v), HttpStatus.OK);
	}


    @ApiOperation(value = "회원가입기능, 새로운 유저를 추가한다. 인증메일도 보내진다 ", response = String.class)
	@PostMapping
	public ResponseEntity<String> insertUser(@RequestBody UserInfo q) throws MessagingException, UnsupportedEncodingException {
		logger.debug("insertUser - 호출");
		System.out.println(q.toString());
		boolean emailTest = checkRex(q.getId(), "id");
		boolean pwTest = checkRex(q.getPw(), "password");
		boolean nameTest = checkRex(q.getName(), "name");
		boolean nicknameTest = checkRex(q.getNickname(), "nickname");
		boolean phoneTest = checkRex(q.getPhone(), "phone");
		boolean resTest = false;
		String rep = q.getResponsibility();
	//	System.out.println(rep);
		if(rep.equals("개발")||rep.equals("디자인")||rep.equals("기획")) {
			resTest = true;
		}
	//	System.out.println(emailTest + " " + pwTest + " " + nicknameTest +" " + phoneTest +" " + resTest  );
		if (emailTest && pwTest && nameTest && nicknameTest && phoneTest && resTest) {
			//System.out.println("들어감");
			int test = uiService.insert(q);
			//System.out.println("들어감 : " + test);
			if(test ==1) {
			//이메일 인증
			String key = new MailTempKey().getKey(50, false);
			Email e = new Email(q.getId(),key) ;
	        eService.insert(e);
	        MailHandler sendMail = new MailHandler(mailSender);
	        sendMail.setSubject("[이메일 인증]");
	        sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
	                .append("<a href='https://localhost:8080/api/email/validKey=")
	                .append(key)
	                .append("' target='_blenk'>이메일 인증 확인</a>")
	                .toString());
	        sendMail.setFrom("test@gmail.com", "admin");
	        sendMail.setTo(q.getId());
	        sendMail.send();
			
			
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
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
		UserInfo user = uiService.select(id);
		user.setLeaveUser(true);
		user.setNickname("Unknown");
		if (uiService.update(user)== 1) {
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
    		p = Pattern.compile("^?([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})$");
    	}else if(option == "password") {
    		p = Pattern.compile("^[0-9a-zA-Z_]{8,20}$");
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
