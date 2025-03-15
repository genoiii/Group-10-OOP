/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class.TAT;

import CSVFileManager.CsvFile;
import Class.CollectionUtils;
import java.util.List;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author 63909
 */
public class LeaveService {
    private List<LeaveType> leaveTypeList;
    private Map<String, LeaveType> leaveMapByLeaveTypeID;

    public LeaveService() {
        this.leaveTypeList = CsvFile.LEAVETYPE.readFile(LeaveType::new);
        
        this.leaveMapByLeaveTypeID = CollectionUtils.listToMap(leaveTypeList, LeaveType::getID);
    }
    
    public DefaultComboBoxModel<String> getLeaveTypeComboBoxModel(){
        // Convert payPeriodList to a String[]
        String[] leaveTypeArray = leaveTypeList.stream()
            .map(leaveType -> leaveType.getLeaveTypeName())
            .toArray(String[]::new); // Proper way to get a String[]

        return new DefaultComboBoxModel<String>(leaveTypeArray); // Use DefaultComboBoxModel
    }
    
}
