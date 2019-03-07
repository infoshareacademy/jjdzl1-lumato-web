package com.infoshare.lumato.services;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class CalendarService {


    public CalendarService() {
    }

    public static Calendar returnCalendarDateFromInputString(String input) {
        Calendar calendar = new GregorianCalendar();

        String regex = "(^(((0[1-9]|1[0-9]|2[0-8])[-](0[1-9]|1[012]))|((29|30|31)[-](0[13578]|1[02]))|((29|30)[-](0[4,6,9]|11)))[-](19|[2-9][0-9])\\d\\d$)|(^29[-]02[-](19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)$)\n";
        if (input.matches(regex)) {
            String[] dateArray = input.split("-");

            calendar.set(Calendar.YEAR, Integer.valueOf(dateArray[2]));
            calendar.set(Calendar.MONTH, Integer.valueOf(dateArray[1]));
            calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(dateArray[0]));
            return calendar;
        } else {
            System.out.println("======== WRONG INPUT! =========");
            return null;
        }
    }
}
