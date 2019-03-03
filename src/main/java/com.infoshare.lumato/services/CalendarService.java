package com.infoshare.lumato.services;

import java.util.Calendar;

public class CalendarService {

    private Calendar calendar;

    public CalendarService() {
    }

    public Calendar returnCalendarDate(String input) {

        String regex = "";
        if (input.matches(regex)) {
            String[] dateArray = input.split("[/\\- ]");

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
