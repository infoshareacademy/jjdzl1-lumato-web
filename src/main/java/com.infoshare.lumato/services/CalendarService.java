package com.infoshare.lumato.services;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarService {


    public CalendarService() {
    }

    public static Calendar returnCalendarDate(String input) {
         Calendar calendar = new GregorianCalendar();

        String regex = "\\d{4}-\\d{2}-\\d{2}";
        if (input.matches(regex)) {
            String[] dateArray = input.split("/");

            calendar.set(Calendar.YEAR, Integer.valueOf(dateArray[0]));
            calendar.set(Calendar.MONTH, Integer.valueOf(dateArray[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateArray[2]));
            return calendar;
        } else {
            System.out.println("======== WRONG INPUT! =========");
            return null;
        }
    }
}
