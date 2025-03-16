/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

import CSVFileManager.*;
import Class.EMS.*;
import Class.UMS.*;
import java.util.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Charm
 */
public class EmployeeInformation extends javax.swing.JFrame {
    List<Employee> employeeList = CsvFile.EMPLOYEEINFORMATION.readFile(Employee::new);            
    Admin admin;    
    EmployeeService employeeService = new EmployeeService();
    InformationService informationService =  new InformationService();
    
    /**
     * Creates new form EmployeeInformation
     * Initializes the frame and populates the employee table.
     */       
    public EmployeeInformation() { 
        initComponents();
//        jTable1EmployeeList.setModel(employeeService.getEmployeeTableModel()); // Populate table with employee data
        jTable1EmployeeList.setModel(informationService.getEmployeeInformationTableModel()); // Populate table with employee data
    }
    
    /**
     * Constructor that takes an admin user as a parameter.
     * This could be used to manage role-based access control in the future.
     *
     * @param admin The logged-in admin userS
     */
    public EmployeeInformation(Admin admin) {
        initComponents();
        this.admin = admin;
        admin.addLogoutListener(this);
        
//        jTable1EmployeeList.setModel(employeeService.getEmployeeTableModel()); // Populate table with employee data
        jTable1EmployeeList.setModel(informationService.getEmployeeInformationTableModel()); // Populate table with employee data
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1EmployeeInformation = new javax.swing.JButton();
        jButton3EmployeeRequest = new javax.swing.JButton();
        jButton4Payroll = new javax.swing.JButton();
        jButton6LogOut = new javax.swing.JButton();
        jButton3SelfServicePortal = new javax.swing.JButton();
        jButton4Attendance = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1EmployeeList = new javax.swing.JTable();
        jButton1AddNewRecord = new javax.swing.JButton();
        jButton1ViewEmployeeDetails = new javax.swing.JButton();
        jButton2DeleteEmployeeRecord = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 23, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jButton1EmployeeInformation.setBackground(new java.awt.Color(0, 102, 153));
        jButton1EmployeeInformation.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton1EmployeeInformation.setForeground(new java.awt.Color(255, 255, 255));
        jButton1EmployeeInformation.setText("Employee Information");
        jButton1EmployeeInformation.setBorder(null);
        jButton1EmployeeInformation.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1EmployeeInformationActionPerformed(evt);
            }
        });

        jButton3EmployeeRequest.setBackground(new java.awt.Color(0, 102, 153));
        jButton3EmployeeRequest.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3EmployeeRequest.setForeground(new java.awt.Color(255, 255, 255));
        jButton3EmployeeRequest.setText("Employee Request");
        jButton3EmployeeRequest.setBorder(null);
        jButton3EmployeeRequest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3EmployeeRequestActionPerformed(evt);
            }
        });

        jButton4Payroll.setBackground(new java.awt.Color(0, 102, 153));
        jButton4Payroll.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4Payroll.setForeground(new java.awt.Color(255, 255, 255));
        jButton4Payroll.setText("Payroll");
        jButton4Payroll.setBorder(null);
        jButton4Payroll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4PayrollActionPerformed(evt);
            }
        });

        jButton6LogOut.setBackground(new java.awt.Color(0, 102, 153));
        jButton6LogOut.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton6LogOut.setForeground(new java.awt.Color(255, 255, 255));
        jButton6LogOut.setText("Log Out");
        jButton6LogOut.setBorder(null);
        jButton6LogOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6LogOutActionPerformed(evt);
            }
        });

        jButton3SelfServicePortal.setBackground(new java.awt.Color(0, 102, 153));
        jButton3SelfServicePortal.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3SelfServicePortal.setForeground(new java.awt.Color(255, 255, 255));
        jButton3SelfServicePortal.setText("Self Service Portal");
        jButton3SelfServicePortal.setBorder(null);
        jButton3SelfServicePortal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3SelfServicePortalActionPerformed(evt);
            }
        });

        jButton4Attendance.setBackground(new java.awt.Color(0, 102, 153));
        jButton4Attendance.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton4Attendance.setForeground(new java.awt.Color(255, 255, 255));
        jButton4Attendance.setText("Attendance");
        jButton4Attendance.setBorder(null);
        jButton4Attendance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4AttendanceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1EmployeeInformation, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3EmployeeRequest, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Payroll, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton6LogOut, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton3SelfServicePortal, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Attendance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jButton3SelfServicePortal)
                .addGap(30, 30, 30)
                .addComponent(jButton3EmployeeRequest)
                .addGap(30, 30, 30)
                .addComponent(jButton1EmployeeInformation)
                .addGap(30, 30, 30)
                .addComponent(jButton4Payroll)
                .addGap(30, 30, 30)
                .addComponent(jButton4Attendance)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton6LogOut)
                .addContainerGap())
        );

        jTable1EmployeeList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Employee ID", "Last Name", "First Name", "Birthdate", "Address", "Phone Number"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1EmployeeList.setShowGrid(true);
        jTable1EmployeeList.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1EmployeeList);
        jTable1EmployeeList.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1EmployeeList.getColumnModel().getColumnCount() > 0) {
            jTable1EmployeeList.getColumnModel().getColumn(0).setResizable(false);
            jTable1EmployeeList.getColumnModel().getColumn(1).setResizable(false);
            jTable1EmployeeList.getColumnModel().getColumn(2).setResizable(false);
            jTable1EmployeeList.getColumnModel().getColumn(3).setResizable(false);
            jTable1EmployeeList.getColumnModel().getColumn(4).setResizable(false);
            jTable1EmployeeList.getColumnModel().getColumn(5).setResizable(false);
        }

        jButton1AddNewRecord.setText("Add New Record");
        jButton1AddNewRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1AddNewRecordActionPerformed(evt);
            }
        });

        jButton1ViewEmployeeDetails.setText("View Employee Details");
        jButton1ViewEmployeeDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ViewEmployeeDetailsActionPerformed(evt);
            }
        });

        jButton2DeleteEmployeeRecord.setText("Delete Employee Record");
        jButton2DeleteEmployeeRecord.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2DeleteEmployeeRecordActionPerformed(evt);
            }
        });

        jButton1.setText("View Attendance");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 878, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1AddNewRecord)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addGap(59, 59, 59)
                        .addComponent(jButton1ViewEmployeeDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2DeleteEmployeeRecord)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButton1AddNewRecord)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 528, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton1ViewEmployeeDetails)
                            .addComponent(jButton2DeleteEmployeeRecord)
                            .addComponent(jButton1))
                        .addContainerGap())
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1AddNewRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1AddNewRecordActionPerformed
        Access.accessViewEmployeeDetails(this.admin); 
        this.setVisible(false); // Hide the current frame
    }//GEN-LAST:event_jButton1AddNewRecordActionPerformed
    
    private void jButton1ViewEmployeeDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ViewEmployeeDetailsActionPerformed
        // Ensure a record is selected before proceeding
        if (!isSelectRecord()) {
            return;
        }
        
        int rowIndex = jTable1EmployeeList.getSelectedRow(); // Get selected row index
        String employeeID = jTable1EmployeeList.getValueAt(rowIndex, 0).toString(); // Retrieve employee ID
        
        // Open the employee details view with the selected employee's data
        Access.accessViewEmployeeDetails(this.admin, employeeID);
        this.setVisible(false); // Hide the current frame
    }//GEN-LAST:event_jButton1ViewEmployeeDetailsActionPerformed

    private void jButton6LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6LogOutActionPerformed
        admin.logout(this);
    }//GEN-LAST:event_jButton6LogOutActionPerformed

    private void jButton2DeleteEmployeeRecordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2DeleteEmployeeRecordActionPerformed
        // Ensure a record is selected before proceeding
        if (!isSelectRecord()) {
            return;
        }
        
        // Show confirmation dialog before deleting the record
        int result = JOptionPane.showConfirmDialog(null, "Are you sure to delete the Employee Record", "Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.NO_OPTION || result == JOptionPane.CLOSED_OPTION) {
                return; // Cancel the deletion if the user selects "No"
            }
            
        int rowIndex = jTable1EmployeeList.getSelectedRow(); // Get selected row index
        String employeeID = jTable1EmployeeList.getValueAt(rowIndex, 0).toString(); // Retrieve Employee ID
                      
        jTable1EmployeeList.removeAll();// Clear the table model before updating the data
        
        employeeService.deleteEmployee(employeeID);
        
        InformationService deleteInformationService = new InformationService();
