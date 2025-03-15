/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

/**
 *
 * @author Charm
 */
import Class.EntityManagement.EntityManager;
import Class.EntityManagement.EntityType;
import Class.PromptComboBoxRenderer;
import Class.TAT.Leave;
import Class.TAT.Overtime;
import Class.TAT.Request;
import Class.TAT.RequestService;
import Class.UMS.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class EmployeeRequests extends javax.swing.JFrame {    
    private User currentUser; // Store the current user (Admin or NonAdmin)
    Admin admin;
    RequestService requestService = new RequestService();
    
    public EmployeeRequests() {
        initComponents();
        jComboBox1StatusSelectLeave.setModel(requestService.getStatusComboBoxModel());
        jComboBox1StatusSelectLeave.setRenderer(new PromptComboBoxRenderer("Filter Status") );
        jComboBox1StatusSelectLeave.setSelectedIndex(-1);
        
        jComboBox1StatusSelectOT.setModel(requestService.getStatusComboBoxModel());
        jComboBox1StatusSelectOT.setRenderer(new PromptComboBoxRenderer("Filter Status") );
        jComboBox1StatusSelectOT.setSelectedIndex(-1);
        
        jTable1Leave.setModel(requestService.getLeaveRequestTableModel());
        jTable2Overtime.setModel(requestService.getOvertimeRequestTableModel());
    }

//    // Constructor to initialize with a User object
//    public EmployeeRequests(User user) {
//        this.currentUser = user;
//        initComponents(); // Initialize components 
//        String selectedStatus = "PENDING"; // Default to "PENDING" status on startup
//        loadOvertimeRequestsToTable(selectedStatus);
//        loadLeaveRequestsToTable(selectedStatus);
//    }
    
    // Constructor to initialize with a User object
    public EmployeeRequests(Admin admin) {
        initComponents(); // Initialize components 
        this.admin = admin;
        admin.addLogoutListener(this);
        
        jComboBox1StatusSelectLeave.setModel(requestService.getStatusComboBoxModel());
        jComboBox1StatusSelectLeave.setRenderer(new PromptComboBoxRenderer("Filter Status") );
        jComboBox1StatusSelectLeave.setSelectedIndex(-1);       
                
        jComboBox1StatusSelectOT.setModel(requestService.getStatusComboBoxModel());
        jComboBox1StatusSelectOT.setRenderer(new PromptComboBoxRenderer("Filter Status") );
        jComboBox1StatusSelectOT.setSelectedIndex(-1);
        
        jTable1Leave.setModel(requestService.getLeaveRequestTableModel());
        jTable2Overtime.setModel(requestService.getOvertimeRequestTableModel());
        
//        String selectedStatus = "PENDING"; // Default to "PENDING" status on startup
//        loadOvertimeRequestsToTable(selectedStatus);
//        loadLeaveRequestsToTable(selectedStatus);
    }

    // Method to load and filter overtime requests based on the selected status
    private void loadOvertimeRequestsToTable(String selectedStatus) {
        String csvFile = "src/CSVFiles/MotorPH Employee Data - Overtime.csv";  // Path to CSV file
        String line;
        String delimiter = ",";
        
        // Create a list to hold the CSV data 
        ArrayList<Object[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the header line and ignore it 
            String headerLine = br.readLine(); 
            
            // Loop through each line of the CSV file
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(delimiter);  // Split by commas
                
                // Create a new row to display in the table
                Object[] tableRow = {
                    rowData[0],  // Overtime Request ID
                    rowData[1],  // Employee ID
                    rowData[2],  // Overtime Date
                    rowData[3],  // Start Time
                    rowData[4],  // End Time
                    rowData[5],  // Total Hours
                    rowData[6]   // Request Date
                };

                // Apply filtering if selectedStatus is not null
                if (selectedStatus == null || selectedStatus.equals("ALL") || rowData[7].equals(selectedStatus)) {
                    data.add(tableRow);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Get the current table model
        DefaultTableModel model = (DefaultTableModel) jTable2Overtime.getModel();
        
        // Clear any existing rows in the table
        model.setRowCount(0);
        
        // Add the filtered rows to the table
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
    
    // Method to load and filter leave requests based on the selected status
    private void loadLeaveRequestsToTable(String selectedStatus) {
        String csvFile = "src/CSVFiles/MotorPH Employee Data - Leave.csv";  // Path to CSV file
        String line;
        String delimiter = ",";
        
        // Create a list to hold the CSV data 
        ArrayList<Object[]> data = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Read the header line and ignore it 
            String headerLine = br.readLine(); 
            
            // Loop through each line of the CSV file
            while ((line = br.readLine()) != null) {
                String[] rowData = line.split(delimiter);  // Split by commas
                
                // Create a new row to display in the table
                Object[] tableRow = {
                    rowData[0],  // Leave Request ID
                    rowData[1],  // Employee ID
                    rowData[2],  // Start Date
                    rowData[3],  // End Date
                    rowData[4],  // Leave Type
                    rowData[5],  // Total Days
                    rowData[6],  // Notes
                    rowData[7]   // Request Date
                };

                // Apply filtering here if selectedStatus is not null
                if (selectedStatus == null || selectedStatus.equals("ALL") || rowData[8].equals(selectedStatus)) {
                    data.add(tableRow);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Get the current table model
        DefaultTableModel model = (DefaultTableModel) jTable1Leave.getModel();
        
        // Clear any existing rows in the table
        model.setRowCount(0);
        
        // Add the filtered rows to the table
        for (Object[] row : data) {
            model.addRow(row);
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPanel1 = new javax.swing.JPanel();
        jButton1EmployeeInformation = new javax.swing.JButton();
        jButton3EmployeeRequest = new javax.swing.JButton();
        jButton4Payroll = new javax.swing.JButton();
        jButton6LogOut = new javax.swing.JButton();
        jButton3SelfServicePortal = new javax.swing.JButton();
        jButton4Attendance = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable1Leave = new javax.swing.JTable();
        jComboBox1StatusSelectLeave = new javax.swing.JComboBox<>();
        jButton1RejectLeave = new javax.swing.JButton();
        jButton1ApproveLeave = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2Overtime = new javax.swing.JTable();
        jComboBox1StatusSelectOT = new javax.swing.JComboBox<>();
        jButton1RejectOT = new javax.swing.JButton();
        jButton2ApproveOT = new javax.swing.JButton();

        jButton5.setBackground(new java.awt.Color(0, 102, 153));
        jButton5.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Payroll");
        jButton5.setBorder(null);

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

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
                    .addComponent(jButton3SelfServicePortal, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE)
                    .addComponent(jButton4Attendance, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 178, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
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

        jTable1Leave.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Leave Request ID", "Employee ID", "Start Date", "End Date", "Leave Type", "Total Days", "Notes", "Request Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1Leave.setCellSelectionEnabled(false);
        jTable1Leave.setRowSelectionAllowed(true);
        jTable1Leave.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jTable1Leave.setShowGrid(false);
        jTable1Leave.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(jTable1Leave);
        jTable1Leave.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable1Leave.getColumnModel().getColumnCount() > 0) {
            jTable1Leave.getColumnModel().getColumn(0).setResizable(false);
            jTable1Leave.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1Leave.getColumnModel().getColumn(1).setResizable(false);
            jTable1Leave.getColumnModel().getColumn(1).setPreferredWidth(20);
            jTable1Leave.getColumnModel().getColumn(2).setResizable(false);
            jTable1Leave.getColumnModel().getColumn(2).setPreferredWidth(10);
            jTable1Leave.getColumnModel().getColumn(3).setResizable(false);
            jTable1Leave.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable1Leave.getColumnModel().getColumn(4).setResizable(false);
            jTable1Leave.getColumnModel().getColumn(4).setPreferredWidth(20);
            jTable1Leave.getColumnModel().getColumn(5).setResizable(false);
            jTable1Leave.getColumnModel().getColumn(5).setPreferredWidth(10);
            jTable1Leave.getColumnModel().getColumn(6).setResizable(false);
            jTable1Leave.getColumnModel().getColumn(6).setPreferredWidth(150);
            jTable1Leave.getColumnModel().getColumn(7).setResizable(false);
        }

        jComboBox1StatusSelectLeave.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING", "APPROVED", "REJECTED" }));
        jComboBox1StatusSelectLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1StatusSelectLeaveActionPerformed(evt);
            }
        });

        jButton1RejectLeave.setText("REJECT");
        jButton1RejectLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1RejectLeaveActionPerformed(evt);
            }
        });

        jButton1ApproveLeave.setText("APPROVE");
        jButton1ApproveLeave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ApproveLeaveActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton1ApproveLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1RejectLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1StatusSelectLeave, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jComboBox1StatusSelectLeave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1RejectLeave)
                    .addComponent(jButton1ApproveLeave))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Leave", jPanel3);

        jTable2Overtime.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Overtime Request ID", "Employee ID", "Overtime Date", "Start Time", "End Time", "Total Hours", "Request Date"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable2Overtime.setCellSelectionEnabled(false);
        jTable2Overtime.setRowSelectionAllowed(true);
        jTable2Overtime.setSelectionBackground(new java.awt.Color(153, 204, 255));
        jTable2Overtime.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable2Overtime);
        jTable2Overtime.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        if (jTable2Overtime.getColumnModel().getColumnCount() > 0) {
            jTable2Overtime.getColumnModel().getColumn(0).setResizable(false);
            jTable2Overtime.getColumnModel().getColumn(1).setResizable(false);
            jTable2Overtime.getColumnModel().getColumn(2).setResizable(false);
            jTable2Overtime.getColumnModel().getColumn(3).setResizable(false);
            jTable2Overtime.getColumnModel().getColumn(4).setResizable(false);
            jTable2Overtime.getColumnModel().getColumn(5).setResizable(false);
        }

        jComboBox1StatusSelectOT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "PENDING", "APPROVED", "REJECTED" }));
        jComboBox1StatusSelectOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1StatusSelectOTActionPerformed(evt);
            }
        });

        jButton1RejectOT.setText("REJECT");
        jButton1RejectOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1RejectOTActionPerformed(evt);
            }
        });

        jButton2ApproveOT.setText("APPROVE");
        jButton2ApproveOT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ApproveOTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton2ApproveOT, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton1RejectOT, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 904, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox1StatusSelectOT, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jComboBox1StatusSelectOT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 315, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1RejectOT)
                    .addComponent(jButton2ApproveOT))
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Overtime", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 933, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(32, Short.MAX_VALUE))
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 27, Short.MAX_VALUE))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1EmployeeInformationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1EmployeeInformationActionPerformed
        Access.accessEmployeeInformation(this.admin);
        this.setVisible(false);
    }//GEN-LAST:event_jButton1EmployeeInformationActionPerformed

    private void jButton6LogOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6LogOutActionPerformed
        admin.logout(this);
    }//GEN-LAST:event_jButton6LogOutActionPerformed

    private void jButton3SelfServicePortalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3SelfServicePortalActionPerformed
       Access.accessProfilePage(this.admin);
       this.setVisible(false);
    }//GEN-LAST:event_jButton3SelfServicePortalActionPerformed

    private void jComboBox1StatusSelectOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1StatusSelectOTActionPerformed
        if (jComboBox1StatusSelectOT.getSelectedIndex() < 0 || jComboBox1StatusSelectOT.getSelectedItem() == null) {
            return;
        }
        
        // Get the selected status from the JComboBox
        String selectedStatus = (String) jComboBox1StatusSelectOT.getSelectedItem();
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable2Overtime.getModel());
        jTable2Overtime.setRowSorter(sorter);
        
        if ("All".equals(selectedStatus)) {
            sorter.setRowFilter(null); // Show all rows
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("^" + selectedStatus + "$", 7)); // Match status in column index 2
        }
        
        // Load and filter the data based on the selected status
