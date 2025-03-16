/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author 63909
 */
public class Parser {
    // List of common date formats
    private static final List<String> DATE_FORMATS = Arrays.asList(
        "M/d/yyyy",                         // 2/1/2025
        "MM/dd/yyyy",                       // 02/01/2025
        "yyyy-MM-dd",                       // 2025-02-01
        "dd-MM-yyyy",                       // 01-02-2025
        "d-M-yyyy",                         // 1-2-2025
        "yyyy-MM-dd",                       // 2025-02-01
        "EEE MMM dd HH:mm:ss zzz yyyy",     // Fri Dec 18 00:00:00 CST 1970 (With timezone)
        "EEE MMM dd HH:mm:ss yyyy"          // Fri Dec 18 00:00:00 1970 (Without timezone)
    );

    // Parse Integer
    public static Integer parseInteger(String value, Integer defaultValue) {
        try {
            return (value != null && !value.trim().isEmpty()) ? Integer.valueOf(value.trim()) : defaultValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid integer format: '" + value + "'", e);
        }
    }

    // Parse Double
    public static Double parseDouble(String value, Double defaultValue) {
        try {
            return (value != null && !value.trim().isEmpty()) ? Double.valueOf(value.trim()) : defaultValue;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid double format: '" + value + "'", e);
        }
    }
    
    public static Date parseDate(String value, Date defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }

        for (String pattern : DATE_FORMATS) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern, Locale.ENGLISH);
                sdf.setLenient(false); // Ensures strict date parsing
                return sdf.parse(value.trim());
            } catch (ParseException ignored) {
                // Try the next format
            }
        }

        throw new IllegalArgumentException("Invalid date format: '" + value + "'. Supported formats: " + DATE_FORMATS);
    }
    
    public static LocalDate parseLocalDate(String value, LocalDate defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }

        for (String pattern : DATE_FORMATS) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
                return LocalDate.parse(value.trim(), formatter);
            } catch (DateTimeParseException ignored) {
                // Try the next format
            }
        }

        throw new IllegalArgumentException("Invalid date format: '" + value + "'. Supported formats: " + DATE_FORMATS);
    }

//    // Parse LocalDate (Default ISO Format)
//    public static LocalDate parseLocalDate(String value, LocalDate defaultValue) {
//        try {
//            return (value != null && !value.trim().isEmpty()) ? LocalDate.parse(value.trim()) : defaultValue;
//        } catch (DateTimeParseException e) {
//            throw new IllegalArgumentException("Invalid date format: '" + value + "'", e);
//        }
//    }

    // Parse LocalDate with Custom Format
    public static LocalDate parseLocalDate(String value, LocalDate defaultValue, String pattern) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            return (value != null && !value.trim().isEmpty()) ? LocalDate.parse(value.trim(), formatter) : defaultValue;
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format: '" + value + "' (Expected format: " + pattern + ")", e);
        }
    }
    
    public static LocalTime parseLocalTime(String value, LocalTime defaultValue, String pattern) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }

        try {
            DateTimeFormatter formatter = (pattern != null && !pattern.isEmpty()) 
                ? DateTimeFormatter.ofPattern(pattern) 
                : DateTimeFormatter.ISO_LOCAL_TIME; // Default to "HH:mm:ss"

            return LocalTime.parse(value.trim(), formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid time format: '" + value + "' (Expected format: " + pattern + ")", e);
        }
    }

    // Generic Object Parser (using functional interface)
    public static <T> T parseValue(String value, T defaultValue, ParserFunction<String, T> parserFunction) {
        try {
            return (value != null && !value.trim().isEmpty()) ? parserFunction.apply(value.trim()) : defaultValue;
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid value format: '" + value + "'", e);
        }
    }

    // Functional interface for generic parsing
    @FunctionalInterface
    public interface ParserFunction<T, R> {
        R apply(T t) throws Exception;
    }
}