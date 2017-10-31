package com.pachiraframework.java8.ch03;

import java.util.Base64;

public class Base64s {
	public static void main(String[] args) {
		String text = "hello world";
		String encoded = Base64.getEncoder().encodeToString(text.getBytes());
		System.out.println(encoded);
		
		String decoded = new String(Base64.getDecoder().decode(encoded.getBytes()));
		System.out.println(decoded);
	}
}
