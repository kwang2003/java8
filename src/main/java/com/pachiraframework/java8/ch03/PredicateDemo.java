package com.pachiraframework.java8.ch03;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import com.google.common.collect.Lists;

public class PredicateDemo {
	public static void main(String[] args) {
		List<String> list = Lists.newArrayList("abc","","def");
		Predicate<String> nonEmptyStringPredicate = (String s)->!s.isEmpty();
		List<String> result = filter(list, nonEmptyStringPredicate);
		System.out.println(result);
	}
	private static <T> List<T> filter(List<T> list,Predicate<T> predicate){
		List<T> result = new ArrayList<T>();
		for(T t : list) {
			if(predicate.test(t)) {
				result.add(t);
			}
		}
		return result;
	}
}
