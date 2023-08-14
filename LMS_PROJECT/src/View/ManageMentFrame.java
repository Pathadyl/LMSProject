/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.Admin;
import Model.Book;
import Model.Loan;
import Model.Member;
import Service.AdminService;
import java.awt.CardLayout;
import java.awt.Color;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Admin
 */
public class ManageMentFrame extends javax.swing.JFrame {
    AdminService adminService;
    Member memberProcedure;
    Book bookProcedure;
    List<Loan> memberProcedureLoanTransaction;
    Loan loanProcedure;
    DefaultTableModel defaulMemberTableModel;
    DefaultTableModel defaultBookTableModel;
    DefaultTableModel defaultLoanTableModel;
    
    /**
     * Creates new form ManageMentFrame
     */
    public ManageMentFrame(Admin admin) {
        initComponents();
        String welcome = "Welcome, " + admin.getUserName();
        welcomeLabel.setText(welcome);
        adminService = new AdminService();
        memberProcedure = new Member();
        bookProcedure = new Book();
        loanProcedure = new Loan();
        memberProcedureLoanTransaction = new ArrayList<Loan>();
        
//================= SET UP DATA FOR USER TABLE =================================
        defaulMemberTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        memberTable.setModel(defaulMemberTableModel);
        defaulMemberTableModel.addColumn("ID USER");
        defaulMemberTableModel.addColumn("Full Name");
        defaulMemberTableModel.addColumn("Date Of Birth");
        defaulMemberTableModel.addColumn("Phone");
        defaulMemberTableModel.addColumn("Start Date");
        defaulMemberTableModel.addColumn("End Date");
        
        List<Member> members = adminService.getAllMember();
        
        for (Member member : members) {
            setMemberTableData(member);
        }
       
       memberTable.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                    int selectedRow = memberTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int userID = (int) memberTable.getValueAt(selectedRow, 0);
                        new UserInformationFrame(userID).setVisible(true);
                    }
               }
           }
       });
//============================================================================== 

//================= SET UP DATA FOR BOOK TABLE =================================
        defaultBookTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        bookTable.setModel(defaultBookTableModel);
        defaultBookTableModel.addColumn("ID BOOK");
        defaultBookTableModel.addColumn("NAME BOOK");
        defaultBookTableModel.addColumn("NAME AUTHOR");
        defaultBookTableModel.addColumn("DESCRIPTION");
        defaultBookTableModel.addColumn("GENRE");
        defaultBookTableModel.addColumn("YEAR PUBLIC");
        defaultBookTableModel.addColumn("AVAILABILITY");
        
        List<Book> books = adminService.getAllBook();
        
        for (Book book : books) {
            setBookTableData(book);
        }
       
       bookTable.addMouseListener(new MouseAdapter() {
           public void mouseClicked(MouseEvent e) {
               if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
                   int selectedRow = bookTable.getSelectedRow();
                    if (selectedRow != -1) {
                        int bookID = (int) bookTable.getValueAt(selectedRow, 0);
                        new BookInformationframe(bookID).setVisible(true);
                    }
                }
           }
        });
//==============================================================================
        
        genreComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedGenre = (String) genreComboBox.getSelectedItem();
                if(selectedGenre.equals("All")) {
                    defaultBookTableModel.setRowCount(0);
                    List<Book> books = adminService.getAllBook();
                    for (Book book : books) {
                        setBookTableData(book);
                    }
                } else if (selectedGenre.equals("Science")) {
                    List<Book> searchedBook = new ArrayList<Book>();
                    searchedBook = adminService.searchBookByGerne(selectedGenre);
                    defaultBookTableModel.setRowCount(0);
                    for (Book book : searchedBook) {
                        setBookTableData(book);
                    }
                } else if (selectedGenre.equals("Fiction")) {
                    List<Book> searchedBook = new ArrayList<Book>();
                    searchedBook = adminService.searchBookByGerne(selectedGenre);
                    defaultBookTableModel.setRowCount(0);
                    for (Book book : searchedBook) {
                        setBookTableData(book);
                    }
                } else if (selectedGenre.equals("Comedy")) {
                    List<Book> searchedBook = new ArrayList<Book>();
                    searchedBook = adminService.searchBookByGerne(selectedGenre);
                    defaultBookTableModel.setRowCount(0);
                    for (Book book : searchedBook) {
                        setBookTableData(book);
                    }
                }
            }
        });
        
        userManagementButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                userManagementButton.setContentAreaFilled(true);
            }
            
            public void mouseExited(MouseEvent e) {
                userManagementButton.setContentAreaFilled(false);
            }
        });
        
        bookManagementButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                bookManagementButton.setContentAreaFilled(true);
            }
            
            public void mouseExited(MouseEvent e) {
                bookManagementButton.setContentAreaFilled(false);
            }
        });
        
        procedureButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                procedureButton.setContentAreaFilled(true);
            }
            
            public void mouseExited(MouseEvent e) {
                procedureButton.setContentAreaFilled(false);
            }
        });
        
        logOutButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                logOutButton.setContentAreaFilled(true);
            }
            
            public void mouseExited(MouseEvent e) {
                logOutButton.setContentAreaFilled(false);
            }
        });
//================= SET UP DATA FOR LOAN TABLE =================================        
        
        defaultLoanTableModel = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        loanTable.setModel(defaultLoanTableModel);
        defaultLoanTableModel.addColumn("LOAN ID");
        defaultLoanTableModel.addColumn("ID BOOK");
        defaultLoanTableModel.addColumn("ID MEMBER");
        defaultLoanTableModel.addColumn("LOAN DATE");
        defaultLoanTableModel.addColumn("DUE DATE");
        defaultLoanTableModel.addColumn("RETURN DATE");
        
        List<Loan> loans = adminService.getAllLoan();
        
        for (Loan loan : loans) {
            setLoanTableData(loan);
        }
