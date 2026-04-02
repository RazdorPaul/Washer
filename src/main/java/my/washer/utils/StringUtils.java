package main.java.my.washer.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class StringUtils {
    public static String timeToString() {
        var hour = LocalDateTime.now();
        var minute = LocalDateTime.now().getMinute();
        return hour + ":" + minute;
    }
    public static String dateToString() {
        var month = LocalDate.now().getMonth().toString();
        var day = LocalDate.now().getDayOfMonth();
        var year = LocalDate.now().getYear();
        return month + ", " + year + ", " + day;
    }
    public static String timeIntegerToString() {
        var month = LocalDate.now().getMonth().getValue();
        var day = LocalDate.now().getDayOfMonth();
        var year = LocalDate.now().getYear();
        var hour = LocalDateTime.now().getHour();
        var minute = LocalDateTime.now().getMinute();
        var second = LocalDateTime.now().getSecond();
        List<Integer> res = List.of(year, month, day, hour, minute, second);
        return res.stream()
                .map(num -> {
                    var str = num.toString();
                    if (num < 10) {
                        str = "0" + str;
                    }
                    return str;
                })
                .reduce("", (str, integer) -> str + integer);
    }
}