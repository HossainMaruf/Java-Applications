import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class UserStatusPanel extends JPanel {
    private JLabel nameLabel, accountLabel, transactionLabel, statusLabel, balanceLabel;
    private JLabel nameValue, accountNo, transactionState, activeStatus, balance, info_text;
    private JButton logout;
    public UserStatusPanel(String name, String password) {
        // Creating the components
        nameLabel = new JLabel("Name");
        nameValue = new JLabel(name.toUpperCase());
        accountLabel = new JLabel("Account No.");
        accountNo = new JLabel(DB.getAccountNo(name, password));
        // Getting the status value
        String currentStatus = DB.getStatus(name, password);
        transactionLabel = new JLabel("Transaction");
        transactionState = new JLabel((currentStatus.equals("logout"))? "Disable" : "Enable");
        statusLabel = new JLabel("Status");
        activeStatus = new JLabel(currentStatus.toUpperCase());
        balanceLabel = new JLabel("Balance");
        balance = new JLabel(DB.getBalance(name, password));
        info_text = new JLabel((currentStatus.equals("logout")? "Transaction is OFF" : "Transaction is ON"));
        logout = new JButton("Logout");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("User Details");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Set Grid Layout
        setLayout(new GridLayout(6,2));
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
        add(info_text);
        add(logout);

        // Add event to logout
        logout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DB.setStatus(name, password, "logout");
                new StatusInfo("Details", name, password, 50);
            }
        });
    }
}

class StatusInfo extends JFrame {
    private UserStatusPanel user_status_panel;
    public StatusInfo(String title, String name, String password) {
        // Frame Creation
        super(title);
        setSize(400, 300);
        setLocation(360, 300);

        // Creating the components
        user_status_panel = new UserStatusPanel(name, password);

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(user_status_panel);

        setVisible(true);
    }

    public StatusInfo(String title, String name, String password, int goDown) {
        // Frame Creation
        super(title);
        setSize(400, 300);
        setLocation(360, 300 + goDown);

        // Creating the components
        user_status_panel = new UserStatusPanel(name, password);

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(user_status_panel);

        setVisible(true);
    }
}

class FormPanel extends JPanel {
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton login, register;

    public FormPanel() {
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
        register = new JButton("Register Now");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("User Login");
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
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(register, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(login, gc);


        // Add event to Login button
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = passwordField.getText();
                if(DB.isValidUser(name, password)) {
                    DB.setStatus(name, password, "login");
                    new StatusInfo("Details", name, password);
                }
            }
        });

        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserRegistration("Register User");
            }
        });
    }
}

public class UserLogin extends JFrame {
    // Some Panel
    private FormPanel formPanel;
    public UserLogin(String title) {
        // Frame Creation
        super(title);
        setSize(300, 300);
        setLocation(50, 300);

        // Creating form panel
        formPanel = new FormPanel();

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(formPanel);
        setVisible(true);
    }
}
