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
import com.ssafy.test.model.dto.Inter;
import com.ssafy.test.model.dto.Message;
import com.ssafy.test.model.dto.PidPjt;
import com.ssafy.test.model.dto.Pinterest;
import com.ssafy.test.model.dto.Pmember;
import com.ssafy.test.model.dto.Pools;
import com.ssafy.test.model.dto.Project;
import com.ssafy.test.model.dto.Projectcnt;
import com.ssafy.test.model.dto.SearchParameter;
import com.ssafy.test.model.dto.User;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.UserSimple;
import com.ssafy.test.model.service.BoardService;
import com.ssafy.test.model.service.EmailService;
import com.ssafy.test.model.service.JwtService;
import com.ssafy.test.model.service.MailHandler;
import com.ssafy.test.model.service.MailTempKey;
import com.ssafy.test.model.service.MessageService;
import com.ssafy.test.model.service.PinterestService;
import com.ssafy.test.model.service.PmemberService;
import com.ssafy.test.model.service.ProjectService;
import com.ssafy.test.model.service.UserInfoService;

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

   @Autowired
   private ProjectService pjtService;

   @Autowired
   private PinterestService pinterService;
   @Autowired
   private PmemberService pmservice;
   
   @Autowired
   private MessageService mService;

   @ApiOperation(value = "유저풀에서 사용", response = List.class)
   @GetMapping("pools")
   public ResponseEntity<List<Pools>> getPools() throws Exception {
	   //Two<Integer, Integer> two = new Two<Integer,Integer>();
	   //two.setFirst(paging * cnt);
	   //two.setSecond(cnt);
      List<Pools> v = uiService.getPools();
      for (int i = 0; i < v.size(); i++) {
         List<PidPjt> ptmp = new ArrayList<PidPjt>();
         List<Inter> itmp = new ArrayList<Inter>();
         String a = v.get(i).getProjects();
         String b = v.get(i).getInterests();
         if (a != null) {
            String[] atmp = a.split("-._");
            for (int j = 0; j < atmp.length; j++) {
               //System.out.println("atmp : " + atmp[j]);
               String[] s = atmp[j].split("-./");
               int pid = Integer.parseInt(s[0]);
               //System.out.println("s[1] : " + s[1]);
               String[] s2 = s[1].split("-.>");
               if(s2.length == 2) {
               //System.out.println("프로젝트 이름 : " + s2[0]);
               //System.out.println("내용 : " + s2[1]);
               String name = s2[0];
               String content = s2[1];
               
               //System.out.println("s : " + s.length);
               //System.out.println("pid : " + pid);
               //System.out.println("name : " + name);
               PidPjt p = new PidPjt(pid, name,content);
               ptmp.add(p);
               }
            }
         }
         if (b != null) {
            String[] btmp = b.split(",");
            for (int j = 0; j < btmp.length; j++) {
               Inter it = new Inter(btmp[j]);
               itmp.add(it);
            }
         }
         
         // 너무 오래 걸림. 각각 호출하도록 바꿔야할듯.
         /* 
         if (v.get(i).getGit() != null) {
            
            try {
               // 웹에서 내용을 가져온다.
            	int status =  Jsoup.connect("https://github.com/" + v.get(i).getGit() + "?tab=repositories").ignoreHttpErrors(true).execute().statusCode();
            	//int status =  Jsoup.connect("https://github.com/kyhoon001" + "?tab=repositories").ignoreHttpErrors(true).execute().statusCode();
            	if(status == 200) {
            		System.out.println("정상쓰");
            		List<Two<String,String>> rtmp = new ArrayList<Two<String,String>>();
            		
            		Document doc = Jsoup.connect("https://github.com/" + v.get(i).getGit() + "?tab=repositories").get();
               // 내용 중에서 원하는 부분을 가져온다.
            		Elements contents = doc.select(".wb-break-all a");
               // 원하는 부분은 Elements형태로 되어 있으므로 이를 String 형태로 바꾸어 준다.
            		String text = contents.text();
            		String[] reposit = text.split(" ");
            		//System.out.println("스플릿까지 했음");
            		//System.out.println("text : " + text);
            		//System.out.println("resposit : " + reposit[0]);
               for (int j = 0; j < reposit.length; j++) {
           			Two<String,String> r = new Two<String,String>();
            	   System.out.println(j + " 번째 추가 : " + reposit[j]);
            	   r.setFirst(reposit[j]);
            	   r.setSecond("https://github.com/" +  v.get(i).getGit() +'/' + reposit[j]);
            	   rtmp.add(r);
            	   //v.get(i).getRepository().add(reposit[j]);
               }
               v.get(i).setRepository(rtmp);
               // System.out.println("reposit" + i + " : " + reposit[i]);
            	}
            } catch (IOException e) { // Jsoup의 connect 부분에서 IOException 오류가 날 수 있으므로 사용한다.
               e.printStackTrace();
               System.out.println("에러 떴다");
               
            }
         }
         */
         v.get(i).setInterest(itmp);
         v.get(i).setProject(ptmp);
      }

      logger.debug("getPools - 호출");
      return new ResponseEntity<List<Pools>>(v, HttpStatus.OK);
   }

   @ApiOperation(value = "모든 검색어 통합 검색하는 것.", response = UserInfo.class)
   @GetMapping("searchAll/tag={tag}&addr={addr}&keyword={keyword}")
   public ResponseEntity<List<Pools>> search(@PathVariable String tag, @PathVariable String addr,
         @PathVariable String keyword) {
      SearchParameter sp = new SearchParameter();
      //sp.setPaging(paging* cnt);
      //sp.setPcnt(cnt);
      String b[] = addr.split(",");
      if (tag.equals("null")) {
         // tag 기술 스택이 없는 경우
         sp.setKeyword(keyword);
         sp.setDong(b[0]);
         sp.setGugun(b[1]);
         sp.setSido(b[2]);
         sp.setCnt(0);
         
         //System.out.println("주소만 넣었으니까 일로 왔겠지?");
         
         List<Pools> v = uiService.searchAll(sp);
         //System.out.println("v하나만 찍어보자 : " +v.get(0));
         for (int i = 0; i < v.size(); i++) {
            List<PidPjt> ptmp = new ArrayList<PidPjt>();
            List<Inter> itmp = new ArrayList<Inter>();
            String aa = v.get(i).getProjects();
            String bb = v.get(i).getInterests();
            if (aa != null) {
               String[] atmp = aa.split(",");
               for (int j = 0; j < atmp.length; j++) {
                  //System.out.println("atmp : " + atmp[j]);
                  String[] s = atmp[j].split(";");
                  int pid = Integer.parseInt(s[0]);
                  String name = s[1];
                  //System.out.println("s : " + s.length);
                  //System.out.println("pid : " + pid);
                  //System.out.println("name : " + name);
                  PidPjt p = new PidPjt(pid, name);
                  ptmp.add(p);
               }
            }
            if (bb != null) {
               String[] btmp = bb.split(",");
               for (int j = 0; j < btmp.length; j++) {
                  Inter it = new Inter(btmp[j]);
                  itmp.add(it);
               }
            }

            v.get(i).setInterest(itmp);
            v.get(i).setProject(ptmp);
         }

         return new ResponseEntity<List<Pools>>(v, HttpStatus.OK);

      } else {
         // 기술 스택 태그가 있는 경우
         String a[] = tag.split(",");
         if (a.length > 0) {
            sp.setTag1(a[0]);
            //System.out.println("tag1 : " + sp.getTag1());
         }
         if (a.length > 1) {
            sp.setTag2(a[1]);
         //System.out.println("tag2 : " + sp.getTag2());
         }
         if (a.length > 2) {
            sp.setTag3(a[2]);
         //System.out.println("tag3 : " + sp.getTag3());
         }
         if (a.length > 3) {
            sp.setTag4(a[3]);
         //System.out.println("tag4 : " + sp.getTag4());
         }
         if (a.length > 4)
            sp.setTag5(a[4]);
         sp.setCnt(a.length);
         //System.out.println("cnt : " + sp.getCnt());
         sp.setSido(b[0]);
         //System.out.println("b[0] : " + sp.getSido());
         sp.setGugun(b[1]);
         //System.out.println("b[0] : " + sp.getGugun());
         sp.setDong(b[2]);
         //System.out.println("b[0] : " +sp.getDong());
         sp.setKeyword(keyword);
         //System.out.println("b[0] : " + sp.getKeyword());
         //System.out.println("오잉잉이용????");
         
         
         
         List<Pools> v = uiService.searchAll(sp);
         System.out.println("searchall 했고");
         //System.out.println("v 하나 받아보자 : " + v.get(0));
         for (int i = 0; i < v.size(); i++) {
            List<PidPjt> ptmp = new ArrayList<PidPjt>();
            List<Inter> itmp = new ArrayList<Inter>();
            String aa = v.get(i).getProjects();
            String bb = v.get(i).getInterests();
            if (aa != null) {
               String[] atmp = aa.split(",");
               for (int j = 0; j < atmp.length; j++) {
                  //System.out.println("atmp : " + atmp[j]);
                  String[] s = atmp[j].split(";");
                  int pid = Integer.parseInt(s[0]);
                  String name = s[1];
                  //System.out.println("s : " + s.length);
                  //System.out.println("pid : " + pid);
                  //System.out.println("name : " + name);
                  PidPjt p = new PidPjt(pid, name);
                  ptmp.add(p);
               }
            }
            if (bb != null) {
               String[] btmp = bb.split(",");
               for (int j = 0; j < btmp.length; j++) {
                  Inter it = new Inter(btmp[j]);
                  itmp.add(it);
               }
            }

            v.get(i).setInterest(itmp);
            v.get(i).setProject(ptmp);
         }

         
         // 어차피 널이 들어감.
         return new ResponseEntity<List<Pools>>(v, HttpStatus.OK);

      }
   }

   @ApiOperation(value = "카카오로 로그인 ", response = String.class)
   @PostMapping("/login/kakao")
   public ResponseEntity<Map<String, Object>> loginUserForKakao(@RequestBody String kakaoId ,HttpServletResponse response){
     
      Map<String, Object> resultMap = new HashMap<>();
         HttpStatus status = null;

//    	 System.out.println(kakaoId);
         try {
             UserInfo loginUser = uiService.loginForKakao(kakaoId);
             System.out.println(loginUser.toString());
             uiService.updateLastDate(loginUser);
             // 로그인했다면 토큰생성
             String token = jwtService.create(loginUser);
             // 토큰 정보는 request 헤더로 보내고 나머지는 map에 담음
             response.setHeader("jwt-auth-token", token);
             resultMap.put("status", true);
             resultMap.put("data", loginUser);
             status = HttpStatus.ACCEPTED;
          } catch (RuntimeException e) {
             logger.error("로그인 안됨", e);
             resultMap.put("message", e.getMessage());
             status = HttpStatus.INTERNAL_SERVER_ERROR;
          }
          return new ResponseEntity<Map<String, Object>>(resultMap, status);
         
         // 에러를 바꿔줘야할것같아여 ㅠㅠ
   }
   @PostMapping("/signin")
   public ResponseEntity<Map<String, Object>> signin(@RequestBody User user, HttpServletResponse response) {
      // System.out.println("test1 : " + user.getId() + " : " + user.getPw());

      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;
      try {
         // System.out.println(user.getId() + " : " + user.getPw());
         UserInfo loginUser = uiService.signin(user.getId(), user.getPw());
         uiService.updateLastDate(loginUser);
         // 로그인했다면 토큰생성
         String token = jwtService.create(loginUser);
         // 토큰 정보는 request 헤더로 보내고 나머지는 map에 담음
         response.setHeader("jwt-auth-token", token);
         resultMap.put("status", true);
         resultMap.put("data", loginUser);
         status = HttpStatus.ACCEPTED;
      } catch (RuntimeException e) {
         logger.error("로그인 안됨", e);
         resultMap.put("message", e.getMessage());
         status = HttpStatus.INTERNAL_SERVER_ERROR;
      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }

   @GetMapping("/getCurrList")
   public ResponseEntity<List<UserInfo>> getCurrList() {
      List<UserInfo> list = uiService.getCurrList();
      return new ResponseEntity<List<UserInfo>>(list, HttpStatus.OK);
   }

   @GetMapping("/getAddressList/{pid}")
   public ResponseEntity<List<UserInfo>> getAddressList(@PathVariable int pid) {
      List<UserInfo> list = uiService.getAddressList(pid);
      return new ResponseEntity<List<UserInfo>>(list, HttpStatus.OK);
   }

   @GetMapping("/getRecommended/User/{id}")
   public ResponseEntity<List<UserSimple>> getRecommendedUser(@PathVariable String id) {
	
      List<UserInfo> list = uiService.getRecommendedUser(id);
      List<UserSimple> users = new ArrayList<>();
     
      for (int i = 0; i < list.size(); i++) {
         if (list.get(i).getId().equals(id))
            continue;
         UserInfo tmp = uiService.select(list.get(i).getId());
         UserSimple us = new UserSimple(tmp.getId(), tmp.getNickname(), tmp.getGit(), tmp.getLastDate(),
               tmp.isState(), tmp.getResponsibility(), tmp.isLeaveUser());
         users.add(us);
      }
      List<UserSimple> simple = new ArrayList<>();
      if(users.size() <3) {
    	  int num = 3-users.size();
    	  for(int i=0;i<num;i++)
    		  users.add(users.get(i));
      }else {
    	  boolean[] visit = new boolean [users.size()];
    	  int idx = 0;
    	  Random rand = new Random();
    	  while(true) {
    		  if(idx ==3) break;
    		  int n = rand.nextInt(users.size());
    		  if(!visit[n]) {
    			  simple.add(users.get(n));
    			  visit[n] =true;
    			  idx++;
    		  }
    		  
    	  }
      }
      return new ResponseEntity<List<UserSimple>>(simple, HttpStatus.OK);
   }

   @GetMapping("/getRecommended/PJT/{id}")
   public ResponseEntity<List<Projectcnt>> getRecommendedPJT(@PathVariable String id) {
      List<Project> list = uiService.getRecommendedPJT(id); // 추천 15개 받아옴 
      List<Projectcnt> finallist = new ArrayList<>();
      List<Projectcnt> projects = new ArrayList<>();
      List<Pmember> myprojects = pmservice.selectByUserId(id); 

      for (int i = 0; i < list.size(); i++) {
         boolean flag = false;
         int thispid = list.get(i).getPid(); 
         for (int mp = 0; mp < myprojects.size(); mp++) {
            if (thispid == myprojects.get(mp).getPid()) {
               flag = true;
               break;
            }
         }
         if (flag)
            continue;
         
         Projectcnt tmp = pjtService.searchByPID(thispid);
         List<Pinterest> pinter = pinterService.select(thispid);
         int size = pinter.size();
         if (size > 0)
            tmp.setTag1(pinter.get(0).getInterest());
         if (size > 1)
            tmp.setTag2(pinter.get(1).getInterest());
         if (size > 2)
            tmp.setTag3(pinter.get(2).getInterest());
         if (size > 3)
            tmp.setTag4(pinter.get(3).getInterest());
         if (size > 4)
            tmp.setTag5(pinter.get(4).getInterest());
         projects.add(tmp);
      
      }
      System.out.println(projects.size());
      
      if(projects.size() <3) {
    	  for(int i=0;i<projects.size();i++) {
    		  finallist.add(projects.get(i));
    	  }
    	  int num = 3-projects.size();
    	  for(int i=0;i<num;i++)
    		  finallist.add(projects.get(0));
      }else {
    	  boolean[] visit = new boolean [projects.size()];
    	  int idx = 0;
    	  Random rand = new Random();
    	  while(true) {
    		  if(idx ==3) break;
    		  int n = rand.nextInt(projects.size());
    		  if(!visit[n]) {
    			  finallist.add(projects.get(n));
    			  visit[n] =true;
    			  idx++;
    		  }
    	  }
      }
      return new ResponseEntity<List<Projectcnt>>(finallist, HttpStatus.OK);
   }

   @PostMapping("/info")
   public ResponseEntity<Map<String, Object>> getInfo(HttpServletRequest request, @RequestBody UserInfo userinfo) {
      Map<String, Object> resultMap = new HashMap<>();
      HttpStatus status = null;
      try {
         String info = uiService.getServerInfo();
         resultMap.putAll(jwtService.get(request.getHeader("jwt-auth-token")));
         resultMap.put("status", true);
         resultMap.put("info", info);
         resultMap.put("request_body", userinfo);

      } catch (RuntimeException e) {
         logger.error("어흐 정보조회 실패 ", e);
         resultMap.put("message", e.getMessage());
         status = HttpStatus.INTERNAL_SERVER_ERROR;

      }
      return new ResponseEntity<Map<String, Object>>(resultMap, status);
   }

   @ApiOperation(value = "모든 유저의 정보를 반환한다.", response = List.class)
   @GetMapping
   public ResponseEntity<List<UserSimple>> getAllUsers() throws Exception {
      logger.debug("getAllUsers - 호출");
      List<UserInfo> all = uiService.selectAll();
      List<UserSimple> users = new ArrayList<>();
      for (int i = 0; i < all.size(); i++) {
         UserInfo tmp = all.get(i);
         users.add(new UserSimple(tmp.getId(), tmp.getNickname(), tmp.getGit(), tmp.getLastDate(), tmp.isState(),
               tmp.getResponsibility(), tmp.isLeaveUser()));
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
   public ResponseEntity<List<UserInfo>> selectByAddr(@PathVariable String sido, @PathVariable String gugun,
         @PathVariable String dong) throws Exception {
      logger.debug("selectByAddr - 호출");
      Addr v = new Addr();
      v.setDong(dong);
      v.setGugun(gugun);
      v.setSido(sido);
      return new ResponseEntity<List<UserInfo>>(uiService.selectByAddr(v), HttpStatus.OK);
   }

   @ApiOperation(value = "회원가입기능, 새로운 유저를 추가한다. 인증메일도 보내진다 ", response = String.class)
   @PostMapping
   public ResponseEntity<String> insertUser(@RequestBody UserInfo q)
         throws MessagingException, UnsupportedEncodingException {
      logger.debug("insertUser - 호출");
      System.out.println(q.toString());
  
      boolean emailTest = checkRex(q.getId(), "id");
      boolean pwTest = checkRex(q.getPw(), "password");
      boolean nameTest = checkRex(q.getName(), "name");
      boolean nicknameTest = checkRex(q.getNickname(), "nickname");
      boolean phoneTest = checkRex(q.getPhone(), "phone");
      boolean resTest = false;
      String rep = q.getResponsibility();
      // System.out.println(rep);
      if (rep.equals("개발") || rep.equals("디자인") || rep.equals("기획")) {
         resTest = true;
      }
      // System.out.println(emailTest + " " + pwTest + " " + nicknameTest +" " +
      // phoneTest +" " + resTest );
      if (emailTest && pwTest && nameTest && nicknameTest && phoneTest && resTest) {
         // System.out.println("들어감");
         int test = uiService.insert(q);
         // System.out.println("들어감 : " + test);
         if (test == 1) {
            // 이메일 인증
            String key = new MailTempKey().getKey(50, false);
            Email e = new Email(q.getId(), key);
            eService.insert(e);
            MailHandler sendMail = new MailHandler(mailSender);
            sendMail.setSubject("[이메일 인증]");
            sendMail.setText(new StringBuffer().append("<h1>메일인증</h1>")
                  .append("<a href='https://localhost:8080/api/email/validKey=").append(key)
                  .append("' target='_blenk'>이메일 인증 확인</a>").toString());
            sendMail.setFrom("test@gmail.com", "admin");
            sendMail.setTo(q.getId());
            sendMail.send();
//            Message msg = new Message();
//            msg.setFromUser("admin"); msg.setToUser(q.getId());
//            msg.setContent("가입해주셔서 감사합니다! 우리 함께 멋진 프로젝트를 해봐요 🤗");
//            mService.insert(msg);
            return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
         }
      }
      return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT); // 에러를 바꿔줘야할것같아여 ㅠㅠ
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
      if (uiService.update(user) == 1) {
         return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
      }
      return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
   }

   public boolean checkRex(String input, String option) {
      Pattern p = Pattern.compile(" ");
      if (option == "id") {
         p = Pattern
               .compile("^[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\\.]?[0-9a-zA-Z])*\\.[a-zA-Z]{2,3}$");
      } else if (option == "name") {
         p = Pattern.compile("^[가-힣]*$");
      } else if (option == "phone") {
         p = Pattern.compile("^?([0-9]{3})-?([0-9]{3,4})-?([0-9]{4})$");
      } else if (option == "password") {
         p = Pattern.compile("^[0-9a-zA-Z_]{8,20}$");
      } else if (option == "nickname") {
         p = Pattern.compile("^[ㄱ-ㅎ|가-힣|a-z|A-Z|0-9|\\*]+$");
      }

      Matcher m = p.matcher(input);
      if (m.find()) {
         return true;
      }

      System.out.println(option + "틀렸음 ㅡㅡ ");

      return false;
   }

}