//        loadOvertimeRequestsToTable(selectedStatus);
    }//GEN-LAST:event_jComboBox1StatusSelectOTActionPerformed

    private void jButton2ApproveOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ApproveOTActionPerformed
        if (!isSelectRecord(jTable2Overtime)) {
            return;
        }
        
        int rowIndex = jTable2Overtime.getSelectedRow(); // Get selected row index
        String overtimeID = jTable2Overtime.getValueAt(rowIndex, 0).toString(); // Retrieve employee ID
        
        Overtime updateOvertime = requestService.getOvertimeRecord(overtimeID);
        Request updateRequest = requestService.getRequestRecord(overtimeID);
        
        updateOvertime.setIsApproved(true);
        updateRequest.approve(admin.getEmployeeID());
    }//GEN-LAST:event_jButton2ApproveOTActionPerformed

    private void jButton1RejectOTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1RejectOTActionPerformed
        if (!isSelectRecord(jTable2Overtime)) {
            return;
        }
        
        int rowIndex = jTable2Overtime.getSelectedRow(); // Get selected row index
        String overtimeID = jTable2Overtime.getValueAt(rowIndex, 0).toString(); // Retrieve employee ID
        
        Overtime updateOvertime = requestService.getOvertimeRecord(overtimeID);
        Request updateRequest = requestService.getRequestRecord(overtimeID);
        
        updateOvertime.setIsApproved(false);
        updateRequest.reject(admin.getEmployeeID(), "test");
    }//GEN-LAST:event_jButton1RejectOTActionPerformed

    private void jComboBox1StatusSelectLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1StatusSelectLeaveActionPerformed
        if (jComboBox1StatusSelectLeave.getSelectedIndex() < 0 || jComboBox1StatusSelectLeave.getSelectedItem() == null) {
            return;
        }

        // Get selected status from the JComboBox
        String selectedStatus = (String) jComboBox1StatusSelectLeave.getSelectedItem();
        
        TableRowSorter<TableModel> sorter = new TableRowSorter<>(jTable1Leave.getModel());
        jTable1Leave.setRowSorter(sorter);
        
        if ("All".equals(selectedStatus)) {
            sorter.setRowFilter(null); // Show all rows
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("^" + selectedStatus + "$", 8)); // Match status in column index 2
        }

