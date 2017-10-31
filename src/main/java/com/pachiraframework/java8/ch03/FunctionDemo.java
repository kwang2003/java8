package com.pachiraframework.java8.ch03;

import java.util.List;
import java.util.function.Function;

import com.google.common.collect.Lists;

public class FunctionDemo {
	public static void main(String[] args) {
		Function<String, Integer> f = (String s)->Integer.valueOf(s);
		List<String> list = Lists.newArrayList("1","2");
		List<Integer> result = map(list, f);
		System.out.println(result);
	}
	
	private static <T,R>  List<R> map(List<T> list,Function<T,R> f){
		List<R> result = Lists.newArrayList();
		for(T t : list) {
			R r = f.apply(t);
			result.add(r);
		}
		return result;
	}
}
