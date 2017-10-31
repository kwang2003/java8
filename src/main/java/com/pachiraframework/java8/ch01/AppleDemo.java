package com.pachiraframework.java8.ch01;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import lombok.AllArgsConstructor;
import lombok.Data;

public class AppleDemo {
	public static void main(String[] args) {
		List<Apple> invertory = appleInventory();
		List<Apple> greenApples = filterGreenApples(invertory);
		print(greenApples);
		System.out.println("#################");
		greenApples = filterGreenApples2(invertory, Apple :: isGreen);
		print(greenApples);
		System.out.println("*****************");
		sort(greenApples);
		print(greenApples);
	}
	
	private static void sort(List<Apple> apples) {
		apples.sort((Apple a1,Apple a2)->a1.getWeight().compareTo(a2.getWeight()));
	}
	
	private static void print(List<Apple> apples) {
		for(Apple apple : apples) {
			System.out.println(apple);
		}
	}
	/**
	 * 使用java8实现的
	 * @return
	 */
	private static List<Apple> filterGreenApples2(List<Apple> invertory,Predicate<Apple> predicate){
		List<Apple> result = Lists.newArrayList();
		for(Apple apple : invertory) {
			if(predicate.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	/**
	 * 采用传统方法过滤苹果列表
	 * @return
	 */
	private static List<Apple> filterGreenApples(List<Apple> invertory){
		List<Apple> result = Lists.newArrayList();
		for(Apple apple : invertory) {
			if(apple.getColor().equals("green")) {
				result.add(apple);
			}
		}
		return result;
	}
	
	private static List<Apple> appleInventory(){
		List<Apple> invertory = Lists.newArrayList();
		String[] colors = new String[] {"red","green"};
		Random random = new Random();
		for(int i =0;i < 10;i++) {
			Apple apple = new Apple(colors[random.nextInt(2)], random.nextInt(500));
			invertory.add(apple);
		}
		return invertory;
	}
	@Data
	@AllArgsConstructor
	private static class Apple{
		private String color;
		private Integer weight;
		
		private boolean isGreen() {
			return "green".equals(this.color);
		}
//		
//		private boolean isHeavyApple() {
//			return 150 > this.getWeight();
//		}
	}
	
	private interface Predicate<T>{
		boolean test(T t);
	}
}
