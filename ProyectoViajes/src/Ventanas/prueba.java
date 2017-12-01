package Ventanas;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.toedter.calendar.JCalendar;

public class prueba {
	
	public static void main(String[] args) {
		Date day = new Date();
		SimpleDateFormat s= new SimpleDateFormat("MMMMMMMMMM");
		System.out.println(s.format(day));
	}

}
