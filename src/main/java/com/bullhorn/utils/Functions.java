package com.bullhorn.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Functions {

    public static Date AddDays(Date givenDate, int numOfDays) {
        Date returnDate = null;
        try {
            Calendar c = Calendar.getInstance();
            c.setTime(givenDate);
            c.add(Calendar.DATE, numOfDays);
            returnDate = c.getTime();
        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }
        return returnDate;
    }

    public static Date GetDateForWeekDay(Date endingDate, String weekDayString) {
        Date newDate = endingDate;
        int offSetDays = 0;
        try {
            int endingDay = endingDate.getDay();
            if (weekDayString.toUpperCase().equals("MON"))
                offSetDays = (1 - endingDay);
            else if (weekDayString.toUpperCase().equals("TUE"))
                offSetDays = 2 - endingDay;
            else if (weekDayString.toUpperCase().equals("WED"))
                offSetDays = 3 - endingDay;
            else if (weekDayString.toUpperCase().equals("THR"))
                offSetDays = 4 - endingDay;
            else if (weekDayString.toUpperCase().equals("FRI"))
                offSetDays = 5 - endingDay;
            else if (weekDayString.toUpperCase().equals("SAT"))
                offSetDays = 6 - endingDay;
            else if (weekDayString.toUpperCase().equals("SUN"))
                offSetDays = 7 - endingDay;
            else
                System.out.println("Day String " + weekDayString + " is wrong");

            if (offSetDays > 0) offSetDays = (offSetDays - 7);

            newDate = Functions.AddDays(endingDate, offSetDays);

        } catch (IndexOutOfBoundsException e) {
            System.err.println("Caught IOException: " + e.getMessage());
        }

        return newDate;
    }

    public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static Map<String, String> states = new HashMap<String, String>();

    static {
        states.put("alabama", "AL");
        states.put("alaska", "AK");
        states.put("alberta", "AB");
        states.put("american samoa", "AS");
        states.put("arizona", "AZ");
        states.put("arkansas", "AR");
        states.put("armed forces (ae)", "AE");
        states.put("armed forces americas", "AA");
        states.put("armed forces pacific", "AP");
        states.put("british columbia", "BC");
        states.put("california", "CA");
        states.put("colorado", "CO");
        states.put("connecticut", "CT");
        states.put("delaware", "DE");
        states.put("district of columbia", "DC");
        states.put("florida", "FL");
        states.put("georgia", "GA");
        states.put("guam", "GU");
        states.put("hawaii", "HI");
        states.put("idaho", "ID");
        states.put("illinois", "IL");
        states.put("indiana", "IN");
        states.put("iowa", "IA");
        states.put("kansas", "KS");
        states.put("kentucky", "KY");
        states.put("louisiana", "LA");
        states.put("maine", "ME");
        states.put("manitoba", "MB");
        states.put("maryland", "MD");
        states.put("massachusetts", "MA");
        states.put("michigan", "MI");
        states.put("minnesota", "MN");
        states.put("mississippi", "MS");
        states.put("missouri", "MO");
        states.put("montana", "MT");
        states.put("nebraska", "NE");
        states.put("nevada", "NV");
        states.put("new brunswick", "NB");
        states.put("new hampshire", "NH");
        states.put("new jersey", "NJ");
        states.put("new mexico", "NM");
        states.put("new york", "NY");
        states.put("newfoundland", "NF");
        states.put("north carolina", "NC");
        states.put("north dakota", "ND");
        states.put("northwest territories", "NT");
        states.put("nova scotia", "NS");
        states.put("nunavut", "NU");
        states.put("ohio", "OH");
        states.put("oklahoma", "OK");
        states.put("ontario", "ON");
        states.put("oregon", "OR");
        states.put("pennsylvania", "PA");
        states.put("prince edward island", "PE");
        states.put("puerto rico", "PR");
        states.put("quebec", "QC");
        states.put("rhode island", "RI");
        states.put("saskatchewan", "SK");
        states.put("south carolina", "SC");
        states.put("south dakota", "SD");
        states.put("tennessee", "TN");
        states.put("texas", "TX");
        states.put("utah", "UT");
        states.put("vermont", "VT");
        states.put("virgin islands", "VI");
        states.put("virginia", "VA");
        states.put("washington", "WA");
        states.put("west virginia", "WV");
        states.put("wisconsin", "WI");
        states.put("wyoming", "WY");
        states.put("yukon territory", "YT");
    }

    public static String getStateCode(String s) {
        String stateCode = "";
        try {
            s = s.trim().toLowerCase();
            if (states.get(s) != null)
                stateCode = states.get(s);
        } catch (Exception e) {
            stateCode = s;
        }

        if (stateCode.length() < 1)
            stateCode = s;
        return stateCode;
    }

}
