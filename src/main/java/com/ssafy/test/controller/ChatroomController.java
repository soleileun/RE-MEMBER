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

import com.ssafy.test.model.dto.Chatroom;
import com.ssafy.test.model.dto.ChatroomChat;
import com.ssafy.test.model.service.ChatroomService;
import com.ssafy.test.model.service.UserInfoService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/chatroom")
public class ChatroomController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ChatroomService Service;
	

	@Autowired
	private UserInfoService uService;

	@ApiOperation(value = "특정 유저가 속한 모든 roomName을 반환한다", response = List.class)
	@GetMapping("chat/name={uid}")
	public ResponseEntity<List<Chatroom>> retrieveChatroom(@PathVariable String uid) throws Exception {
		return new ResponseEntity<List<Chatroom>>(Service.selectAll(uid), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 유저가 속한 roomlist를 조회한다. 조인 쿼리", response = List.class)
	@GetMapping("chat/roomlist={uid}")
	public ResponseEntity<List<ChatroomChat>> selectRoomList(@PathVariable String uid) throws Exception {
		return new ResponseEntity<List<ChatroomChat>>(Service.selectDetailAll(uid), HttpStatus.OK);
	}
	

	@ApiOperation(value = "특정 roomName의 모든 멤버 정보를 반환한다", response = List.class)
	@GetMapping("chat/roomname={roomName}")
	public ResponseEntity<List<Chatroom>> selectMember(@PathVariable String roomName) throws Exception {
		return new ResponseEntity<List<Chatroom>>(Service.selectMember(roomName), HttpStatus.OK);
	}
	


	@ApiOperation(value = "특정 roomName의 모든 멤버 정보를 반환한다", response = List.class)
	@GetMapping("exists/uid1={uid1}&uid2={uid2}")
	public ResponseEntity<String> selectOneToOne(@PathVariable String uid1, @PathVariable String uid2) throws Exception {
		Map<String,String> k = new HashMap<>();
		k.put("uid1", uid1);
		k.put("uid2", uid2);
		return new ResponseEntity<String>(Service.selectOneToOne(k), HttpStatus.OK);
	}
	
	
	
	
	
	@ApiOperation(value = "새로운 채팅방 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Chatroom v) {
		if(uService.select(v.getUid()) != null) {
			System.out.println("유저가 있넹");

			if (Service.insert(v) != 0) {
				return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
			}
			
		}
		else {
			System.out.println("유저가 없넹");
			return new ResponseEntity<String>("해당 ID를 가진 유저가 없습니다. ID를 확인해주세요.", HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "특정 유저가 특정 roomName을 가진 채팅방에서 퇴장한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("delete/roomname={roomName}&uid={uid}")
	public ResponseEntity<String> deleteBoard(@PathVariable String roomName, @PathVariable String uid) {

		Chatroom v = new Chatroom();
		v.setRoomName(roomName);
		v.setUid(uid);
		
		if (Service.delete(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
	

	@ApiOperation(value = "정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("change/{roomName}&{uid}")
	public ResponseEntity<String> updateBoard(@PathVariable String roomName, @PathVariable String uid) {

		Chatroom v = new Chatroom();
		v.setRoomName(roomName);
		v.setUid(uid);
		
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
