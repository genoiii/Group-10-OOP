/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

import Class.CSVFileManagement.CSVFileSerializer;
import Class.EntityManagement.EntityManager;
import Class.EntityManagement.EntityType;
import Class.IDManagement.IDManager;
import Class.TAT.Request;
import java.time.LocalDate;
import java.util.Arrays;
/**
 *
 * @author 63909
 */
public class TestClass {
    public static void main(String[] args){
        EntityManager requestCRUD = new EntityManager(EntityType.REQUEST);
        EntityManager overtimeCRUD = new EntityManager(EntityType.OVERTIME);
        
        String newRequestID = IDManager.generateID(requestCRUD.getEntityType().getIdPrefix());
        String newOvertimeID = IDManager.generateID(overtimeCRUD.getEntityType().getIdPrefix());
        String[] requestData = {newRequestID,newOvertimeID,"10003",LocalDate.now().toString(),"","","","",""};
        
        Request newRequest = new Request(requestData);
        System.out.print(Arrays.toString(CSVFileSerializer.toCsv(newRequest)));
        
    }
    
}
