package com.automobile.test;

import java.time.Duration;
import java.time.LocalDateTime;

public class Test {

	public static void main(String[] args) {
		LocalDateTime from = LocalDateTime.of(2019, 11, 25, 3, 20);
		LocalDateTime to = LocalDateTime.of(2019, 11, 25, 15, 50);
		Duration d = Duration.between(from, to);
		long absSeconds = Math.abs(d.getSeconds());
			String positive = String.format(
			        "%d:%02d",
			        absSeconds / 3600,
			        (absSeconds % 3600) / 60);
//		System.out.println(d.getSeconds()/3600);
		System.out.println(positive);
		
	}
}