//        jTable1EmployeeList.setModel(employeeService.getEmployeeTableModel()); // Refresh the table model with the updated data
        jTable1EmployeeList.setModel(deleteInformationService.getEmployeeInformationTableModel()); // Refresh the table model with the updated data
        
        JOptionPane.showMessageDialog(null, "Successfully Deleted"); // Notify user of successful deletion
    }//GEN-LAST:event_jButton2DeleteEmployeeRecordActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // Ensure a record is selected before proceeding
        if (!isSelectRecord()) {
            return;
        }
        
        int rowIndex = jTable1EmployeeList.getSelectedRow(); // Get selected row index

        // Extract all employee details from the table
        String employeeId = jTable1EmployeeList.getValueAt(rowIndex, 0).toString();
        String lastName = jTable1EmployeeList.getValueAt(rowIndex, 1).toString();
        String firstName = jTable1EmployeeList.getValueAt(rowIndex, 2).toString();        
        String birthday = jTable1EmployeeList.getValueAt(rowIndex, 3).toString();
        String address = jTable1EmployeeList.getValueAt(rowIndex, 4).toString();
        String phone = jTable1EmployeeList.getValueAt(rowIndex, 5).toString();

        // Create an Employee object with all details
        Employee selectedEmployee = new Employee(employeeId, lastName, firstName, birthday, address, phone);
        
