package com.pachiraframework.java8.ch03;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerDemo {
	public static void main(String[] args) {
		forEach(Arrays.asList(1,2,3,5,6), (Integer i)->System.out.println("DDDD"+i));
	}
	
	public static <T> void forEach(List<T> list,Consumer<T> consumer) {
		for(T t : list) {
			consumer.accept(t);
		}
	}
}
