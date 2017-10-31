package com.pachiraframework.java8.ch03;

import java.util.function.Supplier;

public class DefaultableFactory {
	public static Defaultable create(Supplier<Defaultable> supplier) {
		return supplier.get();
	}
	
	public static void main(String[] args) {
		Defaultable defaultable = DefaultableFactory.create(DefaultableImpl::new);
		System.out.println(defaultable.notRequrired());
		
		defaultable = DefaultableFactory.create(OverridableImpl::new);
		System.out.println(defaultable.notRequrired());
	}
}
