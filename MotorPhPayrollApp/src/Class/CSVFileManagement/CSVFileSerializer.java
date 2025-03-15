/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.CSVFileManagement;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;

/**
 *
 * @author 63909
 */
public class CSVFileSerializer {
    
    public static <T> String[] toCsv(T objects){
        Class<T> clazz = (Class<T>) objects.getClass();
        Field[] fields = clazz.getDeclaredFields();
        
        return Arrays.stream(fields)
            .filter(field -> !Modifier.isTransient(field.getModifiers())) // Skip transient fields
            .map(field -> {
                try {
                    field.setAccessible(true);
                    Object value = field.get(objects);
                    return escapeCsvField(value != null ? value.toString() : "");
                } catch (IllegalAccessException e) {
                    return "";
                }
            })
            .toArray(String[]::new);
    }
    
    private static String escapeCsvField(String field) {
        if (field.contains(",") || field.contains("\"") || field.contains("\n")) {
            return "\"" + field.replace("\"", "\"\"") + "\"";
        }
        return field;
    }    
}
