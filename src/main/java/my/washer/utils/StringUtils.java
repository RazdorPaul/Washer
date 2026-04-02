package main.java.my.washer.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class StringUtils {
    public static String timeToString() {
        var hour = LocalDateTime.now();
        var munute = LocalDateTime.now().getMinute();
        return hour + ":" + munute;
    }
    public static String dateToString() {
        var month = LocalDate.now().getMonth().toString();
        var day = LocalDate.now().getDayOfMonth();
        var year = LocalDate.now().getYear();
        return month + ", " + year + ", " + day;
    }

}