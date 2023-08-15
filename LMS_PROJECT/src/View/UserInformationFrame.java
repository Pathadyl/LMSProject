/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Book;
import Model.Loan;
import Model.Member;
import Service.AdminService;
import java.sql.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class UserInformationFrame extends javax.swing.JFrame {
    AdminService adminService;
    DefaultTableModel defaultTableUserModel;
    Member member;
    
    /**
     * Creates new form UserInformationFrame
     */
    
    public UserInformationFrame(int memberID) {
        adminService = new AdminService();
        member = new Member();
        initComponents();
        
        member = adminService.getMemberByID(memberID);
        
        defaultTableUserModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        borrowBookTable.setModel(defaultTableUserModel);
        defaultTableUserModel.addColumn("ID BooK");
        defaultTableUserModel.addColumn("Book Name");
        defaultTableUserModel.addColumn("Borrow Date");
        defaultTableUserModel.addColumn("Due Date");
        
        setBorrowDataTable(defaultTableUserModel, adminService.getBorrowBookedByMemberID(memberID), 
                           adminService.getMemberLoanTransactionByMemberID(memberID));
        
        userIDInfoTextField.setText(String.valueOf(member.getMemberID()));
        nameInfoTextFiled.setText(member.getFullName());
        dobInfoTextField.setText(member.getDob().toString());
        phoneInfoTextField.setText(member.getPhone());
        startDateInfoTextField.setText(member.getStartDate().toString());
        endDateInfoTextField.setText(member.getEndDate().toString());
        
    }
    
    private void setBorrowDataTable(DefaultTableModel defaultTableUserModel, List<Book> borrowList, List<Loan> loans){
        for (Book b : borrowList) {
            for(Loan loan : loans) {
                if (b.getBookId() == loan.getIdBook()) {
                    defaultTableUserModel.addRow(new Object[]{b.getBookId(),
                                                              b.getBookName(),
                                                              loan.getLoanDate(),
                                                              loan.getDueDate()}
                                                );
                }
            }
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

        jPanel1 = new javax.swing.JPanel();
        nameInfoLabel = new javax.swing.JLabel();
        phoneInfoLabel = new javax.swing.JLabel();
        startDateInfoLabel = new javax.swing.JLabel();
        endDateInfoLabel = new javax.swing.JLabel();
        userIDInfoLabel = new javax.swing.JLabel();
        userIDInfoTextField = new javax.swing.JTextField();
        nameInfoTextFiled = new javax.swing.JTextField();
        dobInfoTextField = new javax.swing.JTextField();
        phoneInfoTextField = new javax.swing.JTextField();
        startDateInfoTextField = new javax.swing.JTextField();
        endDateInfoTextField = new javax.swing.JTextField();
        dobInfoLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        borrowBookTable = new javax.swing.JTable();
        updateUserButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        bar = new javax.swing.JMenuBar();
        userInfoMenu = new javax.swing.JMenu();
        showInfoMode = new javax.swing.JMenuItem();
        editInfoMode = new javax.swing.JMenuItem();

        setTitle("Member Infomation Frame");

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        nameInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        nameInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        nameInfoLabel.setText("Full Name:");

        phoneInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        phoneInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        phoneInfoLabel.setText("Phone Number:");

        startDateInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        startDateInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        startDateInfoLabel.setText("Start Date:");

        endDateInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        endDateInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        endDateInfoLabel.setText("End Date:");

        userIDInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        userIDInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        userIDInfoLabel.setText("User ID:");

        userIDInfoTextField.setEditable(false);
        userIDInfoTextField.setBackground(new java.awt.Color(255, 156, 192));

        nameInfoTextFiled.setEditable(false);
        nameInfoTextFiled.setBackground(new java.awt.Color(255, 156, 192));

        dobInfoTextField.setEditable(false);
        dobInfoTextField.setBackground(new java.awt.Color(255, 156, 192));

        phoneInfoTextField.setEditable(false);
        phoneInfoTextField.setBackground(new java.awt.Color(255, 156, 192));

        startDateInfoTextField.setEditable(false);
        startDateInfoTextField.setBackground(new java.awt.Color(255, 156, 192));

        endDateInfoTextField.setEditable(false);
        endDateInfoTextField.setBackground(new java.awt.Color(255, 156, 192));

        dobInfoLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dobInfoLabel.setForeground(new java.awt.Color(0, 0, 0));
        dobInfoLabel.setText("Date Of Birth: ");

        borrowBookTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        borrowBookTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(borrowBookTable);

        updateUserButton.setBackground(new java.awt.Color(157, 0, 57));
        updateUserButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        updateUserButton.setForeground(new java.awt.Color(255, 255, 255));
        updateUserButton.setText("Update");
        updateUserButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateUserButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("List Borrow Book");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(startDateInfoLabel)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(endDateInfoTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(startDateInfoTextField)))
                    .addComponent(nameInfoLabel)
                    .addComponent(endDateInfoLabel)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dobInfoLabel)
                            .addComponent(phoneInfoLabel))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(dobInfoTextField, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameInfoTextFiled, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(userIDInfoTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 132, Short.MAX_VALUE)
                            .addComponent(phoneInfoTextField)))
                    .addComponent(userIDInfoLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 83, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(54, 54, 54))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(118, 118, 118))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(updateUserButton)
                .addGap(143, 143, 143))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(userIDInfoLabel)
                            .addComponent(userIDInfoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameInfoLabel)
                            .addComponent(nameInfoTextFiled, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dobInfoLabel)
                            .addComponent(dobInfoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(phoneInfoLabel)
                            .addComponent(phoneInfoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(startDateInfoLabel)
                            .addComponent(startDateInfoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(endDateInfoLabel)
                            .addComponent(endDateInfoTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateUserButton, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(157, 0, 57));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("USER INFORMATION");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(208, 208, 208))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        userInfoMenu.setText("File");

        showInfoMode.setText("Show Info Mode");
        showInfoMode.setSelected(true);
        showInfoMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showInfoModeActionPerformed(evt);
            }
        });
        userInfoMenu.add(showInfoMode);

        editInfoMode.setText("Edit Mode");
        editInfoMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editInfoModeActionPerformed(evt);
            }
        });
        userInfoMenu.add(editInfoMode);

        bar.add(userInfoMenu);

        setJMenuBar(bar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void updateUserButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateUserButtonActionPerformed
        // TODO add your handling code here:
        String name = nameInfoTextFiled.getText();
        String dob = dobInfoTextField.getText();
        String phone = phoneInfoTextField.getText();
        String startDate = startDateInfoTextField.getText();
        String endDate = endDateInfoTextField.getText();
            
        member.setFullName(name);
        member.setDob(Date.valueOf(dob));
        member.setPhone(phone);
        member.setStartDate(Date.valueOf(startDate));
        member.setEndDate(Date.valueOf(endDate));
            
            String ConfirmationMessage = "Do you want to change this member to: \n"
                                       + "Name: " + name + "\n" 
                                       + "Date of birth: " + dob + "\n"
                                       + "Phone number: " + phone + "\n"; 
        
            int result = JOptionPane.showConfirmDialog(null, 
                                                 ConfirmationMessage, 
                                                 "CHANGE MEMBER INFOMATION", 
                                                 JOptionPane.YES_NO_OPTION);
        
            if (result == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, 
                                              "Change Success!", 
                                              "SUCCESS", 
                                              JOptionPane.INFORMATION_MESSAGE);
                adminService.updateMember(member);
            }
    }//GEN-LAST:event_updateUserButtonActionPerformed

    private void editInfoModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editInfoModeActionPerformed
        // TODO add your handling code here:
        nameInfoTextFiled.setEditable(true);
        dobInfoTextField.setEditable(true);
        phoneInfoTextField.setEditable(true);
    }//GEN-LAST:event_editInfoModeActionPerformed

    private void showInfoModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showInfoModeActionPerformed
        // TODO add your handling code here:
        nameInfoTextFiled.setEditable(false);
        dobInfoTextField.setEditable(false);
        phoneInfoTextField.setEditable(false);

    }//GEN-LAST:event_showInfoModeActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar bar;
    private javax.swing.JTable borrowBookTable;
    private javax.swing.JLabel dobInfoLabel;
    private javax.swing.JTextField dobInfoTextField;
    private javax.swing.JMenuItem editInfoMode;
    private javax.swing.JLabel endDateInfoLabel;
    private javax.swing.JTextField endDateInfoTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel nameInfoLabel;
    private javax.swing.JTextField nameInfoTextFiled;
    private javax.swing.JLabel phoneInfoLabel;
    private javax.swing.JTextField phoneInfoTextField;
    private javax.swing.JMenuItem showInfoMode;
    private javax.swing.JLabel startDateInfoLabel;
    private javax.swing.JTextField startDateInfoTextField;
    private javax.swing.JButton updateUserButton;
    private javax.swing.JLabel userIDInfoLabel;
    private javax.swing.JTextField userIDInfoTextField;
    private javax.swing.JMenu userInfoMenu;
    // End of variables declaration//GEN-END:variables
}
