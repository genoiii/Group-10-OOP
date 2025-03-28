/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Frame;

import Class.*;
import Class.UMS.*;
/**
 *
 * @author Charm
 */
//import App.UMS.Admin;
import javax.swing.JOptionPane;

public class LoginPage extends javax.swing.JFrame {

    /**
     * Creates new form LoginPage
     */
    public LoginPage() {
        initComponents();
        jButton2ForgotPassword.setVisible(false);
        jLabelIncorrectCredentials.setVisible(false); // Hide error message label initially
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1Username = new javax.swing.JLabel();
        jTextField1Username = new javax.swing.JTextField();
        jLabel3Password = new javax.swing.JLabel();
        jButton1LogIn = new javax.swing.JButton();
        jButton2ForgotPassword = new javax.swing.JButton();
        jLabelIncorrectCredentials = new javax.swing.JLabel();
        jPasswordFieldPassword = new javax.swing.JPasswordField();
        jLabelLogo = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1Username.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1Username.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1Username.setText("Username:");

        jTextField1Username.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1UsernameActionPerformed(evt);
            }
        });

        jLabel3Password.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3Password.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3Password.setText("Password:");

        jButton1LogIn.setBackground(new java.awt.Color(204, 204, 204));
        jButton1LogIn.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButton1LogIn.setText("Log In");
        jButton1LogIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1LogInActionPerformed(evt);
            }
        });

        jButton2ForgotPassword.setBackground(new java.awt.Color(0, 102, 153));
        jButton2ForgotPassword.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        jButton2ForgotPassword.setForeground(new java.awt.Color(255, 255, 255));
        jButton2ForgotPassword.setText("Forgot Password?");
        jButton2ForgotPassword.setBorder(null);

        jLabelIncorrectCredentials.setForeground(new java.awt.Color(255, 102, 102));
        jLabelIncorrectCredentials.setText("Incorrect Username or Password");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton2ForgotPassword)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel1Username, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3Password, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(10, 10, 10)))
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextField1Username, javax.swing.GroupLayout.DEFAULT_SIZE, 181, Short.MAX_VALUE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(jLabelIncorrectCredentials))
                                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.Alignment.TRAILING)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addComponent(jButton1LogIn, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(110, 110, 110)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1Username)
                    .addComponent(jTextField1Username, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3Password)
                    .addComponent(jPasswordFieldPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelIncorrectCredentials)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2ForgotPassword)
                .addGap(30, 30, 30)
                .addComponent(jButton1LogIn)
                .addContainerGap(98, Short.MAX_VALUE))
        );

        jLabelLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/MotorPH Logo.png"))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("MotorPH Payroll App");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(41, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelLogo)
                    .addComponent(jLabel1))
                .addGap(30, 30, 30)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabelLogo)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1LogInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1LogInActionPerformed
        // Retrieve the entered username and password
        String username = jTextField1Username.getText();
        String password = new String(jPasswordFieldPassword.getPassword());
        
        // Create an Input object to validate user credentials
        Input userInputCredential = new Input();
        User user = new User();
        user = userInputCredential.isAuthenticated(username, password);
        
        if (user != null) {
            JOptionPane.showMessageDialog(this, "Login Successful!\nWelcome, " + user.getUsername());
            dispose(); // Close login window

            // Open the appropriate dashboard
            if (user instanceof Admin) {
                Admin.login(user);
            } else if (user instanceof NonAdmin) {
                NonAdmin.login(user);
            }
            this.setVisible(false); // Close the LoginPage  
            return;
        }
        
        jLabelIncorrectCredentials.setVisible(true); // Display error message if authentication fails
//            JOptionPane.showMessageDialog(this, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        
//        // Check if the user is authenticated
//        if (!userInputCredential.isAuthenticated(username, password)){
//            jLabelIncorrectCredentials.setVisible(true); // Display error message if authentication fails
//            return; // Exit the method to prevent further execution
//        }

//        User user = new User(userInputCredential);
//        user.login(userInputCredential.isAdmin());
        
        
            
    }//GEN-LAST:event_jButton1LogInActionPerformed

    private void jTextField1UsernameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1UsernameActionPerformed
//        String username = jTextField1Username.getText();
//        if (username.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Username cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
//        }
    }//GEN-LAST:event_jTextField1UsernameActionPerformed

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
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginPage.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginPage().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1LogIn;
    private javax.swing.JButton jButton2ForgotPassword;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel1Username;
    private javax.swing.JLabel jLabel3Password;
    private javax.swing.JLabel jLabelIncorrectCredentials;
    private javax.swing.JLabel jLabelLogo;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPasswordField jPasswordFieldPassword;
    private javax.swing.JTextField jTextField1Username;
    // End of variables declaration//GEN-END:variables
}