//================================================================================
        
        

        
    }
    
    private void setMemberTableData(Member member){
        defaulMemberTableModel.addRow(new Object[]{member.getMemberID(), 
                                                  member.getFullName(), 
                                                  member.getDob(), 
                                                  member.getPhone(), 
                                                  member.getStartDate(), 
                                                  member.getEndDate()}
                                    );
    }
    
    private void setBookTableData(Book book) {
        defaultBookTableModel.addRow(new Object[]{book.getBookId(), 
                                                      book.getBookName(), 
                                                      book.getBookAuthor(),  
                                                      book.getBookDescription(),
                                                      book.getBookGerne(),
                                                      book.getYearPublic(),
                                                      book.getAvailability()}
                                        );
    }
    
    private void setLoanTableData(Loan loan) {
        defaultLoanTableModel.addRow(new Object[]{loan.getLoanId(),
                                                  loan.getIdBook(),
                                                  loan.getIdMember(),
                                                  loan.getLoanDate(),
                                                  loan.getDueDate(),
                                                  loan.getReturnDate()}
                                        );
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor. 
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        managementButtonGroupPanel = new javax.swing.JPanel();
        userManagementButton = new javax.swing.JButton();
        bookManagementButton = new javax.swing.JButton();
        procedureButton = new javax.swing.JButton();
        logOutButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        managementPanel = new javax.swing.JPanel();
        memberInfoPanel = new javax.swing.JPanel();
        memberTablePanel = new javax.swing.JScrollPane();
        memberTable = new javax.swing.JTable();
        searchMemberGroupPanel = new javax.swing.JPanel();
        memberSeachByNameTextField = new javax.swing.JTextField();
        memberSearchByNameLabel = new javax.swing.JLabel();
        memberSearchIDTextField = new javax.swing.JTextField();
        memberSearchIDLabel = new javax.swing.JLabel();
        addMemberButton = new javax.swing.JButton();
        deleteMemberButton = new javax.swing.JButton();
        refreshDataButton = new javax.swing.JButton();
        memberManagementLabel = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        bookInfoPanel = new javax.swing.JPanel();
        searchBookGroupPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        genreComboBox = new javax.swing.JComboBox<>();
        bookSearchIDLabel = new javax.swing.JLabel();
        bookSearchIDTextField = new javax.swing.JTextField();
        bookSearchByNameLabel = new javax.swing.JLabel();
        bookSeachByNameTextField = new javax.swing.JTextField();
        bookManagementLabel = new javax.swing.JLabel();
        addBookButton = new javax.swing.JButton();
        deleteBookButton = new javax.swing.JButton();
        refreshBookDataButton = new javax.swing.JButton();
        bookTablePanel = new javax.swing.JScrollPane();
        bookTable = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        procedurePanel = new javax.swing.JPanel();
        checkingProcedure = new javax.swing.JPanel();
        checkingPanel = new javax.swing.JPanel();
        memberIDLabel = new javax.swing.JLabel();
        infoConfirmLabel = new javax.swing.JLabel();
        memberIDTextField = new javax.swing.JTextField();
        memberNameLabel = new javax.swing.JLabel();
        bookNameTextField = new javax.swing.JTextField();
        bookIDLabel = new javax.swing.JLabel();
        availabilityTextField = new javax.swing.JTextField();
        bookNameLabel = new javax.swing.JLabel();
        memberNameTextField = new javax.swing.JTextField();
        availabilityLabel = new javax.swing.JLabel();
        bookIDTextField = new javax.swing.JTextField();
        checkingButton = new javax.swing.JButton();
        procedure = new javax.swing.JPanel();
        defaultPanel = new javax.swing.JPanel();
        returnPanel = new javax.swing.JPanel();
        returnConfirmLabel = new javax.swing.JLabel();
        borrowDateLabel = new javax.swing.JLabel();
        borrowDateTextField = new javax.swing.JTextField();
        dueDateLabel = new javax.swing.JLabel();
        dueDateTextField = new javax.swing.JTextField();
        returnDateLabel = new javax.swing.JLabel();
        statusTextField = new javax.swing.JTextField();
        statusLabel = new javax.swing.JLabel();
        returnDateTextField = new javax.swing.JTextField();
        returnButton = new javax.swing.JButton();
        borrowPanel = new javax.swing.JPanel();
        borrowLabel = new javax.swing.JLabel();
        borrowDurationLabel = new javax.swing.JLabel();
        borrowDurationComboBox = new javax.swing.JComboBox<>();
        borrowDate2Label = new javax.swing.JLabel();
        borrowDate2TextField = new javax.swing.JTextField();
        dueDate2Label = new javax.swing.JLabel();
        dueDate2TextField = new javax.swing.JTextField();
        borrowButton = new javax.swing.JButton();
        borrowStatusLabel = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        loanTransactionPanel = new javax.swing.JPanel();
        loanTransactionLabel = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        loanTable = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        welcomeLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        managementButtonGroupPanel.setBackground(new java.awt.Color(51, 51, 51));

        userManagementButton.setBackground(new java.awt.Color(145, 0, 57));
        userManagementButton.setForeground(new java.awt.Color(255, 255, 255));
        userManagementButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Conference_26px.png"))); // NOI18N
        userManagementButton.setText("MEMBER MANAGEMENT      ");
        userManagementButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        userManagementButton.setContentAreaFilled(false);
        userManagementButton.setFocusable(false);
        userManagementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userManagementButtonActionPerformed(evt);
            }
        });

        bookManagementButton.setBackground(new java.awt.Color(145, 0, 57));
        bookManagementButton.setForeground(new java.awt.Color(255, 255, 255));
        bookManagementButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Books_26px.png"))); // NOI18N
        bookManagementButton.setText("BOOK MANAGEMENT           ");
        bookManagementButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        bookManagementButton.setBorderPainted(false);
        bookManagementButton.setContentAreaFilled(false);
        bookManagementButton.setFocusable(false);
        bookManagementButton.setIconTextGap(6);
        bookManagementButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookManagementButtonActionPerformed(evt);
            }
        });

        procedureButton.setBackground(new java.awt.Color(145, 0, 57));
        procedureButton.setForeground(new java.awt.Color(255, 255, 255));
        procedureButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Sell_26px.png"))); // NOI18N
        procedureButton.setText("PROCEDURE MANAGEMENT");
        procedureButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        procedureButton.setBorderPainted(false);
        procedureButton.setContentAreaFilled(false);
        procedureButton.setFocusable(false);
        procedureButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                procedureButtonActionPerformed(evt);
            }
        });

        logOutButton.setBackground(new java.awt.Color(145, 0, 57));
        logOutButton.setForeground(new java.awt.Color(255, 255, 255));
        logOutButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/icons8_Exit_26px.png"))); // NOI18N
        logOutButton.setText("LOG OUT                                   ");
        logOutButton.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        logOutButton.setBorderPainted(false);
        logOutButton.setContentAreaFilled(false);
        logOutButton.setFocusable(false);
        logOutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOutButtonActionPerformed(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Image/TroyLMSLogo.png"))); // NOI18N

        javax.swing.GroupLayout managementButtonGroupPanelLayout = new javax.swing.GroupLayout(managementButtonGroupPanel);
        managementButtonGroupPanel.setLayout(managementButtonGroupPanelLayout);
        managementButtonGroupPanelLayout.setHorizontalGroup(
            managementButtonGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managementButtonGroupPanelLayout.createSequentialGroup()
                .addGroup(managementButtonGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(managementButtonGroupPanelLayout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jLabel2)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(userManagementButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bookManagementButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(procedureButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(logOutButton, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE))
                .addContainerGap())
        );
        managementButtonGroupPanelLayout.setVerticalGroup(
            managementButtonGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(managementButtonGroupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 91, Short.MAX_VALUE)
                .addComponent(userManagementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookManagementButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(procedureButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logOutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
        );

        managementPanel.setAlignmentX(0.0F);
        managementPanel.setPreferredSize(new java.awt.Dimension(748, 475));
        managementPanel.setLayout(new java.awt.CardLayout());

        memberInfoPanel.setBackground(new java.awt.Color(244, 244, 244));
        memberInfoPanel.setPreferredSize(new java.awt.Dimension(748, 475));

        memberTable.setModel(new javax.swing.table.DefaultTableModel(
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
        memberTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        memberTable.setSelectionBackground(new java.awt.Color(157, 0, 57));
        memberTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        memberTable.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        memberTablePanel.setViewportView(memberTable);

        searchMemberGroupPanel.setBackground(new java.awt.Color(244, 244, 244));

        memberSeachByNameTextField.setBackground(new java.awt.Color(255, 156, 192));
        memberSeachByNameTextField.setForeground(new java.awt.Color(0, 0, 0));
        memberSeachByNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberSeachByNameTextFieldActionPerformed(evt);
            }
        });

        memberSearchByNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        memberSearchByNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        memberSearchByNameLabel.setText("Search By Name:");

        memberSearchIDTextField.setBackground(new java.awt.Color(255, 156, 192));
        memberSearchIDTextField.setForeground(new java.awt.Color(0, 0, 0));
        memberSearchIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberSearchIDTextFieldActionPerformed(evt);
            }
        });

        memberSearchIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        memberSearchIDLabel.setForeground(new java.awt.Color(0, 0, 0));
        memberSearchIDLabel.setText("Search By ID:");

        javax.swing.GroupLayout searchMemberGroupPanelLayout = new javax.swing.GroupLayout(searchMemberGroupPanel);
        searchMemberGroupPanel.setLayout(searchMemberGroupPanelLayout);
        searchMemberGroupPanelLayout.setHorizontalGroup(
            searchMemberGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, searchMemberGroupPanelLayout.createSequentialGroup()
                .addComponent(memberSearchIDLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(memberSearchIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 52, Short.MAX_VALUE)
                .addComponent(memberSearchByNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(memberSeachByNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        searchMemberGroupPanelLayout.setVerticalGroup(
            searchMemberGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchMemberGroupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchMemberGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memberSearchIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(memberSearchIDLabel)
                    .addComponent(memberSearchByNameLabel)
                    .addComponent(memberSeachByNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addMemberButton.setBackground(new java.awt.Color(157, 0, 57));
        addMemberButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addMemberButton.setForeground(new java.awt.Color(255, 255, 255));
        addMemberButton.setText("Add Member");
        addMemberButton.setPreferredSize(new java.awt.Dimension(115, 27));
        addMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberButtonActionPerformed(evt);
            }
        });

        deleteMemberButton.setBackground(new java.awt.Color(157, 0, 57));
        deleteMemberButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteMemberButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteMemberButton.setText("Delete Member");
        deleteMemberButton.setPreferredSize(new java.awt.Dimension(115, 27));
        deleteMemberButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteMemberButtonActionPerformed(evt);
            }
        });

        refreshDataButton.setBackground(new java.awt.Color(157, 0, 57));
        refreshDataButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshDataButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshDataButton.setText("Refresh Data");
        refreshDataButton.setPreferredSize(new java.awt.Dimension(115, 27));
        refreshDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshDataButtonActionPerformed(evt);
            }
        });

        memberManagementLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        memberManagementLabel.setForeground(new java.awt.Color(157, 0, 57));
        memberManagementLabel.setText("MEMBER MANAGEMENT");

        javax.swing.GroupLayout memberInfoPanelLayout = new javax.swing.GroupLayout(memberInfoPanel);
        memberInfoPanel.setLayout(memberInfoPanelLayout);
        memberInfoPanelLayout.setHorizontalGroup(
            memberInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberInfoPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(memberInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberInfoPanelLayout.createSequentialGroup()
                        .addComponent(memberManagementLabel)
                        .addGap(254, 254, 254))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberInfoPanelLayout.createSequentialGroup()
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 755, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, memberInfoPanelLayout.createSequentialGroup()
                .addContainerGap(67, Short.MAX_VALUE)
                .addGroup(memberInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(searchMemberGroupPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(memberTablePanel))
                .addGap(18, 18, 18)
                .addGroup(memberInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(memberInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(refreshDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteMemberButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(addMemberButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        memberInfoPanelLayout.setVerticalGroup(
            memberInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(memberInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(memberManagementLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(memberInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(memberInfoPanelLayout.createSequentialGroup()
                        .addComponent(searchMemberGroupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(memberTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(memberInfoPanelLayout.createSequentialGroup()
                        .addComponent(addMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(deleteMemberButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(refreshDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(53, Short.MAX_VALUE))
        );

        memberTablePanel.getAccessibleContext().setAccessibleName("userTablePanel");

        managementPanel.add(memberInfoPanel, "card4");

        bookInfoPanel.setBackground(new java.awt.Color(244, 244, 244));
        bookInfoPanel.setPreferredSize(new java.awt.Dimension(748, 475));

        searchBookGroupPanel.setBackground(new java.awt.Color(244, 244, 244));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Genre:");

        genreComboBox.setBackground(new java.awt.Color(157, 0, 57));
        genreComboBox.setForeground(new java.awt.Color(255, 255, 255));
        genreComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Science", "Fiction", "Comedy" }));

        bookSearchIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bookSearchIDLabel.setForeground(new java.awt.Color(0, 0, 0));
        bookSearchIDLabel.setText("Search By ID:");

        bookSearchIDTextField.setBackground(new java.awt.Color(255, 156, 192));
        bookSearchIDTextField.setForeground(new java.awt.Color(0, 0, 0));
        bookSearchIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookSearchIDTextFieldActionPerformed(evt);
            }
        });

        bookSearchByNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bookSearchByNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        bookSearchByNameLabel.setText("Search By Name:");

        bookSeachByNameTextField.setBackground(new java.awt.Color(255, 156, 192));
        bookSeachByNameTextField.setForeground(new java.awt.Color(0, 0, 0));
        bookSeachByNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookSeachByNameTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchBookGroupPanelLayout = new javax.swing.GroupLayout(searchBookGroupPanel);
        searchBookGroupPanel.setLayout(searchBookGroupPanelLayout);
        searchBookGroupPanelLayout.setHorizontalGroup(
            searchBookGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBookGroupPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(genreComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(bookSearchIDLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookSearchIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(bookSearchByNameLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bookSeachByNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        searchBookGroupPanelLayout.setVerticalGroup(
            searchBookGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchBookGroupPanelLayout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addGroup(searchBookGroupPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(genreComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bookSearchIDLabel)
                    .addComponent(bookSearchIDTextField)
                    .addComponent(bookSearchByNameLabel)
                    .addComponent(bookSeachByNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        bookManagementLabel.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        bookManagementLabel.setForeground(new java.awt.Color(157, 0, 57));
        bookManagementLabel.setText("BOOK MANAGEMENT");

        addBookButton.setBackground(new java.awt.Color(157, 0, 57));
        addBookButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        addBookButton.setForeground(new java.awt.Color(255, 255, 255));
        addBookButton.setText("Add Book");
        addBookButton.setPreferredSize(new java.awt.Dimension(115, 27));
        addBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBookButtonActionPerformed(evt);
            }
        });

        deleteBookButton.setBackground(new java.awt.Color(157, 0, 57));
        deleteBookButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        deleteBookButton.setForeground(new java.awt.Color(255, 255, 255));
        deleteBookButton.setText("Delete Book");
        deleteBookButton.setPreferredSize(new java.awt.Dimension(115, 27));
        deleteBookButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBookButtonActionPerformed(evt);
            }
        });

        refreshBookDataButton.setBackground(new java.awt.Color(157, 0, 57));
        refreshBookDataButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        refreshBookDataButton.setForeground(new java.awt.Color(255, 255, 255));
        refreshBookDataButton.setText("Refresh Data");
        refreshBookDataButton.setPreferredSize(new java.awt.Dimension(115, 27));
        refreshBookDataButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refreshBookDataButtonActionPerformed(evt);
            }
        });

        bookTable.setModel(new javax.swing.table.DefaultTableModel(
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
        bookTable.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);
        bookTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        bookTablePanel.setViewportView(bookTable);

        javax.swing.GroupLayout bookInfoPanelLayout = new javax.swing.GroupLayout(bookInfoPanel);
        bookInfoPanel.setLayout(bookInfoPanelLayout);
        bookInfoPanelLayout.setHorizontalGroup(
            bookInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookInfoPanelLayout.createSequentialGroup()
                .addGroup(bookInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookInfoPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1))
                    .addGroup(bookInfoPanelLayout.createSequentialGroup()
                        .addGap(55, 55, 55)
                        .addComponent(searchBookGroupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(bookInfoPanelLayout.createSequentialGroup()
                .addGroup(bookInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bookInfoPanelLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(bookTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 550, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(bookInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bookInfoPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(bookInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(addBookButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(deleteBookButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(bookInfoPanelLayout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(refreshBookDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(bookInfoPanelLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(bookManagementLabel)))
                .addGap(0, 20, Short.MAX_VALUE))
        );
        bookInfoPanelLayout.setVerticalGroup(
            bookInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bookInfoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(bookManagementLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(searchBookGroupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(bookInfoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bookInfoPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78)
                        .addComponent(deleteBookButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(refreshBookDataButton, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bookInfoPanelLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(bookTablePanel, javax.swing.GroupLayout.PREFERRED_SIZE, 328, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(53, 53, 53))
        );

        managementPanel.add(bookInfoPanel, "card3");

        procedurePanel.setBackground(new java.awt.Color(255, 255, 255));

        checkingProcedure.setBackground(new java.awt.Color(204, 204, 204));

        checkingPanel.setBackground(new java.awt.Color(244, 244, 244));

        memberIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        memberIDLabel.setForeground(new java.awt.Color(0, 0, 0));
        memberIDLabel.setText("Member ID: ");

        infoConfirmLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        infoConfirmLabel.setForeground(new java.awt.Color(157, 0, 57));
        infoConfirmLabel.setText("INFORMATION CONFIRM");

        memberIDTextField.setBackground(new java.awt.Color(255, 156, 192));
        memberIDTextField.setForeground(new java.awt.Color(0, 0, 0));
        memberIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                memberIDTextFieldActionPerformed(evt);
            }
        });

        memberNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        memberNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        memberNameLabel.setText("Name:");

        bookNameTextField.setBackground(new java.awt.Color(255, 156, 192));
        bookNameTextField.setForeground(new java.awt.Color(0, 0, 0));

        bookIDLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bookIDLabel.setForeground(new java.awt.Color(0, 0, 0));
        bookIDLabel.setText("Book ID:");

        availabilityTextField.setBackground(new java.awt.Color(255, 156, 192));
        availabilityTextField.setForeground(new java.awt.Color(0, 0, 0));

        bookNameLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        bookNameLabel.setForeground(new java.awt.Color(0, 0, 0));
        bookNameLabel.setText("Book Name:");

        memberNameTextField.setBackground(new java.awt.Color(255, 156, 192));
        memberNameTextField.setForeground(new java.awt.Color(0, 0, 0));

        availabilityLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        availabilityLabel.setForeground(new java.awt.Color(0, 0, 0));
        availabilityLabel.setText("Availability:");

        bookIDTextField.setBackground(new java.awt.Color(255, 156, 192));
        bookIDTextField.setForeground(new java.awt.Color(0, 0, 0));
        bookIDTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookIDTextFieldActionPerformed(evt);
            }
        });

        checkingButton.setBackground(new java.awt.Color(157, 0, 57));
        checkingButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        checkingButton.setForeground(new java.awt.Color(255, 255, 255));
        checkingButton.setText("CHECKING INFORMATION");
        checkingButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkingButtonActionPerformed(evt);
            }
        });

        procedure.setLayout(new java.awt.CardLayout());

        defaultPanel.setBackground(new java.awt.Color(244, 244, 244));

        javax.swing.GroupLayout defaultPanelLayout = new javax.swing.GroupLayout(defaultPanel);
        defaultPanel.setLayout(defaultPanelLayout);
        defaultPanelLayout.setHorizontalGroup(
            defaultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 353, Short.MAX_VALUE)
        );
        defaultPanelLayout.setVerticalGroup(
            defaultPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 253, Short.MAX_VALUE)
        );

        procedure.add(defaultPanel, "card4");

        returnPanel.setBackground(new java.awt.Color(204, 204, 204));

        returnConfirmLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        returnConfirmLabel.setForeground(new java.awt.Color(157, 0, 57));
        returnConfirmLabel.setText("RETURN BOOK PROCEDURE");

        borrowDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        borrowDateLabel.setForeground(new java.awt.Color(0, 0, 0));
        borrowDateLabel.setText("Borrow Date:");

        borrowDateTextField.setEditable(false);
        borrowDateTextField.setBackground(new java.awt.Color(255, 156, 192));
        borrowDateTextField.setForeground(new java.awt.Color(0, 0, 0));
        borrowDateTextField.setEnabled(false);
        borrowDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowDateTextFieldActionPerformed(evt);
            }
        });

        dueDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dueDateLabel.setForeground(new java.awt.Color(0, 0, 0));
        dueDateLabel.setText("Due Date: ");

        dueDateTextField.setEditable(false);
        dueDateTextField.setBackground(new java.awt.Color(255, 156, 192));
        dueDateTextField.setForeground(new java.awt.Color(0, 0, 0));
        dueDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dueDateTextFieldActionPerformed(evt);
            }
        });

        returnDateLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        returnDateLabel.setForeground(new java.awt.Color(0, 0, 0));
        returnDateLabel.setText("Return Date: ");

        statusTextField.setEditable(false);
        statusTextField.setBackground(new java.awt.Color(255, 156, 192));
        statusTextField.setForeground(new java.awt.Color(0, 0, 0));
        statusTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                statusTextFieldActionPerformed(evt);
            }
        });

        statusLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        statusLabel.setForeground(new java.awt.Color(0, 0, 0));
        statusLabel.setText("Status:");

        returnDateTextField.setEditable(false);
        returnDateTextField.setBackground(new java.awt.Color(255, 156, 192));
        returnDateTextField.setForeground(new java.awt.Color(0, 0, 0));
        returnDateTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnDateTextFieldActionPerformed(evt);
            }
        });

        returnButton.setBackground(new java.awt.Color(157, 0, 57));
        returnButton.setText("Complete Return!");
        returnButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout returnPanelLayout = new javax.swing.GroupLayout(returnPanel);
        returnPanel.setLayout(returnPanelLayout);
        returnPanelLayout.setHorizontalGroup(
            returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, returnPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(returnConfirmLabel)
                .addGap(45, 45, 45))
            .addGroup(returnPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(returnButton)
                    .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(returnPanelLayout.createSequentialGroup()
                            .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(returnDateLabel)
                                .addComponent(statusLabel))
                            .addGap(18, 18, 18)
                            .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(returnPanelLayout.createSequentialGroup()
                                    .addGap(0, 0, Short.MAX_VALUE)
                                    .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(returnPanelLayout.createSequentialGroup()
                                    .addComponent(returnDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(returnPanelLayout.createSequentialGroup()
                            .addComponent(dueDateLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(dueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(returnPanelLayout.createSequentialGroup()
                            .addComponent(borrowDateLabel)
                            .addGap(18, 18, 18)
                            .addComponent(borrowDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        returnPanelLayout.setVerticalGroup(
            returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(returnPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(returnConfirmLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrowDateLabel)
                    .addComponent(borrowDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueDateLabel)
                    .addComponent(dueDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(returnDateLabel)
                    .addComponent(returnDateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(returnPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusLabel)
                    .addComponent(statusTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(returnButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        procedure.add(returnPanel, "card2");

        borrowPanel.setBackground(new java.awt.Color(204, 204, 204));

        borrowLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        borrowLabel.setForeground(new java.awt.Color(157, 0, 57));
        borrowLabel.setText("BORROW BOOK PROCEDURE");

        borrowDurationLabel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        borrowDurationLabel.setForeground(new java.awt.Color(0, 0, 0));
        borrowDurationLabel.setText("Borrow Duration:");

        borrowDurationComboBox.setBackground(new java.awt.Color(157, 0, 57));
        borrowDurationComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "One week", "Two week", "Three week", "Four week" }));
        borrowDurationComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowDurationComboBoxActionPerformed(evt);
            }
        });

        borrowDate2Label.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        borrowDate2Label.setForeground(new java.awt.Color(0, 0, 0));
        borrowDate2Label.setText("Borrow Date:");

        borrowDate2TextField.setBackground(new java.awt.Color(255, 156, 192));
        borrowDate2TextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowDate2TextFieldActionPerformed(evt);
            }
        });

        dueDate2Label.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        dueDate2Label.setForeground(new java.awt.Color(0, 0, 0));
        dueDate2Label.setText("Due Date:");

        dueDate2TextField.setBackground(new java.awt.Color(255, 156, 192));

        borrowButton.setBackground(new java.awt.Color(157, 0, 57));
        borrowButton.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        borrowButton.setForeground(new java.awt.Color(255, 255, 255));
        borrowButton.setText("COMPLETE BORROW!");
        borrowButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrowButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout borrowPanelLayout = new javax.swing.GroupLayout(borrowPanel);
        borrowPanel.setLayout(borrowPanelLayout);
        borrowPanelLayout.setHorizontalGroup(
            borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowPanelLayout.createSequentialGroup()
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(borrowPanelLayout.createSequentialGroup()
                        .addGap(49, 49, 49)
                        .addComponent(borrowLabel))
                    .addGroup(borrowPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(borrowDurationLabel)
                            .addComponent(borrowDate2Label)
                            .addComponent(dueDate2Label)
                            .addComponent(borrowStatusLabel))
                        .addGap(18, 18, 18)
                        .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dueDate2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowDurationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(borrowDate2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(borrowPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(borrowButton)))
                .addContainerGap(42, Short.MAX_VALUE))
        );
        borrowPanelLayout.setVerticalGroup(
            borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(borrowPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(borrowLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(borrowStatusLabel)
                .addGap(18, 18, 18)
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrowDurationLabel)
                    .addComponent(borrowDurationComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(borrowDate2Label)
                    .addComponent(borrowDate2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(borrowPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dueDate2TextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dueDate2Label))
                .addGap(18, 18, 18)
                .addComponent(borrowButton)
                .addGap(19, 19, 19))
        );

        procedure.add(borrowPanel, "card3");

        javax.swing.GroupLayout checkingPanelLayout = new javax.swing.GroupLayout(checkingPanel);
        checkingPanel.setLayout(checkingPanelLayout);
        checkingPanelLayout.setHorizontalGroup(
            checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkingPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(infoConfirmLabel)
                .addGap(66, 66, 66))
            .addComponent(procedure, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(checkingPanelLayout.createSequentialGroup()
                .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(checkingPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(memberIDLabel)
                            .addComponent(memberNameLabel)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(availabilityLabel)
                                .addComponent(bookNameLabel)
                                .addComponent(bookIDLabel)))
                        .addGap(21, 21, 21)
                        .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(bookNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                            .addComponent(memberNameTextField)
                            .addComponent(bookIDTextField)
                            .addComponent(memberIDTextField)
                            .addComponent(availabilityTextField)))
                    .addGroup(checkingPanelLayout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(checkingButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, checkingPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator4)
                .addContainerGap())
        );
        checkingPanelLayout.setVerticalGroup(
            checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(checkingPanelLayout.createSequentialGroup()
                .addComponent(infoConfirmLabel)
                .addGap(2, 2, 2)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memberIDLabel)
                    .addComponent(memberIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(memberNameLabel)
                    .addComponent(memberNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookIDLabel)
                    .addComponent(bookIDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bookNameLabel)
                    .addComponent(bookNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(checkingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(availabilityLabel)
                    .addComponent(availabilityTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(checkingButton)
                .addGap(0, 0, 0)
                .addComponent(procedure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout checkingProcedureLayout = new javax.swing.GroupLayout(checkingProcedure);
        checkingProcedure.setLayout(checkingProcedureLayout);
        checkingProcedureLayout.setHorizontalGroup(
            checkingProcedureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(checkingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        checkingProcedureLayout.setVerticalGroup(
            checkingProcedureLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(checkingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        loanTransactionPanel.setBackground(new java.awt.Color(204, 204, 204));

        loanTransactionLabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        loanTransactionLabel.setForeground(new java.awt.Color(157, 0, 57));
        loanTransactionLabel.setText("LOAN TRANSACTION");

        loanTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(loanTable);

        javax.swing.GroupLayout loanTransactionPanelLayout = new javax.swing.GroupLayout(loanTransactionPanel);
        loanTransactionPanel.setLayout(loanTransactionPanelLayout);
        loanTransactionPanelLayout.setHorizontalGroup(
            loanTransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loanTransactionPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator3)
                .addContainerGap())
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(loanTransactionPanelLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addComponent(loanTransactionLabel)
                .addContainerGap(112, Short.MAX_VALUE))
        );
        loanTransactionPanelLayout.setVerticalGroup(
            loanTransactionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loanTransactionPanelLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(loanTransactionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 385, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout procedurePanelLayout = new javax.swing.GroupLayout(procedurePanel);
        procedurePanel.setLayout(procedurePanelLayout);
        procedurePanelLayout.setHorizontalGroup(
            procedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, procedurePanelLayout.createSequentialGroup()
                .addComponent(loanTransactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(checkingProcedure, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        procedurePanelLayout.setVerticalGroup(
            procedurePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(checkingProcedure, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(loanTransactionPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        managementPanel.add(procedurePanel, "card5");

        jPanel1.setBackground(new java.awt.Color(145, 0, 57));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("TROY LIBRARY MANAGEMENT SYSTEM");

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/adminIcons/male_user_50px.png"))); // NOI18N

        welcomeLabel.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        welcomeLabel.setForeground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 413, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(welcomeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 57, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(welcomeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(managementButtonGroupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 228, Short.MAX_VALUE)
                    .addComponent(managementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(managementButtonGroupPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addGap(0, 56, Short.MAX_VALUE)
                    .addComponent(managementPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void userManagementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userManagementButtonActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) managementPanel.getLayout();
        cardLayout.show(managementPanel, "card4");
    }//GEN-LAST:event_userManagementButtonActionPerformed

    private void logOutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOutButtonActionPerformed
        // TODO add your handling code here:
        SignInFrame frame = new SignInFrame();
        frame.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logOutButtonActionPerformed

    private void bookManagementButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookManagementButtonActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) managementPanel.getLayout();
        cardLayout.show(managementPanel, "card3");
    }//GEN-LAST:event_bookManagementButtonActionPerformed

    private void bookSearchIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookSearchIDTextFieldActionPerformed
        // TODO add your handling code here:
        if (bookSearchIDTextField.getText().matches("-?\\d+")){
            int bookID = Integer.parseInt(bookSearchIDTextField.getText());
            Book searchedBook = adminService.searchBookByID(bookID);
            
            if(searchedBook != null){
                defaultBookTableModel.setRowCount(0);
                setBookTableData(searchedBook);
            } else {
                JOptionPane.showMessageDialog( null, "NOT FOUND!" );
            }
        }
    }//GEN-LAST:event_bookSearchIDTextFieldActionPerformed

    private void bookSeachByNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookSeachByNameTextFieldActionPerformed
        // TODO add your handling code here:
        List<Book> searchedBook = new ArrayList<Book>();
        searchedBook = adminService.searchBookByName(bookSeachByNameTextField.getText());
        
        if (!searchedBook.isEmpty()){
            defaultBookTableModel.setRowCount(0);
            for(Book book : searchedBook) {
                setBookTableData(book);
            }
        } else {
            JOptionPane.showMessageDialog( null, "NOT FOUND!" );
        }
    }//GEN-LAST:event_bookSeachByNameTextFieldActionPerformed

    private void addBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addBookButtonActionPerformed
        // TODO add your handling code here:
        new AddBookFrame().setVisible(true);
    }//GEN-LAST:event_addBookButtonActionPerformed

    private void deleteBookButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteBookButtonActionPerformed
        // TODO add your handling code here:
        int row = bookTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(ManageMentFrame.this, 
                                          "Please choose book to remove!", 
                                          "WARNING", 
                                          JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(ManageMentFrame.this, 
                                                         "Do you want to remove this book?",
                                                         "DELETE BOOK CONFRIM",
                                                         JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int bookID = (int) bookTable.getValueAt(row, 0);
                adminService.deleteBook(bookID);
                
                defaultBookTableModel.setRowCount(0);
                List<Book> books = adminService.getAllBook();
                for (Book book : books) {
                    setBookTableData(book);
                }
            }
        }
    }//GEN-LAST:event_deleteBookButtonActionPerformed

    private void refreshBookDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshBookDataButtonActionPerformed
        // TODO add your handling code here:
        defaultBookTableModel.setRowCount(0);
        List<Book> books = adminService.getAllBook();
        for (Book book : books) {
            setBookTableData(book);
        }
    }//GEN-LAST:event_refreshBookDataButtonActionPerformed

    private void refreshDataButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshDataButtonActionPerformed
        // TODO add your handling code here:
        defaulMemberTableModel.setRowCount(0);
        List<Member> members = adminService.getAllMember();
        for (Member member : members) {
            setMemberTableData(member);
        }
    }//GEN-LAST:event_refreshDataButtonActionPerformed

    private void deleteMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteMemberButtonActionPerformed
        // TODO add your handling code here:
        int row = memberTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(ManageMentFrame.this,
                "Please choose member to delete!",
                "WARNING",
                JOptionPane.ERROR_MESSAGE);
        } else {
            int confirm = JOptionPane.showConfirmDialog(ManageMentFrame.this,
                "Do you want to delete this member?",
                "DELETE MEMBER CONFRIM",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                int memberID = (int) memberTable.getValueAt(row, 0);
                adminService.deleteMember(memberID);

                defaulMemberTableModel.setRowCount(0);
                List<Member> members = adminService.getAllMember();
                for (Member member : members) {
                    setMemberTableData(member);
                }
            }
        }
    }//GEN-LAST:event_deleteMemberButtonActionPerformed

    private void addMemberButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addMemberButtonActionPerformed
        // TODO add your handling code here:
        new AddMemberFrame().setVisible(true);
    }//GEN-LAST:event_addMemberButtonActionPerformed

    private void memberSeachByNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberSeachByNameTextFieldActionPerformed
        // TODO add your handling code here:
        List<Member> searchedMember = new ArrayList<Member>();
        searchedMember = adminService.searchMemberByName(memberSeachByNameTextField.getText());

        if (!searchedMember.isEmpty()){
            defaulMemberTableModel.setRowCount(0);
            for(Member user : searchedMember) {
                setMemberTableData(user);
            }
        } else {
            JOptionPane.showMessageDialog( null, "NOT FOUND!" );
        }
    }//GEN-LAST:event_memberSeachByNameTextFieldActionPerformed

    private void memberSearchIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberSearchIDTextFieldActionPerformed
        // TODO add your handling code here:
        if (memberSearchIDTextField.getText().matches("-?\\d+")){
            int memberID = Integer.parseInt(memberSearchIDTextField.getText());
            Member searchedMember = adminService.searchMemberByID(memberID);

            if(searchedMember != null){
                defaulMemberTableModel.setRowCount(0);
                setMemberTableData(searchedMember);
            } else {
                JOptionPane.showMessageDialog( null, "NOT FOUND!" );
            }
        }

    }//GEN-LAST:event_memberSearchIDTextFieldActionPerformed

    private void procedureButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_procedureButtonActionPerformed
        // TODO add your handling code here:
        CardLayout cardLayout = (CardLayout) managementPanel.getLayout();
        cardLayout.show(managementPanel, "card5");
    }//GEN-LAST:event_procedureButtonActionPerformed

    private void memberIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_memberIDTextFieldActionPerformed
        // TODO add your handling code here:
        int memberID = Integer.parseInt(memberIDTextField.getText());
        memberProcedure = adminService.getMemberByID(memberID);
        if(memberProcedure == null) {
            JOptionPane.showMessageDialog(null, 
                                          "Member not found", 
                                          "Warning", 
                                          JOptionPane.WARNING_MESSAGE);
        } else {
            memberNameTextField.setText(memberProcedure.getFullName());
            memberProcedureLoanTransaction = adminService.getMemberLoanTransactionByMemberID(memberID);
            defaultLoanTableModel.setRowCount(0);
            for(Loan loan : memberProcedureLoanTransaction) {
                setLoanTableData(loan);
            }
        }
    }//GEN-LAST:event_memberIDTextFieldActionPerformed

    private void bookIDTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookIDTextFieldActionPerformed
        // TODO add your handling code here:
        int bookID = Integer.parseInt(bookIDTextField.getText());
        bookProcedure = adminService.getBookByID(bookID);
        if(bookProcedure == null) {
            JOptionPane.showMessageDialog(null, 
                                          "Book not found", 
                                          "Warning", 
                                          JOptionPane.WARNING_MESSAGE);
        } else {
            bookNameTextField.setText(bookProcedure.getBookName());
            availabilityTextField.setText(String.valueOf(bookProcedure.getAvailability()));
        }
    }//GEN-LAST:event_bookIDTextFieldActionPerformed

    private void checkingButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkingButtonActionPerformed
        // TODO add your handling code here:
        if (memberProcedureLoanTransaction.isEmpty()) {
            // Case borrowBook
                CardLayout cardLayout = (CardLayout) procedure.getLayout();
                cardLayout.show(procedure, "card3");
                
                if(bookProcedure.getAvailability() != 0) {
                    borrowStatusLabel.setText("Status: Available");
                    borrowStatusLabel.setForeground(Color.green);
                } else {
                    borrowStatusLabel.setText("Status: Not Available!");
                    borrowStatusLabel.setForeground(Color.red);
                }
                
        } else {
            for (Loan loan: memberProcedureLoanTransaction) {
                if (loan.getIdBook() == bookProcedure.getBookId() && loan.getReturnDate() == null) {
                    // Case return book
                    loanProcedure = loan;
                    CardLayout cardLayout = (CardLayout) procedure.getLayout();
                    cardLayout.show(procedure, "card2");
                
                    Date dueDate = loan.getDueDate();
                    Date returnDate = Date.valueOf(LocalDate.now());
                    borrowDateTextField.setText(loan.getLoanDate().toString());
                    dueDateTextField.setText(dueDate.toString());
                    returnDateTextField.setText(returnDate.toString());
                
                    int comparisonResult = returnDate.compareTo(dueDate);
                    if (comparisonResult <= 0 ) {
                        statusTextField.setText("On Time");
                    } else {
                        statusTextField.setText("Late");
                    }                
                
                } else if (loan.getIdBook() != bookProcedure.getBookId()) {
                    // Case borrowBook
                    CardLayout cardLayout = (CardLayout) procedure.getLayout();
                    cardLayout.show(procedure, "card3");
                
                    if(bookProcedure.getAvailability() != 0) {
                        borrowStatusLabel.setText("Status: Available");
                        borrowStatusLabel.setForeground(Color.green);
                    } else {
                    borrowStatusLabel.setText("Status: Not Available!");
                    borrowStatusLabel.setForeground(Color.red);
                    }
                
                
                } else if (loan.getIdBook() == bookProcedure.getBookId() && loan.getReturnDate() != null) {
                    // Case borrowBook
                    CardLayout cardLayout = (CardLayout) procedure.getLayout();
                    cardLayout.show(procedure, "card3");
                
                    if(bookProcedure.getAvailability() != 0) {
                        borrowStatusLabel.setText("Status: Available");
                        borrowStatusLabel.setForeground(Color.green);
                    } else {
                        borrowStatusLabel.setText("Status: Not Available!");
                        borrowStatusLabel.setForeground(Color.red);
                    }
                }
            }
        }
    }//GEN-LAST:event_checkingButtonActionPerformed

    private void borrowDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_borrowDateTextFieldActionPerformed

    private void dueDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dueDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_dueDateTextFieldActionPerformed

    private void statusTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_statusTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_statusTextFieldActionPerformed

    private void returnDateTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnDateTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnDateTextFieldActionPerformed

    private void borrowDate2TextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowDate2TextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_borrowDate2TextFieldActionPerformed

    private void returnButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnButtonActionPerformed
        // TODO add your handling code here
       adminService.returnBook(loanProcedure.getLoanId(), Date.valueOf(returnDateTextField.getText()));
       JOptionPane.showMessageDialog(null, 
                                     "Return Success!", 
                                     "Success", 
                                     JOptionPane.INFORMATION_MESSAGE);
       int memberID = Integer.parseInt(memberIDTextField.getText());
       memberProcedureLoanTransaction = adminService.getMemberLoanTransactionByMemberID(memberID);
            defaultLoanTableModel.setRowCount(0);
            for(Loan loan : memberProcedureLoanTransaction) {
                setLoanTableData(loan);
            }
       
    }//GEN-LAST:event_returnButtonActionPerformed

    private void borrowDurationComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowDurationComboBoxActionPerformed
        // TODO add your handling code here:
        borrowDate2TextField.setText(LocalDate.now().toString());
                
        if(borrowDurationComboBox.getSelectedItem().equals("One week")) {
        dueDate2TextField.setText(LocalDate.now().plusWeeks(1).toString());
        } else if (borrowDurationComboBox.getSelectedItem().equals("Two week")) {
            dueDate2TextField.setText(LocalDate.now().plusWeeks(2).toString());
        } else if (borrowDurationComboBox.getSelectedItem().equals("Three week")) {
            dueDate2TextField.setText(LocalDate.now().plusWeeks(3).toString());
        } else if (borrowDurationComboBox.getSelectedItem().equals("Four week")) {
            dueDate2TextField.setText(LocalDate.now().plusWeeks(4).toString());
        }
    }//GEN-LAST:event_borrowDurationComboBoxActionPerformed

    private void borrowButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrowButtonActionPerformed
        // TODO add your handling code here:
        if (borrowStatusLabel.getText().equals("Status: Not Available!")) {
            JOptionPane.showMessageDialog(null, 
                                          "Book is not available", 
                                          "Warning", 
                                          JOptionPane.WARNING_MESSAGE);
        } else {
            loanProcedure.setIdBook(bookProcedure.getBookId());
            loanProcedure.setIdMember(memberProcedure.getMemberID());
            loanProcedure.setLoanDate(Date.valueOf(borrowDate2TextField.getText()));
            loanProcedure.setDueDate(Date.valueOf(dueDate2TextField.getText()));
            adminService.loanBook(loanProcedure);
            JOptionPane.showMessageDialog(null, 
                                              "Borrow Success!", 
                                              "Success", 
                                              JOptionPane.INFORMATION_MESSAGE);
            int memberID = Integer.parseInt(memberIDTextField.getText());
            memberProcedureLoanTransaction = adminService.getMemberLoanTransactionByMemberID(memberID);
            defaultLoanTableModel.setRowCount(0);
            for(Loan loan : memberProcedureLoanTransaction) {
                setLoanTableData(loan);
            }
            
        }
    }//GEN-LAST:event_borrowButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addBookButton;
    private javax.swing.JButton addMemberButton;
    private javax.swing.JLabel availabilityLabel;
    private javax.swing.JTextField availabilityTextField;
    private javax.swing.JLabel bookIDLabel;
    private javax.swing.JTextField bookIDTextField;
    private javax.swing.JPanel bookInfoPanel;
    private javax.swing.JButton bookManagementButton;
    private javax.swing.JLabel bookManagementLabel;
    private javax.swing.JLabel bookNameLabel;
    private javax.swing.JTextField bookNameTextField;
    private javax.swing.JTextField bookSeachByNameTextField;
    private javax.swing.JLabel bookSearchByNameLabel;
    private javax.swing.JLabel bookSearchIDLabel;
    private javax.swing.JTextField bookSearchIDTextField;
    private javax.swing.JTable bookTable;
    private javax.swing.JScrollPane bookTablePanel;
    private javax.swing.JButton borrowButton;
    private javax.swing.JLabel borrowDate2Label;
    private javax.swing.JTextField borrowDate2TextField;
    private javax.swing.JLabel borrowDateLabel;
    private javax.swing.JTextField borrowDateTextField;
    private javax.swing.JComboBox<String> borrowDurationComboBox;
    private javax.swing.JLabel borrowDurationLabel;
    private javax.swing.JLabel borrowLabel;
    private javax.swing.JPanel borrowPanel;
    private javax.swing.JLabel borrowStatusLabel;
    private javax.swing.JButton checkingButton;
    private javax.swing.JPanel checkingPanel;
    private javax.swing.JPanel checkingProcedure;
    private javax.swing.JPanel defaultPanel;
    private javax.swing.JButton deleteBookButton;
    private javax.swing.JButton deleteMemberButton;
    private javax.swing.JLabel dueDate2Label;
    private javax.swing.JTextField dueDate2TextField;
    private javax.swing.JLabel dueDateLabel;
    private javax.swing.JTextField dueDateTextField;
    private javax.swing.JComboBox<String> genreComboBox;
    private javax.swing.JLabel infoConfirmLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable loanTable;
    private javax.swing.JLabel loanTransactionLabel;
    private javax.swing.JPanel loanTransactionPanel;
    private javax.swing.JButton logOutButton;
    private javax.swing.JPanel managementButtonGroupPanel;
    private javax.swing.JPanel managementPanel;
    private javax.swing.JLabel memberIDLabel;
    private javax.swing.JTextField memberIDTextField;
    private javax.swing.JPanel memberInfoPanel;
    private javax.swing.JLabel memberManagementLabel;
    private javax.swing.JLabel memberNameLabel;
    private javax.swing.JTextField memberNameTextField;
    private javax.swing.JTextField memberSeachByNameTextField;
    private javax.swing.JLabel memberSearchByNameLabel;
    private javax.swing.JLabel memberSearchIDLabel;
    private javax.swing.JTextField memberSearchIDTextField;
    private javax.swing.JTable memberTable;
    private javax.swing.JScrollPane memberTablePanel;
    private javax.swing.JPanel procedure;
    private javax.swing.JButton procedureButton;
    private javax.swing.JPanel procedurePanel;
    private javax.swing.JButton refreshBookDataButton;
    private javax.swing.JButton refreshDataButton;
    private javax.swing.JButton returnButton;
    private javax.swing.JLabel returnConfirmLabel;
    private javax.swing.JLabel returnDateLabel;
    private javax.swing.JTextField returnDateTextField;
    private javax.swing.JPanel returnPanel;
    private javax.swing.JPanel searchBookGroupPanel;
    private javax.swing.JPanel searchMemberGroupPanel;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JTextField statusTextField;
    private javax.swing.JButton userManagementButton;
    private javax.swing.JLabel welcomeLabel;
    // End of variables declaration//GEN-END:variables
}
