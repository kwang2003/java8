package com.pachiraframework.java8.ch03;

public interface Defaultable {
	default String notRequrired() {
		return "默认实现";
	}
}
