package com.pachiraframework.java8.parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
	public static void main(String[] args) {
		long[] arrayOfLong = new long [ 20000 ];        
		Arrays.parallelSetAll(arrayOfLong, index -> ThreadLocalRandom.current().nextLong(1000000 ));
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.println(i+" "));
		System.out.println();
		Arrays.parallelSort(arrayOfLong);
		Arrays.stream(arrayOfLong).limit(10).forEach(i -> System.out.println(i+" "));
	}
}
