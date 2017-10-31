package com.pachiraframework.java8.ch01;

public class ThreadDemo {
	public static void main(String[] args) {
		Thread t = new Thread(()->System.out.println("ddddd"));
		t.start();
		System.out.println("#######");
	}
}
