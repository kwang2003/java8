package com.pachiraframework.java8.ch04;

import static java.util.stream.Collectors.toList;
import java.util.List;

import com.google.common.collect.Lists;

public class StreamDemo {
	public static void main(String[] args) {
		List<Dish> menus = Lists.newArrayList(
				new Dish("pork", false, 800, Dish.Type.MEAT),
				new Dish("beef", false, 700, Dish.Type.MEAT),
				new Dish("chicken", false, 400, Dish.Type.MEAT),
				new Dish("french fries", true, 530, Dish.Type.OTHER),
				new Dish("rice", true, 350, Dish.Type.OTHER),
				new Dish("season fruit", true, 120, Dish.Type.OTHER),
				new Dish("pizza", true, 550, Dish.Type.OTHER),
				new Dish("prawns", false, 300, Dish.Type.FISH),
				new Dish("salmon", false, 450, Dish.Type.FISH));
		List<String> threeHighCaloricDishNames = menus.stream().filter(d->d.getCategories()>300).map(Dish::getName).collect(toList());
		System.out.println(threeHighCaloricDishNames);
	}
}
