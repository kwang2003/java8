package com.pachiraframework.java8.ch03;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
	public static void main(String[] args) throws IOException {
		String oneLine = processFile((BufferedReader br)->br.readLine());
		String twoLine = processFile((BufferedReader br)->br.readLine()+br.readLine());
		System.out.println(oneLine);
		System.out.println("#################################");
		System.out.println(twoLine);
	}

	private static String processFile(BufferedReaderProcessor processor) throws IOException {
		BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("c:/tmp.log")));
		return processor.process(bufferedReader);
	}
	@FunctionalInterface
	private static interface BufferedReaderProcessor{
		String process(BufferedReader b)throws IOException;
	}
}

