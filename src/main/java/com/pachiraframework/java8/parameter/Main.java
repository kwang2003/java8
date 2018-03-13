package com.pachiraframework.java8.parameter;

import java.lang.reflect.Method;

public class Main {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException{
		Method method = Main.class.getMethod("main",String[].class);
		System.out.println(method.getName());
		System.out.println(method.getParameters()[0].getName());
	}
}
