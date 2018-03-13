package com.pachiraframework.java8.defaults;

import java.util.function.Supplier;

/**
 *  接口的默认方法和静态方法
 * @author KevinWang
 *
 */
public class Main {
	public static void main(String[] args) {
		Defaultable defaultable = DefaultableFactory.create(DefaultableImpl::new);
		System.out.println(defaultable.notRequired());
		
		defaultable = DefaultableFactory.create(OverridableDefaultableImpl::new);
		System.out.println(defaultable.notRequired());
	}

	/**
	 * 接口默认方法
	 * 
	 * @author KevinWang
	 *
	 */
	private static interface Defaultable {
		default String notRequired() {
			return "Default implementation";
		}
	}

	private static class DefaultableImpl implements Defaultable {
	}

	private static class OverridableDefaultableImpl implements Defaultable {
		@Override
		public String notRequired() {
			return "Overridden implementation";
		}
	}

	private static interface DefaultableFactory {
		static Defaultable create(Supplier<Defaultable> creator) {
			return creator.get();
		}
	}
}
