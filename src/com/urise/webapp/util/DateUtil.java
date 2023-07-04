package com.urise.webapp.util;

import java.time.LocalDate;
import java.time.Month;

public class DateUtil {
    public static LocalDate of(int year, Month month, int day) {
        return LocalDate.of(year, month,1);
    }
}
