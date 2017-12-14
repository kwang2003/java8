package com.pachiraframework.java8;

/**
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * 
 * @author wangxuzheng
 *
 */
public class MinorGC {
	private static final int _1M = 1024 * 1024;

	public static void main(String[] args) {
		testAllocation();
	}

	private static void testAllocation() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[2 * _1M];
		allocation2 = new byte[2 * _1M];
		allocation3 = new byte[2 * _1M];
		allocation4 = new byte[4 * _1M];//发生一次 minor gc
	}
}
