package com.automobile.test;

import java.time.LocalDate;
import java.time.Period;

public class Test {

	public static void main(String[] args) {

		LocalDate ld = LocalDate.of(2012, 12, 31);
		LocalDate ld2 = LocalDate.of(2013, 1, 1);
		long diff = ld.getDayOfYear() - ld2.getDayOfYear();
		System.out.println(diff);
 		Period p = Period.between(ld, ld2);
		System.out.println(p.getDays());
		System.out.println(p.getMonths());
		System.out.println(p.getChronology());
		
	}

}