//        // Load and filter the leave requests based on selected status
//        loadLeaveRequestsToTable(selectedStatus);
    }//GEN-LAST:event_jComboBox1StatusSelectLeaveActionPerformed

    private void jButton4AttendanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4AttendanceActionPerformed
        Access.accessAttendanceBiweekly((Admin) this.admin);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4AttendanceActionPerformed

    private void jButton1ApproveLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ApproveLeaveActionPerformed
        if (!isSelectRecord(jTable1Leave)) {
            return;
        }
        
        int rowIndex = jTable1Leave.getSelectedRow(); // Get selected row index
        String leaveID = jTable1Leave.getValueAt(rowIndex, 0).toString(); // Retrieve employee ID
        
        Leave updateLeave = requestService.getLeaveRecord(leaveID);
        Request updateRequest = requestService.getRequestRecord(leaveID);
        
        updateLeave.setIsApproved(true);
        updateRequest.approve(admin.getEmployeeID());
        
        
    }//GEN-LAST:event_jButton1ApproveLeaveActionPerformed

    private void jButton1RejectLeaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1RejectLeaveActionPerformed
        if (!isSelectRecord(jTable1Leave)) {
            return;
        }
        
        int rowIndex = jTable1Leave.getSelectedRow(); // Get selected row index
        String leaveID = jTable1Leave.getValueAt(rowIndex, 0).toString(); // Retrieve employee ID
        
        Leave updateLeave = requestService.getLeaveRecord(leaveID);
        Request updateRequest = requestService.getRequestRecord(leaveID);
        
        updateLeave.setIsApproved(true);
        updateRequest.reject(admin.getEmployeeID(), "test");
        
        EntityManager leave = new EntityManager(EntityType.LEAVE);
        EntityManager request = new EntityManager(EntityType.REQUEST);
        
