package com.ssafy.test.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Email;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.service.EmailService;
import com.ssafy.test.model.service.MailHandler;
import com.ssafy.test.model.service.MailTempKey;
import com.ssafy.test.model.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/email")
public class EmailController {

	private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private EmailService eService;

	@Autowired
	private UserInfoService uiService;
	@Autowired
	private JavaMailSender mailSender;

    @ApiOperation(value = "key를 입력받아서 있으면 유저 valid를 true로 바꿔줌 ", response = Email.class)    
	@GetMapping("/validation/{validKey}")
	public ResponseEntity<String> validKey(@PathVariable String validKey) {
//		System.out.println(validKey);
		Email temp = eService.selectByKey(validKey);
		if (temp != null) {
			uiService.updateVal(temp.getUserEmail());
			eService.delete(temp.getUserEmail());
		return new ResponseEntity<String>("이메일이 인증되었습니다", HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("인증 실패 ㅠㅠ", HttpStatus.NO_CONTENT); 
		}
	}
    
    @ApiOperation(value = "인증메일 재발신 기능", response = String.class)
	@GetMapping("/resend/{id}")
	public ResponseEntity<String> ResendValidationMail(@PathVariable String id) throws MessagingException, UnsupportedEncodingException {
    	boolean find = false;
    	
    	List<Email> list = eService.selectAll();
    	System.out.println(list.size());
    	for(int i=0;i<list.size();i++) {
    		if(list.get(i).getUserEmail().equals(id)) {
    			eService.delete(id);
    			find = true;
    			System.out.println("찾앗당");
    			break;
    		}
    	}
    	if(!find) {
    	//	System.out.println("여기왜걸려");
    		return new ResponseEntity<String>("회원가입이 필요한 회원임", HttpStatus.NO_CONTENT);
    	}
//    	System.out.println(id);
//    	int cnt = eService.delete(id);
//    	System.out.println(cnt);
//    	if(cnt == 0) {
//    		return new ResponseEntity<String>("회원가입이 필요한 회원임", HttpStatus.NO_CONTENT);
//    	}
    	//이메일 인증
		String key = new MailTempKey().getKey(15, false);
		Email e = new Email(id,key) ;
        eService.insert(e);
        MailHandler sendMail = new MailHandler(mailSender);
        sendMail.setSubject("[이메일 인증]");
        sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
                .append("<a href='http://i3a208.p.ssafy.io:8081/api/email/validation/")              
                .append(key)
                .append("' target='_blank'>이메일 인증하기</a>")
                .toString());
        sendMail.setFrom("ADIM@REMEMBER.COM", "RE:MEMBER");
        sendMail.setTo(id);
        sendMail.send();
		
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
    
    }


}