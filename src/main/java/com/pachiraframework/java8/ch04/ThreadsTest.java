package com.pachiraframework.java8.ch04;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadsTest {
	public static void main(String[] args) {
		ThreadPoolExecutor executorService = new ThreadPoolExecutor(5, 200, 20L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000),Executors.defaultThreadFactory());
		while(true) {
			List<Future<?>> futures = new ArrayList<>();
			for(int i = 0;i < 5;i++) {
				Future<?> future = executorService.submit(new TestThread());
				futures.add(future);
			}
			for(Future<?> future : futures) {
				try {
					Object obj = future.get(5,TimeUnit.SECONDS);
					System.out.println(obj+"@@@@@@@@@@@@@@");
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}
			System.out.println("DONE");
		}
	}
}

class TestThread implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName()+"############");
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
}
