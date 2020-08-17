package com.ssafy.test.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.MessagingException;
import javax.net.ssl.HttpsURLConnection;
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
import com.ssafy.test.model.dto.Following;
import com.ssafy.test.model.dto.Inter;
import com.ssafy.test.model.dto.PidPjt;
import com.ssafy.test.model.dto.Pinterest;
import com.ssafy.test.model.dto.Pmember;
import com.ssafy.test.model.dto.Pools;
import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.Projectcnt;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.Two;
import com.ssafy.test.model.dto.User;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.UserSimple;
import com.ssafy.test.model.dto.WaitMember;
import com.ssafy.test.model.service.BoardService;
import com.ssafy.test.model.service.EmailService;
import com.ssafy.test.model.service.JwtService;
import com.ssafy.test.model.service.MailHandler;
import com.ssafy.test.model.service.MailTempKey;
import com.ssafy.test.model.service.PinterestService;
import com.ssafy.test.model.service.PmemberService;
import com.ssafy.test.model.service.ProjectService;
import com.ssafy.test.model.service.UserInfoService;
import com.ssafy.test.model.service.WaitMemberService;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
//jsoup part
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
// end

import io.swagger.annotations.ApiOperation;


//http://localhost:9999/vue/swagger-ui.html
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RestController
@RequestMapping("/api/waitMember")
public class WaitMemberController {
	
   private static final Logger logger = LoggerFactory.getLogger(WaitMemberController.class);
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
   private WaitMemberService wmService;
   
   @Autowired
   private PmemberService pmService;

   @ApiOperation(value = "대기 인원을 등록한다", response = String.class)
   @PostMapping
   public ResponseEntity<String> insertUser(@RequestBody WaitMember wm)
         throws MessagingException, UnsupportedEncodingException {
      logger.debug("insertUser - 호출");
     int num =0;
      if(wm.getType().equals("Apply")) {
    	 //멤버가 지원한거라면 -> 대기열에 추가해준다 
    	  num = wmService.addWaitMember(wm);
    	 //*****구현부분
    	  //프로젝트멤버에게 쪽지보내줌 새로운 멤버가 지원을 했다 
    	  
    	  
      }else if( wm.getType().equals("Invite")) {
    	//멤버가 초대한거라면 
    	  //초대받은 멤버에게 쪽지를 보내준다 어디에서 초대를 받았다고 
    	num=  wmService.addWaitMember(wm);
      }
     
      
//      if (test == 1) {
//            // 이메일 인증
//            String key = new MailTempKey().getKey(50, false);
//            Email e = new Email(q.getId(), key);
//            eService.insert(e);
//            MailHandler sendMail = new MailHandler(mailSender);
//            sendMail.setSubject("[이메일 인증]");
//            sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
//                  .append("<a href='https://localhost:8080/api/email/validKey=").append(key)
//                  .append("' target='_blenk'>이메일 인증 확인</a>").toString());
//            sendMail.setFrom("test@gmail.com", "admin");
//            sendMail.setTo(q.getId());
//            sendMail.send();
//
//           
//         }
      if(num ==1)  return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
      return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT); // 에러를 바꿔줘야할것같아여 ㅠㅠ
}

   @ApiOperation(value = "해당하는 id의 지원 정보를 반환한다.", response = Following.class)    
	@GetMapping("/searchById/{id}")
	public ResponseEntity<List<WaitMember>> searchById(@PathVariable String id) {
	
		return new ResponseEntity<List<WaitMember>>(wmService.selectbyuserId(id), HttpStatus.OK);
	}
   

   @ApiOperation(value = "해당하는 pid의 지원 유저 정보를 반환한다.", response = Following.class)    
	@GetMapping("/searchByPid/{id}")
	public ResponseEntity<List<WaitMember>> searchByPid(@PathVariable int pid) {
	
		return new ResponseEntity<List<WaitMember>>(wmService.selectbyPid(pid), HttpStatus.OK);
	}

   @ApiOperation(value = "대기열에서 삭제하는 기능", response = String.class)
   @DeleteMapping("/delete/{id}/{pid}")
   public ResponseEntity<String> deleteUser(@PathVariable int pid, @PathVariable String id) {
      logger.debug("deleteUser - 호출");
      
      //type이 apply 인것만 지우기! 
      //##당사자에게는 Apply가 삭제되었다고 알림을 줘야한다 
      if (wmService.deleteWM(pid, id) == 1) {
         return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
      }
      return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
   }




}