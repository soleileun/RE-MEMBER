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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.test.model.dto.Board;
import com.ssafy.test.model.service.BoardService;
import com.ssafy.test.model.dto.Comments;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/board")
public class BoardController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private BoardService bService;
	

	@ApiOperation(value = "모든 게시글의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Board>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Board>>(bService.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "모든 게시글의 댓글 정보를 반환한다.", response = List.class)
	@GetMapping("getcomments/{bno}")
	public ResponseEntity<List<Comments>> getComments(@PathVariable int bno) {
		return new ResponseEntity<List<Comments>>(bService.getComments(bno), HttpStatus.OK);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 반환한다.", response = Board.class)
	@GetMapping("no/{bno}")
	public ResponseEntity<Board> detailnoBoard(@PathVariable int bno) {
		return new ResponseEntity<Board>(bService.select(bno), HttpStatus.OK);
	}


	@ApiOperation(value = "특정 속성을 가진 글을 조회한다. EX) 공지사항", response = List.class)
	@GetMapping("statesearching/{bstate}")
	public ResponseEntity<List<Board>> detailstateBoard(@PathVariable String bstate) {
		System.out.println("bstats : " + bstate);
		return new ResponseEntity<List<Board>>(bService.search(bstate), HttpStatus.OK);
	}
	

	//search part@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	@ApiOperation(value = "특정 사람이 쓴 글을 검색한다.", response = List.class)
	@GetMapping("search/id/{bwriter}")
	public ResponseEntity<List<Board>> searchById(@PathVariable String bwriter) {
		return new ResponseEntity<List<Board>>(bService.searchById(bwriter), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 내용이 포함된 글을 검색한다.", response = List.class)
	@GetMapping("search/contents/{bcontent}")
	public ResponseEntity<List<Board>> searchByContents(@PathVariable String bcontent) {
		return new ResponseEntity<List<Board>>(bService.searchByContents(bcontent), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 제목이 포함된 글을 검색한다.", response = List.class)
	@GetMapping("search/title/{btitle}")
	public ResponseEntity<List<Board>> searchByTitle(@PathVariable String btitle) {
		return new ResponseEntity<List<Board>>(bService.searchByTitle(btitle), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 제목이나 내용이 포함된 글을 검색한다.", response = List.class)
	@GetMapping("search/contentandtitle/{btitle}")
	public ResponseEntity<List<Board>> searchByTitleAndContents(@PathVariable String btitle) {
		return new ResponseEntity<List<Board>>(bService.searchByTitleAndContents(btitle), HttpStatus.OK);
	}

	//search part	END @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	

	//type search part@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	
	@ApiOperation(value = "특정 카테고리에서 특정 사람이 쓴 글을 검색한다.", response = List.class)
	@GetMapping("typesearch/writer={bwriter},bstate={bstate}")
	public ResponseEntity<List<Board>> typesearchById(@PathVariable String bwriter, String bstate) {
		Board v;
		v = new Board();
		v.setBstate(bstate);
		v.setBwriter(bwriter);
		return new ResponseEntity<List<Board>>(bService.typesearchById(v), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 카테고리에서 특정 내용이 포함된 글을 검색한다.", response = List.class)
	@GetMapping("typesearch/bcontent={bcontent},bstate={bstate}")
	public ResponseEntity<List<Board>> typesearchByContents(@PathVariable String bcontent, String bstate) {
		Board v;
		v = new Board();
		v.setBstate(bstate);
		v.setBcontent(bcontent);
		return new ResponseEntity<List<Board>>(bService.typesearchByContents(v), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 카테고리에서 특정 제목이 포함된 글을 검색한다.", response = List.class)
	@GetMapping("typesearch/btitle={btitle},bstate={bstate}")
	public ResponseEntity<List<Board>> typesearchByTitle(@PathVariable String btitle, String bstate) {
		Board v;
		v = new Board();
		v.setBstate(bstate);
		v.setBtitle(btitle);
		return new ResponseEntity<List<Board>>(bService.typesearchByTitle(v), HttpStatus.OK);
	}

	@ApiOperation(value = "특정 카테고리에서 특정 제목이나 내용이 포함된 글을 검색한다.", response = List.class)
	@GetMapping("typesearch/btitleandbcontent={btitle},bstate={bstate}")
	public ResponseEntity<List<Board>> typesearchByTitleAndContents(@PathVariable String btitle, String bstate) {
		Board v;
		v = new Board();
		v.setBstate(bstate);
		v.setBtitle(btitle);
		v.setBcontent(btitle);
		return new ResponseEntity<List<Board>>(bService.typesearchByTitleAndContents(v), HttpStatus.OK);
	}

	//type search part	END @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

	
	@ApiOperation(value = "새로운 글 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Board v) {
		if (bService.insert(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("change/{bno}")
	public ResponseEntity<String> updateBoard(@RequestBody Board v) {

		if (bService.update(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 게시글의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("delete/{bno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int bno) {

		if (bService.delete(bno) != 0) {
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
