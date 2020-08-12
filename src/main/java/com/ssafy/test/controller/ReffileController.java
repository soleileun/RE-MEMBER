package com.ssafy.test.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import com.ssafy.test.model.dto.Reffile;
import com.ssafy.test.model.service.ReffileService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/reffile")
public class ReffileController {

	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private ReffileService Service;
	

	
	/////////////////파일 업로드 주소
	private static String UPLOADED_FOLDER = "C:\\Users\\multicampus\\Desktop\\asdasd\\";
	///////////////////
	
	
	@ApiOperation(value = "모든 reffile의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Reffile>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Reffile>>(Service.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "fno에 해당하는 reffile의 정보를 반환한다.", response = Reffile.class)
	@GetMapping("{fno}")
	public ResponseEntity<Reffile> detailBoard(@PathVariable int fno) {
		return new ResponseEntity<Reffile>(Service.select(fno), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 reffile의 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Reffile v) {
		if (Service.insert(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "fno에 해당하는 reffile의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{fno}")
	public ResponseEntity<String> updateBoard(@RequestBody Reffile v) {

		if (Service.update(v) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "fno에 해당하는 reffile의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{fno}")
	public ResponseEntity<String> deleteBoard(@PathVariable int fno) {

		if (Service.delete(fno) != 0) {
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
	
	
	//파일업로드 예시
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		@RequestMapping(path = "/files", method = RequestMethod.POST,
		        consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
		public @ResponseBody ResponseEntity<String> add(
		        @RequestParam("file") MultipartFile file,
		        @RequestParam("fboardno") int fboardno,
		        @RequestParam("makeId") String makeId) {
		    System.out.println(file.getOriginalFilename()); //foname으로 들어가면 될듯.
		    
		    try {
				//DB에 저장하는 작업
				Reffile v = new Reffile();
				SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
				Date now = new Date();
				//System.out.println(format1.format(now));
				v.setFboardno(fboardno);
				v.setFoname(file.getOriginalFilename());
				v.setFsname("file_set" + format1.format(now) + file.getOriginalFilename() ); // 이름 바꿔주자
				v.setFsize(file.getSize());
				v.setMakeId(makeId);
				v.setMakeDay(new Date());
				//date 관련은 다 생성자에서 넣어주자.

				saveUploadedFiles(Arrays.asList(file), v.getFsname());
				System.out.println("업뎃 : " + Service.update(v));
				System.out.println(v.getFboardno());
				System.out.println(v.getFoname());
				System.out.println(v.getFsname());
				System.out.println(v.getFsize());
				System.out.println(v.getMakeDay());
				System.out.println(v.getMakeId());
				if (Service.update(v) != 0) {
					System.out.println("db에 저장 완료");
				}

			} catch (IOException e) {
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			}
		    return new ResponseEntity<>("Created", HttpStatus.OK);
		}
		
		private void saveUploadedFiles(List<MultipartFile> files, String fname) throws IOException {
			System.out.println("업로드 파일 함수 실행");
			for (MultipartFile file : files) {

				if (file.isEmpty()) {
					System.out.println("파일 비어있음");
					continue; // next pls
				}

				byte[] bytes = file.getBytes();
				System.out.println("bytes에 받았고 : " + file.getBytes());
				Path path = Paths.get(UPLOADED_FOLDER + fname);
				System.out.println("주소 : " + path);
				Files.write(path, bytes);
				System.out.println("write끝");

			}
		}

		////////////////////////////////////////////
}
