package com.pachiraframework.java8.base64;

import java.util.Base64;

public class Main {
	public static void main(String[] args) {
		String text = "Java8 is perfect 中文!";
		String encoded = Base64.getEncoder().encodeToString(text.getBytes());
		System.out.println(encoded);
		String decoded = new String(Base64.getDecoder().decode(encoded.getBytes()));
		System.out.println(decoded);
		
	}
	
}
