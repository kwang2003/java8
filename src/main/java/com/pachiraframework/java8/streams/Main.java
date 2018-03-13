package com.pachiraframework.java8.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
	private enum Status {
		CLOSED, OPEN;
	}

	private static final class Task {
		private final Status status;
		private final Integer points;

		Task(final Status status, final Integer points) {
			this.status = status;
			this.points = points;
		}

		public Integer getPoints() {
			return points;
		}

		public Status getStatus() {
			return status;
		}

		@Override
		public String toString() {
			return String.format("[%s, %d]", status, points);
		}
	}

	public static void main(String[] args) {
		final Collection<Task> tasks = Arrays.asList(new Task(Status.OPEN, 5), new Task(Status.OPEN, 13),
				new Task(Status.CLOSED, 8));
		int sum = tasks.stream().filter(task -> task.getStatus().equals(Status.OPEN)).mapToInt(Task::getPoints).sum();
		System.out.println(sum);
		
		//并行处理
		int totalPoints = tasks.stream().parallel().map(Task::getPoints).reduce(0, Integer::sum);
		System.out.println( "Total points (all tasks): " + totalPoints );
		
		//分组
		Map<Status, List<Task>> map = tasks.stream().collect(Collectors.groupingBy(Task::getStatus ));
		System.out.println( map );
	}
}
