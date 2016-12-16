package com.zylon.soft.test;

import java.util.Date;

public class TimeFormate {
public static void main(String[] args) {
	String dateString = "2016-10-19T08:00:16.000+0000";
	Date date = new Date(dateString);
	System.out.println(date);
}
}
