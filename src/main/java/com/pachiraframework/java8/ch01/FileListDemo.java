package com.pachiraframework.java8.ch01;

import java.io.File;
import java.io.FileFilter;

public class FileListDemo {
	public static void main(String[] args) {
		old();
		System.out.println("###############");
		java8();
	}
	
	/**
	 * 使用Java8之前的方式实现的，列举某个文件夹下的所有文件（不包含文件夹）
	 */
	public static void old() {
		File[] files = new File("c:/").listFiles(new FileFilter() {
			@Override
			public boolean accept(File f) {
				return f.isFile();
			}
		});
		print(files);
	}
	
	private static void print(File[] files) {
		for(File file : files) {
			System.out.println(file);
		}
	}
	
	public static void java8() {
		File[] files = new File("c:/").listFiles(File :: isFile);//JAVA8的特性：函数引用
		print(files);
	}
}
