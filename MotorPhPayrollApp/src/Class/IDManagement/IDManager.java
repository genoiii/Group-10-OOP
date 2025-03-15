/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.IDManagement;

import CSVFileManager.CsvFile;
import Class.CollectionUtils;
import java.util.*;
/**
 *
 * @author 63909
 */
public class IDManager {
    private static final List<String[]> idPrefixList = CsvFile.IDCOUNTERS.readFile(data -> data);
    private static final Map<String, String[]> idCounters = CollectionUtils.listToMap(idPrefixList, data -> data[0]);

     // ✅ Generate next ID for a given prefix
    public static String generateID(IDPrefix prefix) {
        String key = prefix.getPrefix();

        // Get the record from the map (default to a new entry if missing)
        String[] record = idCounters.getOrDefault(key, new String[]{key, "0"});
        
        // Ensure record is stored if it was just initialized
        idCounters.putIfAbsent(key, record);

        // Convert the counter value to an integer, defaulting to 0 if empty
        int currentCounter = record[1].isEmpty() ? 0 : Integer.parseInt(record[1]);

        // Increment the counter
        int nextID = currentCounter + 1;
        record[1] = String.valueOf(nextID);
        // Format the ID (e.g., EMP00001)
        return key + String.format("%05d", nextID);
    }

    // ✅ Save updated counters back to CSV
    public static void saveIDCounters() {
        List<String[]> updatedRecords = idCounters.values().stream().toList();
        CsvFile.IDCOUNTERS.writeFile(updatedRecords);
    }
}
