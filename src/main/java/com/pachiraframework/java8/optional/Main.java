package com.pachiraframework.java8.optional;

import java.util.Optional;

public class Main {
	public static void main(String[] args) {
		Optional<String> optional = Optional.ofNullable("Tom");
		System.out.println("Full name is set?" + optional.isPresent());
		System.out.println("Full name is " + optional.get());
		System.out.println(optional.map(s -> "Hey "+optional.get()).orElse("Hey nobody"));
	}
}
