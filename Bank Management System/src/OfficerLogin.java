import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class OfficerStatusPanel extends JPanel {
    private JLabel nameLabel, statusLabel;
    private JLabel nameValue, activeStatus;

    public OfficerStatusPanel(String name, String password) {
        // Creating the components
        nameLabel = new JLabel("Name");
        nameValue = new JLabel(name.toUpperCase());
        statusLabel = new JLabel("Status: ");
        activeStatus = new JLabel(DB.getOfficerStatus(name, password).toUpperCase());

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Officer Status Information");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Set Grid Layout
        setLayout(new GridLayout(2, 2));
        add(nameLabel);
        add(nameValue);
        add(statusLabel);
        add(activeStatus);
    }
}

class OfficerStatusFrame extends JFrame {
    private OfficerStatusPanel officerStatusPanel;
    public OfficerStatusFrame(String title, String name, String password) {
        // Frame Creation
        super(title);
        setSize(300, 150);
        setLocation(360, 50);

        // Creating the components
        officerStatusPanel = new OfficerStatusPanel(name, password);

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(officerStatusPanel);

        setVisible(true);
    }
}

class BalancePanel extends JPanel {
    private JLabel nameLabel, accountLabel, transactionLabel, statusLabel, balanceLabel;
    private JLabel nameValue, accountNo, transactionState, activeStatus, balance;

    public BalancePanel(String name, String account) {
        // Creating the components
        nameLabel = new JLabel("Name");
        nameValue = new JLabel(name.toUpperCase());
        accountLabel = new JLabel("Account No.");
        accountNo = new JLabel(account);
        // Getting the status value
        String currentStatus = DB.userStatusByAccount(name, account);
        transactionLabel = new JLabel("Transaction");
        transactionState = new JLabel((currentStatus.equals("logout")) ? "Disable" : "Enable");
        statusLabel = new JLabel("Status");
        activeStatus = new JLabel(currentStatus.toUpperCase());
        balanceLabel = new JLabel("Balance");
        balance = new JLabel(DB.userBalanceByAccount(name, account));


        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("User Account Information");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Set Grid Layout
        setLayout(new GridLayout(5, 2));
        add(nameLabel);
        add(nameValue);
        add(accountLabel);
        add(accountNo);
        add(transactionLabel);
        add(transactionState);
        add(statusLabel);
        add(activeStatus);
        add(balanceLabel);
        add(balance);
    }
}

class BalanceArea extends JFrame {
    private BalancePanel balancePanel;
    public BalanceArea(String title, String userName, String accountNO) {
        // Frame Creation
        super(title);
        setSize(400, 300);
        setLocation(770, 200);

        // Creating the components
        balancePanel = new BalancePanel(userName, accountNO);

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(balancePanel);

        setVisible(true);
    }
}

class UserTransactionForm extends JPanel {
    private JLabel nameLabel, accountLabel, amountLabel;
    private JTextField nameField, accountField, amountField;

    private JButton  deposite, withdraw, user_info ,officer_status, logout, register;

