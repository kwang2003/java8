package com.pachiraframework.java8.ch03;

public class ThreadDemo2 {
	public static void main(String[] args) {
		Runnable r1 = () -> System.out.println("DDDDD");
		Runnable r2 = new Runnable() {
			@Override
			public void run() {
				System.out.println("FFFFFFFFFFFF");
			}
		};
		process(r1);
		process(r2);
		process(()->System.out.println("HHHHHHHH"));
	}
	
	private static void process(Runnable runnable) {
		runnable.run();
	}
}
