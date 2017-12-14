package com.pachiraframework.java8;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Date;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Hex;
import org.joda.time.DateTime;

import com.google.common.base.Throwables;
import com.google.common.hash.Hashing;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.TextCodec;

/**
 * @author wangxuzheng
 *
 */
public class JwtTest2 {
	private static String SECRET_KEY = "secret";
	public String buildJwt(Date exp) throws UnsupportedEncodingException {
	    StringBuffer jwt = new StringBuffer();
	    String header = "{\r\n" + 
	    		"  \"alg\": \"HS256\",\r\n" + 
	    		"  \"typ\": \"JWT\"\r\n" + 
	    		"}";
	    String payload  = "{\"sub\":\"1234567890\",\"name\":\"John Doe\",\"admin\":true}";
//	    String encodedHeader = Base64.getEncoder().encodeToString(header.getBytes());
//	    String encodedPayload = Base64.getEncoder().encodeToString(payload.getBytes());
//	    String encodedHeader = base64UrlEncode(header.getBytes());
//	    String encodedPayload = base64UrlEncode(payload.getBytes());
	    String encodedHeader = UrlBase64Coder.encoded(format(header));
	    String encodedPayload = UrlBase64Coder.encoded(payload);
	    String content = encodedHeader + "."+encodedPayload;
	    String sign = sign(SECRET_KEY, content);
	    System.out.println("A:");
	    System.out.println(sign);
	    System.out.println("GUAVA:");
	    System.out.println(Hashing.hmacSha256(SECRET_KEY.getBytes()).hashBytes(content.getBytes()).toString());
	    System.out.println("SIGN2:");
	    System.out.println(sign2(SECRET_KEY, content));
	    System.out.println("BAS64URLENCODED:");
	    System.out.println(urlEncode(sign));
	    jwt.append(content);
	    jwt.append(".");
	    jwt.append(sign);
	    return jwt.toString();
	}
	
	private String format(String str) {
		return str.replaceAll("\r\n", "").replaceAll(" ", "");
	}
	
	private String urlEncode(String str) {
		String s = new String(str);
		s = s.split("=")[0];
		s = s.replace('+', '-'); 
		s = s.replace('/', '_');
		return s;
	}
	
	public String base64UrlEncode(byte[] simple) {
		String s = new String(Base64.getEncoder().encodeToString(simple)); 
		return urlEncode(s);
	}

	public byte[] base64UrlDecode(String cipher) {
		String s = cipher;
		s = s.replace('-', '+');
		s = s.replace('_', '/'); 
		switch (s.length() % 4) {
			case 0:
				break; 
			case 2:
				s += "==";
				break; 
			case 3:
				s += "=";
				break;
			default:
				System.err.println("Illegal base64url String!");
		}
		return Base64.getDecoder().decode(s.getBytes()); 
	}
	public static String sign2(String key, String data) {
	    try {
	        Mac hmac = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
	        hmac.init(secretKey);
	        return new String(Hex.encodeHex(hmac.doFinal(data.getBytes("UTF-8"))));
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
	
	public String sign(String key, String data) {
	    try {

	        Mac sha256HMAC = Mac.getInstance("HmacSHA256");
	        SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "HmacSHA256");
	        sha256HMAC.init(secretKey);

	        return new String(Hex.encodeHex(sha256HMAC.doFinal(data.getBytes("UTF-8"))));

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    } catch (InvalidKeyException e) {
	        e.printStackTrace();
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }

	    return null;
	}
	
	public boolean isJwtValid(String jwt) {
	    try {
	        //解析JWT字符串中的数据，并进行最基础的验证
	        Claims claims = Jwts.parser()
	                .setSigningKey(SECRET_KEY)
	                .parseClaimsJws(jwt)
	                .getBody();
	        String value = claims.get("mobile", String.class);
	        System.out.println(value);
	        return true;
	    }
	    //在解析JWT字符串时，如果密钥不正确，将会解析失败，抛出SignatureException异常，说明该JWT字符串是伪造的
	    //在解析JWT字符串时，如果‘过期时间字段’已经早于当前时间，将会抛出ExpiredJwtException异常，说明本次请求已经失效
	    catch (SignatureException|ExpiredJwtException e) {
	    	System.out.println(Throwables.getStackTraceAsString(e));;
	        return false;
	    }
	}
	
	static class UrlBase64Coder{  
	    public final static String ENCODING = "UTF-8";  
	    
	    /**
	     * 加密  
	     * @param data
	     * @return
	     * @throws UnsupportedEncodingException
	     */
	    public static String encoded(String data) throws UnsupportedEncodingException {  
//	        return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(data.getBytes(ENCODING));  
	    	return TextCodec.BASE64URL.encode(data);
	    }  
	  
	  
	    /**
	     *  解密  
	     * @param data
	     * @return
	     * @throws UnsupportedEncodingException
	     */
	    public static String decode(String data) throws UnsupportedEncodingException {  
//	        byte[] b = org.apache.commons.codec.binary.Base64.decodeBase64(data.getBytes(ENCODING));  
//	        return new String(b, ENCODING);  
	    	return TextCodec.BASE64URL.decodeToString(data);
	    }  
	}  
	
	public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, UnsupportedEncodingException {
		DateTime dateTime = DateTime.now().plusMinutes(10);
		Date expireDate = dateTime.toDate();
		JwtTest2 test = new JwtTest2();
//		test.buildJwt();
		
		String token = test.buildJwt(expireDate);
		System.out.println(token);
		String[] tokens = token.split("\\.");
		Decoder decoder = Base64.getDecoder();
		System.out.println("HEADER:"+new String(decoder.decode(tokens[0])));
		System.out.println("PAYLOAD:"+new String(decoder.decode(tokens[1])));
		System.out.println("SIGN:"+new String(tokens[2]));
		test.isJwtValid(token);
	}
	
}