    public UserTransactionForm(String officer_name, String officer_password) {
        // Get the dimection of FormPanel
        Dimension dim = getPreferredSize();
        // Set the width
        dim.width = 250;
        // Reset the FormPanel Size
        setPreferredSize(dim);

        nameLabel = new JLabel("User Name: ");
        accountLabel = new JLabel("Account No: ");
        amountLabel = new JLabel("Amount: ");
        nameField = new JTextField(10);
        accountField = new JTextField(10);
        amountField = new JTextField(10);
        deposite = new JButton("Deposite");
        withdraw = new JButton("Withdraw");
        logout = new JButton("Officer Logout");
        user_info = new JButton("User Info");
        officer_status = new JButton("Officer Status");
        register = new JButton("Add Officer");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Transaction Information");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));


        // Set GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        // First Row
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,5,0);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        // Second Row
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,5,0);
        add(accountLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(accountField, gc);

        // Third Row
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(amountLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(amountField, gc);

        // Fourth Row
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        add(deposite, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        add(withdraw, gc);

        // Fifth Row
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        add(register, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        add(user_info, gc);

        // Sixth Row
        gc.gridx = 0;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_END;
        add(officer_status, gc);

        gc.gridx = 1;
        gc.gridy = 5;
        gc.anchor = GridBagConstraints.LINE_END;
        add(logout, gc);


        // Add Button Event
        user_info.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String accountNo = accountField.getText();
                String officer_status = DB.getOfficerStatus(officer_name, officer_password);
                if(DB.accountExist(name, accountNo) && officer_status.equals("login")) {
                    // System.out.println("Valid");
                    new BalanceArea("User Information", name, accountNo);
                }
            }
        });

        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB.setOfficerStatus(officer_name, officer_password, "logout");
            }
        });

        deposite.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String accountNo = accountField.getText();
                String amount = amountField.getText().equals("") ? "0" : amountField.getText();

                if(DB.accountExist(name, accountNo)) {
                    Integer intAmount = new Integer(Integer.parseInt(amount));
                    Integer intBalance = new Integer(Integer.parseInt(DB.userBalanceByAccount(name, accountNo)));
                    String officer_status = DB.getOfficerStatus(officer_name, officer_password);
                    String currentStatus = DB.userStatusByAccount(name, accountNo);
                    boolean isON = currentStatus.equals("login");
                    if(intAmount > 0 && isON && officer_status.equals("login")) {
                        Integer currentBalance = intBalance + intAmount;
                        DB.updateUserBalance(name, accountNo, currentBalance);
                    }
                }
            }
        });

        withdraw.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println("Withdraw");
                String name = nameField.getText();
                String accountNo = accountField.getText();
                String amount = amountField.getText().equals("")? "0" : amountField.getText();
                // System.out.println(name + " " + accountNo + " " + amount);

                if(DB.accountExist(name, accountNo)) {
                    Integer intAmount = new Integer(Integer.parseInt(amount));
                    Integer intBalance = new Integer(Integer.parseInt(DB.userBalanceByAccount(name, accountNo)));
                    String officer_status = DB.getOfficerStatus(officer_name, officer_password);
                    String currentStatus = DB.userStatusByAccount(name, accountNo);
                    boolean isON = currentStatus.equals("login");
                    // System.out.println(intAmount + " " + intBalance);
                    if(intBalance >= intAmount && isON && officer_status.equals("login")) {
                        Integer currentBalance = intBalance - intAmount;
                        DB.updateUserBalance(name, accountNo, currentBalance);
                    }
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new OfficerRegistration("Officer Registration");
            }
        });

        officer_status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(DB.isValidOfficer(officer_name, officer_password)) {
                    new OfficerStatusFrame("Officer Status", officer_name, officer_password);
                }
            }
        });
    }
}

class UserTransaction extends JFrame {
    private UserTransactionForm userTransactionForm;
    public UserTransaction(String title, String officer_name, String officer_password) {
        // Frame Creation
        super(title);
        setSize(400, 300);
        setLocation(360, 200);

        // Creating the components
        userTransactionForm = new UserTransactionForm(officer_name, officer_password);

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(userTransactionForm);

        setVisible(true);
    }
}


class OfficerLoginForm extends JPanel {
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton login;

    public OfficerLoginForm() {
        // Get the dimection of FormPanel
        Dimension dim = getPreferredSize();
        // Set the width
        dim.width = 250;
        // Reset the FormPanel Size
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        passwordLabel = new JLabel("Password: ");
        nameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        login = new JButton("Login");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Officer Login");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));


        // Set GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        // First Row
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,5,0);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        // Second Row
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,5,0);
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(passwordField, gc);

        // Third Row
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(login, gc);


        // Add button event
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = passwordField.getText();
                if(DB.isValidOfficer(name, password)) {
                    // System.out.println("Valid");
                    new UserTransaction("User Transaction", name, password);
                    DB.setOfficerStatus(name, password, "login");
                }
            }
        });
    }
}

public class OfficerLogin extends JFrame {
    private OfficerLoginForm officerLoginForm;
    public OfficerLogin(String title) {
        // Frame Creation
        super(title);
        setSize(300, 300);
        setLocation(50, 50);

        // Creating form panel
        officerLoginForm = new OfficerLoginForm();

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(officerLoginForm);
        setVisible(true);
    }
}
