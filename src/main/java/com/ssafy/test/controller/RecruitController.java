package com.ssafy.test.controller;

import java.util.Comparator;
import java.util.List;

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
import com.ssafy.test.model.dto.AddrAndTag;
import com.ssafy.test.model.dto.Interest;
import com.ssafy.test.model.dto.Pinterest;
import com.ssafy.test.model.dto.Recruit;
import com.ssafy.test.model.dto.TagList;
import com.ssafy.test.model.dto.UserInfo;
import com.ssafy.test.model.dto.Usertag;
import com.ssafy.test.model.service.InterestService;
import com.ssafy.test.model.service.PinterestService;
import com.ssafy.test.model.service.RecruitService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = { "*" }, maxAge = 6000)
@RequestMapping("/api/recruit")
public class RecruitController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	@Autowired
	private RecruitService rService;

	@Autowired
	private InterestService iService;

	@Autowired
	private PinterestService piService;

	@ApiOperation(value = "모든 구인구직 게시판의 정보를 반환한다.", response = List.class)
	@GetMapping
	public ResponseEntity<List<Recruit>> retrieveBoard() throws Exception {
		return new ResponseEntity<List<Recruit>>(rService.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "모든 구인구직 게시판의 정보를 소팅해서 반환한다.", response = List.class)
	@GetMapping("/sorting/{id}")
	public ResponseEntity<List<Recruit>> retrieveBoard(@PathVariable String id) throws Exception {
		List<Recruit> list = rService.selectAll();
		List<Interest> iList = iService.selectById(id);
		list.sort(new Comparator<Recruit>() {

			@Override
			public int compare(Recruit o1, Recruit o2) {
				List<Pinterest> pi1 = piService.select(o1.getPid());
				List<Pinterest> pi2 = piService.select(o2.getPid());
				Integer cnt1 = 0, cnt2 = 0;
				for (int i = 0; i < iList.size(); i++) {
					String ui = iList.get(i).getInterest();
					for (int j = 0; j < pi1.size(); j++) {
						if (pi1.get(j).getInterest().equals(ui)) {
							cnt1++;
						}

						if (pi2.get(j).getInterest().equals(ui)) {
							cnt2++;
						}
					}
				}

				return cnt2.compareTo(cnt1);
			}
		});

		return new ResponseEntity<List<Recruit>>(rService.selectAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "해당 지역에 거주하는 게시판의 정보를 반환한다..", response = List.class)
	@GetMapping("/addr/sido={sido}&gugun={gugun}&dong={dong}")
	public ResponseEntity<List<Recruit>> selectByAddr(@PathVariable String sido, @PathVariable String gugun,
			@PathVariable String dong) throws Exception {
		Addr v = new Addr();
		v.setDong(dong);
		v.setGugun(gugun);
		v.setSido(sido);
		return new ResponseEntity<List<Recruit>>(rService.selectByAddr(v), HttpStatus.OK);
	}

	@ApiOperation(value = "완전히 일치하는 유저의 id를 반환한다.", response = Usertag.class)
	@GetMapping("selectSame/{tag}")
	public ResponseEntity<List<Recruit>> selectSame(@PathVariable String tag) {
		TagList v = new TagList();
		String a[] = tag.split(",");
		int b = a.length;
		if (a.length > 0)
			v.setTag1(a[0]);
		if (a.length > 1)
			v.setTag2(a[1]);
		if (a.length > 2)
			v.setTag3(a[2]);
		if (a.length > 3)
			v.setTag4(a[3]);
		if (a.length > 4)
			v.setTag5(a[4]);
		v.setCnt(b);
		// 어차피 널이 들어감.

		return new ResponseEntity<List<Recruit>>(rService.selectSame(v), HttpStatus.OK);
	}

	@ApiOperation(value = "태그와 주소 혼합해서 검색하는 것.", response = Usertag.class)
	@GetMapping("selectAddrAndTag/tag={tag}&addr={addr}")
	public ResponseEntity<List<Recruit>> selectAddrAndTag(@PathVariable String tag, @PathVariable String addr) {
		AddrAndTag v = new AddrAndTag();
		String b[] = addr.split(",");
		if (tag == null) {
			v.setDong(b[0]);
			v.setGugun(b[1]);
			v.setSido(b[2]);
			v.setCnt(0);

			for (String string : b) {
				System.out.println(string);
			}
			return new ResponseEntity<List<Recruit>>(rService.selectAddrAndTag(v), HttpStatus.OK);

		} else {
			String a[] = tag.split(",");

			if (a.length > 0)
				v.setTag1(a[0]);
			if (a.length > 1)
				v.setTag2(a[1]);
			if (a.length > 2)
				v.setTag3(a[2]);
			if (a.length > 3)
				v.setTag4(a[3]);
			if (a.length > 4)
				v.setTag5(a[4]);
			v.setCnt(a.length);
			v.setSido(b[0]);
			v.setGugun(b[1]);
			v.setDong(b[2]);
			// 어차피 널이 들어감.
			for (String string : a) {
				System.out.println(string);
			}
			for (String string : b) {
				System.out.println(string);
			}
			return new ResponseEntity<List<Recruit>>(rService.selectAddrAndTag(v), HttpStatus.OK);

		}
	}

	@ApiOperation(value = "글번호에 해당하는 구인구직 게시판의 정보를 반환한다.", response = Recruit.class)
	@GetMapping("{rnum}")
	public ResponseEntity<Recruit> detailBoard(@PathVariable int rnum) {
		return new ResponseEntity<Recruit>(rService.select(rnum), HttpStatus.OK);
	}

	@ApiOperation(value = "새로운 구인구직 게시판 정보를 입력한다. 그리고 DB입력 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PostMapping
	public ResponseEntity<String> writeBoard(@RequestBody Recruit r) {
		if (rService.insert(r) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트의 정보를 수정한다. 그리고 DB수정 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@PutMapping("{rnum}")
	public ResponseEntity<String> updateBoard(@RequestBody Recruit r) {

		if (rService.update(r) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}

	@ApiOperation(value = "글번호에 해당하는 프로젝트의 정보를 삭제한다. 그리고 DB삭제 성공여부에 따라 'success' 또는 'fail' 문자열을 반환한다.", response = String.class)
	@DeleteMapping("{rnum}")
	public ResponseEntity<String> deleteBoard(@PathVariable int rnum) {

		if (rService.delete(rnum) != 0) {
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		}
		return new ResponseEntity<String>(FAIL, HttpStatus.NO_CONTENT);
	}
}
