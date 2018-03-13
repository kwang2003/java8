package com.pachiraframework.java8;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import org.joda.time.DateTime;

import com.google.common.base.Throwables;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class JWTTest {
	private static String SECRET_KEY = "ABCDE";
	public String buildJwt(Date exp) {
	    String jwt = Jwts.builder()
	            .signWith(SignatureAlgorithm.HS256,SECRET_KEY)//SECRET_KEY是加密算法对应的密钥，这里使用额是HS256加密算法
	            .setExpiration(exp)//expTime是过期时间
	            .setIssuer("kx")
	            .setAudience("fuwu")
	            .claim("user_id",12345)//该方法是在JWT中加入值为vaule的key字段
	            .claim("mobile", "13812345678")
	            .claim("login_id", "zhangsan")
	            .compact();
	    return jwt;
	}
	
	public boolean isJwtValid(String jwt) {
	    try {
	        //解析JWT字符串中的数据，并进行最基础的验证
	        Claims claims = Jwts.parser()
	                .setSigningKey(SECRET_KEY)//SECRET_KEY是加密算法对应的密钥，jjwt可以自动判断机密算法
	                .parseClaimsJws(jwt)//jwt是JWT字符串
	                .getBody();
	        String vaule = claims.get("mobile", String.class);//获取自定义字段key
	        //判断自定义字段是否正确
	        if ("13812345678".equals(vaule)) {
	            return true;
	        } else {
	            return false;
	        }
	    }
	    //在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
	    //在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
	    catch (SignatureException|ExpiredJwtException e) {
	    	System.out.println(Throwables.getStackTraceAsString(e));;
	        return false;
	    }
	}
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException {
		DateTime dateTime = DateTime.now().plusMinutes(10);
		Date expireDate = dateTime.toDate();
		JWTTest test = new JWTTest();
//		test.buildJwt();
		
		String token = test.buildJwt(expireDate);
		System.out.println(token);
		//输出 eyJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1MTI2OTU4MDAsImlzcyI6Imt4IiwiYXVkIjoiZnV3dSIsInVzZXJfaWQiOjEyMzQ1LCJtb2JpbGUiOiIxMzgxMjM0NTY3OCIsImxvZ2luX2lkIjoiemhhbmdzYW4ifQ.JSluip6WnL3e5z5e_CbI5QsMiXCuIwsaCFHEAF7QbXw
		String[] tokens = token.split("\\.");
		Decoder decoder = Base64.getDecoder();
		System.out.println("HEADER:"+new String(decoder.decode(tokens[0])));
		System.out.println("PAYLOAD:"+new String(decoder.decode(tokens[1])));
		boolean isValid = test.isJwtValid(token);
		System.out.println(isValid);
	}
	
}
