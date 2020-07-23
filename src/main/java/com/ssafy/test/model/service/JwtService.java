package com.ssafy.test.model.service;

import org.springframework.stereotype.Component;

import com.ssafy.test.model.dto.UserInfo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtService {

	static Logger logger =LoggerFactory.getLogger(JwtService.class);
	@Value("${jwt.salt}")
	private String salt;
	
	@Value("${jwt.expmin}")
	private Long expireMin;
	
	//로그인 성공하면 사용자 정보 기반으로 jwtToken 생성해 반환한다 
	public String create(final UserInfo userinfo) {
		
		logger.trace("time :{}",expireMin);
		final JwtBuilder builder = Jwts.builder();
		//헤더설정 토큰 타입으로 값 고정
		builder.setHeaderParam("typ", "JWT");
		builder.setSubject("login token")
		.setExpiration(new Date(System.currentTimeMillis()+1000*60*expireMin))
		.claim("UserInfo", userinfo).claim("second", "추가정보?");
		//암호화했음
		builder.signWith(SignatureAlgorithm.HS256, salt.getBytes());
		//직렬화
		final String jwt = builder.compact();
		logger.debug("토큰발행 : {}",jwt );
		return jwt;
	}
	
	//전달받은 토큰이 제대로 생성된건지 확인하고 문제가 있닫면 Runtime에러를 발생시킴
	public void checkValid(final String jwt) {
		logger.trace("토큰점검  :{}",jwt);
		Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
	}
	
	//토큰을 분석해서 필요한 정보를 반환하는 것
	public Map<String, Object> get (final String jwt){
		Jws<Claims> claims = null;
		try {
			claims = Jwts.parser().setSigningKey(salt.getBytes()).parseClaimsJws(jwt);
					
		}catch (final Exception e) {
			throw new RuntimeException();
		}
		
		logger.trace("claims:{}",claims);
		return claims.getBody();
	}
	
}
