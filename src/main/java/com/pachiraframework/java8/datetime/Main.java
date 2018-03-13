package com.pachiraframework.java8.datetime;

import java.time.Clock;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZonedDateTime;

public class Main {
	public static void main(String[] args) {
		Clock clock = Clock.systemUTC();
		System.out.println(clock.instant());
		System.out.println(clock.millis());

		LocalDate date = LocalDate.now();
		LocalDate dateFromClock = LocalDate.now(clock);
		System.out.println(date);
		System.out.println(dateFromClock);
		LocalTime time = LocalTime.now();
		LocalTime timeFromClock = LocalTime.now(clock);
		System.out.println(time);
		System.out.println(timeFromClock);
		
		System.out.println(LocalDateTime.now());
		System.out.println(ZonedDateTime.now());
		
		//计算两个时间之间的间隔
		LocalDateTime from = LocalDateTime.of(2017, Month.APRIL, 1, 0, 0);
		LocalDateTime to = LocalDateTime.now();
		Duration duration = Duration.between(from, to);
		System.out.println(duration.toDays());
	}
}
