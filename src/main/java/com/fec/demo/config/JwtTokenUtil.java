package com.fec.demo.config;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fec.demo.service.CustomUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	// Đoạn JWT_SECRET này là bí mật, chỉ có phía server biết
	@Value("${jwt.secret}")
	private String JWT_SECRET;

	// Thời gian có hiệu lực của chuỗi jwt
	private final long JWT_EXPIRATION = 86_400_000;

	// Tạo ra jwt từ thông tin user
	public String generateToken(CustomUserDetails userDetails) {
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);
		// Tạo chuỗi json web token từ id của user.
		return Jwts.builder().setSubject(Long.toString(userDetails.getUser().getId())).setIssuedAt(now) // thời gian
																										// build token
				.setExpiration(expiryDate) // ngày hết hạn
				.signWith(SignatureAlgorithm.HS512, JWT_SECRET)// kiểm băm
				.compact();
	}

	// Lấy thông tin user từ jwt
	public Long getUserIdFromJWT(String token) {
		if (token != null) {
			Claims claims = Jwts.parser()
					.setSigningKey(JWT_SECRET)
					.parseClaimsJws(token)
					.getBody();// lấy nội dung
//user id = claims.getsubject
			return Long.parseLong(claims.getSubject());
		}
		return null;
	}

// check token
	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			log.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			log.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims string is empty.");
		}
		return false;
	}
}