/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import com.opencsv.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author 63909
 */
public class File {
    private String fileName;
    private String filePath;
    private ArrayList<String[]> dataFile = null;
    private BufferedReader reader = null;
    private BufferedWriter writer = null;
    private CSVWriter csvWriter = null;

    public String getFileName() {
        return fileName;
    }
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<String[]> readFile(String filePath){
        this.filePath = filePath ;            
        dataFile = new ArrayList<String[]>();
        String line = "";

        try {
            reader = new BufferedReader(new FileReader(this.filePath));

            while((line = reader.readLine()) != null){
                String[] row = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1);
                for (int j = 0; j < row.length; j++) {
                    row[j] = row[j].trim();
                    row[j] = row[j].replace("\"", "");
                }
                dataFile.add(row);
            }
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
               reader.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }    
        }            

        return dataFile;
    }
    
    public void writeFile(String filePath, ArrayList<String[]> dataFile) {
        this.filePath = filePath;
        this.dataFile = dataFile;
        String row = "";
        
        try {
            csvWriter = new CSVWriter(new FileWriter(filePath));
            
            for (String[] line : this.dataFile) {
//                row = String.join(",", line);
//                row = row.substring(1, row.length() - 1);
//                writer.write(row);
//                writer.write("\n");
                  csvWriter.writeNext(line);
            }
            
        }catch(Exception e) {
            e.printStackTrace();
        }finally {
            try {
               csvWriter.close(); 
            } catch (IOException e) {
                e.printStackTrace();
            }    
        }
    }
}
