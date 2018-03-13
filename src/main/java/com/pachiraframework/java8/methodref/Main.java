package com.pachiraframework.java8.methodref;

import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;

/**
 * 方法引用
 * 
 * @author KevinWang
 *
 */
public class Main {
	public static void main(String[] args) {
		// 第一种引用方式：构造器引用，语法是Car::new，或者是更一般的形式Class<T>::new，注意，这个构造器没有参数
		Car car1 = Car.create(Car::new);
		List<Car> cars = Arrays.asList(car1);
		// 第二种方法是静态方法引用，语法是Class::static_method，注意这个方法接收一个car类型的参数
		cars.forEach(Car::collide);
		// 第三种方法是接收某个类成员方法的引用,注意，这个方法没有参数
		cars.forEach(Car::repair);
		// 第四种方法是接收一个实例的成员方法引用，语法是instance::method。注意：这个方法接受一个Car类型的参数：
		Car police = Car.create(Car::new);
		cars.forEach(police::follow);

	}

	public static class Car {
		public static Car create(Supplier<Car> supplier) {
			return supplier.get();
		}

		public static void collide(final Car car) {
			System.out.println("Collided " + car.toString());
		}

		public void follow(final Car another) {
			System.out.println("Following the " + another.toString());
		}

		public void repair() {
			System.out.println("Repaired " + this.toString());
		}
	}

}