//        try {
//            leave.saveEntityRecord(updateLeave);
//            request.saveEntityRecord(updateReques);
//        } catch (Exception ex) {
//            Logger.getLogger(EmployeeRequests.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        
    }//GEN-LAST:event_jButton1RejectLeaveActionPerformed

    private void jButton4PayrollActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4PayrollActionPerformed
        Access.accessPayrollList(this.admin);
        this.setVisible(false);
    }//GEN-LAST:event_jButton4PayrollActionPerformed

    private void jButton3EmployeeRequestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3EmployeeRequestActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3EmployeeRequestActionPerformed

//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//    /* Set the Nimbus look and feel */
//    try {
//        for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//            if ("Nimbus".equals(info.getName())) {
//                javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                break;
//            }
//        }
//    } catch (Exception ex) {
//        java.util.logging.Logger.getLogger(EmployeeRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//    }
//
//    /* Create and display the form */
//    java.awt.EventQueue.invokeLater(new Runnable() {
//        public void run() {
// 
//        }
//    });
//    }
    
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
            java.util.logging.Logger.getLogger(EmployeeRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmployeeRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmployeeRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmployeeRequests.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmployeeRequests().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1ApproveLeave;
    private javax.swing.JButton jButton1EmployeeInformation;
    private javax.swing.JButton jButton1RejectLeave;
    private javax.swing.JButton jButton1RejectOT;
    private javax.swing.JButton jButton2ApproveOT;
    private javax.swing.JButton jButton3EmployeeRequest;
    private javax.swing.JButton jButton3SelfServicePortal;
    private javax.swing.JButton jButton4Attendance;
    private javax.swing.JButton jButton4Payroll;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6LogOut;
    private javax.swing.JComboBox<String> jComboBox1StatusSelectLeave;
    private javax.swing.JComboBox<String> jComboBox1StatusSelectOT;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1Leave;
    private javax.swing.JTable jTable2Overtime;
    // End of variables declaration//GEN-END:variables
    /**
     * Validates if exactly one employee record is selected in the table.
     * Displays an error message if no or multiple selections are made.
     *
     * @return true if one record is selected, false otherwise
     */
    private boolean isSelectRecord(JTable jTable) {
        if((jTable.getSelectedRowCount() != 1)) { // Ensure exactly one row is selected
            JOptionPane.showMessageDialog(null, "Please select 1 Employee Record");
            return false;            
        }
        return true;
    }
}
