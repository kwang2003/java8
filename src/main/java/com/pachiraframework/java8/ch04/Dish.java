package com.pachiraframework.java8.ch04;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Dish {
	private String name;
	private boolean vegetarian;
	private int categories;
	private Type type;
	public enum Type { /**
	 * 肉
	 */
	MEAT, /**
	 * 鱼
	 */
	FISH, /**
	 * 其他 
	 */
	OTHER }
}
