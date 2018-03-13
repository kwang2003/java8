package com.pachiraframework.java8.lambda;

import java.util.Arrays;

/**
 * Lambda表达式和函数式接口
 * @author KevinWang
 *
 */
public class Main {
	public static void main(String[] args) {
		System.out.println("##############################################################################");
		// 编译器推断a的类型
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).forEach(a -> System.out.println(a));
		// 显式指定数据类型
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).forEach((Integer a) -> System.out.println(a));
		// 复杂处理模块
		Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8).forEach((Integer a) -> {
			System.out.println(a);
			System.out.println("**");
		});

		// lambda表达式引用局部变量,局部变量会被提升为final的，如果后面的代码对separator进行修改，编译报错
		System.out.println("##############################################################################");
		String separator = ",";
		Arrays.asList(1, 2, 3).forEach(a -> System.out.println(a + separator));

		// lambda表达式返回值
		System.out.println("##############################################################################");
		Arrays.asList(1,2,3,4,5).sort((e1,e2) -> e1.compareTo(e2));
	}
	
	/**
	 * 函数接口，使用@FunctionalInterface注解进行标识，该接口是非常脆弱的，只能有一个方法，一旦接口中添加了新的方法，就会编译失败，但是接口中的默认方法除外
	 * @author KevinWang
	 *
	 */
	@FunctionalInterface
	private interface Functional{
		void method();
		
		default void defaultMethod() {
			
		}
	}
}