//        Access.accessDTR(this.admin, selectedEmployee);
        Access.accessDTR(selectedEmployee);
        this.setVisible(false); // Hide the current frame
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton1EmployeeInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1EmployeeInformationActionPerformed

    }//GEN-LAST:event_jButton1EmployeeInformationActionPerformed

    private void jButton3SelfServicePortalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3SelfServicePortalActionPerformed
        Access.accessProfilePage(this.admin);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3SelfServicePortalActionPerformed

    private void jButton4PayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4PayrollActionPerformed
        Access.accessPayrollList(this.admin);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4PayrollActionPerformed

    private void jButton4AttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4AttendanceActionPerformed
        Access.accessAttendanceBiweekly((Admin) this.admin);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4AttendanceActionPerformed

    private void jButton3EmployeeRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3EmployeeRequestActionPerformed
        Access.accessEmployeeRequests(this.admin);
        this.setVisible(false);
    }//GEN-LAST:event_jButton3EmployeeRequestActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeInformation.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeInformation().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton1AddNewRecord;
    private javax.swing.JButton jButton1EmployeeInformation;
    private javax.swing.JButton jButton1ViewEmployeeDetails;
    private javax.swing.JButton jButton2DeleteEmployeeRecord;
    private javax.swing.JButton jButton3EmployeeRequest;
    private javax.swing.JButton jButton3SelfServicePortal;
    private javax.swing.JButton jButton4Attendance;
    private javax.swing.JButton jButton4Payroll;
    private javax.swing.JButton jButton6LogOut;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1EmployeeList;
    // End of variables declaration//GEN-END:variables

    /**
     * Validates if exactly one employee record is selected in the table.
     * Displays an error message if no or multiple selections are made.
     *
     * @return true if one record is selected, false otherwise
     */
    private boolean isSelectRecord() {
        if((jTable1EmployeeList.getSelectedRowCount() != 1)) { // Ensure exactly one row is selected
            JOptionPane.showMessageDialog(null, "Please select 1 Employee Record");
            return false;            
        }
        return true;
    }

}
