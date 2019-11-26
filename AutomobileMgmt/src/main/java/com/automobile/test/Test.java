package com.automobile.test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;

public class Test {

	public static void main(String[] args) {

		LocalDate ld = LocalDate.of(2019, 11, 6);
		LocalDate ld2 = LocalDate.of(2019, 11, 23 + 1);
		Period p = Period.between(ld, ld2);
		long size = p.getDays();
		System.out.println(size);
		int workingDaysInAMonth = 0;
		while (size != 0) {
			if ((ld.getDayOfWeek()).equals(DayOfWeek.SATURDAY) || (ld.getDayOfWeek()).equals(DayOfWeek.SUNDAY)) {
				size--;
				ld = ld.plusDays(1);
				continue;
			}
			workingDaysInAMonth++;
			ld = ld.plusDays(1);
			size--;
		}
		System.out.println(workingDaysInAMonth);
	}

}
