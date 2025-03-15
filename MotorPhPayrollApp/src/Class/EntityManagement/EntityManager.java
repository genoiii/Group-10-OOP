/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.EntityManagement;

import Class.CSVFileManagement.CSVFileSerializer;

/**
 *
 * @author 63909
 */
public class EntityManager  {
    private final EntityType entityType;

    public EntityManager(EntityType entityType) {
        this.entityType = entityType;
    }
    
     // Create a new record: generate ID, convert to CSV record, and append to file.
    public <T> void addEntityRecord(T newObject) throws Exception { 
        String[] record = CSVFileSerializer.toCsv(newObject);
        entityType.getCsvFile().appendFile(record);
    }
    
//    public <T> void saveEntityRecord(T updatedObject) throws Exception {
//        List<T> objectList = entityType.getCsvFile()readFile(T::new);
//        
//        for (int i = 0; i < objectList.size(); i++) {
//            if (objectList.get(i).getID().equals(updatedObject.getID())) {
//                objectList.set(i, updatedObject);
//                break;
//            }
//        }
//        
//        // Convert the updated employee list to a List of String arrays.
//        List<T> updatedEmployeeRecord = new ArrayList<>();
//        for (T object : objectList) {
//            updatedEmployeeRecord.add(CSVFileSerializer.toCsv(object));
//        }
//        
//        entityType.getCsvFile().writeFile(updatedEmployeeRecord);
//    }  
        
    public EntityType getEntityType() {
        return entityType;
    }
}
