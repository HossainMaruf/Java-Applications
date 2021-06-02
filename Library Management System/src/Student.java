import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StudentLogin extends JPanel{
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton dashBoard, createAccount;
    public StudentLogin() {
            nameLabel = new JLabel("Name: ");
            passwordLabel = new JLabel("Password: ");
            nameField = new JTextField(10);
            passwordField = new JPasswordField(10);
            dashBoard = new JButton("DASHBOARD");
            createAccount = new JButton("Get Membership");

            // Set the border using BorderFactory
            Border innerBorder = BorderFactory.createTitledBorder("Login");
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
            add(createAccount, gc);

            gc.gridx = 1;
            gc.gridy = 2;
            gc.anchor = GridBagConstraints.LINE_END;
            add(dashBoard, gc);

            // Add the button event
            dashBoard.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String name = nameField.getText();
                    String password = passwordField.getText();
                    if(DB.isValidStudent(name, password)) {
                        // System.out.println("Valid");
                        new StudentDashboard("Dashboard");
                    }
                }
            });
        createAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentRegistration("Student");
            }
        });
        }
}


public class Student extends JFrame {
    private StudentLogin studentLogin;
    public Student(String title) {
        // Frame Creation
        super(title);
        setSize(300, 300);
        setLocation(50, 250);

        // Creating form panel
        studentLogin = new StudentLogin();

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(studentLogin);
        setVisible(true);
    }
}
