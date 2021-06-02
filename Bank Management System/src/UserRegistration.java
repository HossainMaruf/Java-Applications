import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class UserRegistrationForm extends JPanel {
    private JLabel nameLabel, passwordLabel, accountLabel, balanceLabel, info_text;
    private JTextField nameField, accountField, balanceField;
    private JPasswordField passwordField;
    private JButton registerBtn;

    public UserRegistrationForm() {
        // Create the components
        nameLabel = new JLabel("Name: ");
        passwordLabel = new JLabel("Password: ");
        accountLabel = new JLabel("Account NO: ");
        balanceLabel = new JLabel("Balance: ");
        info_text = new JLabel("Plz Check Status After Register");

        nameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        accountField = new JTextField(10);
        balanceField = new JTextField(10);

        registerBtn = new JButton("SignUp");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Registration");
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
        gc.anchor = GridBagConstraints.LINE_END;
        add(accountLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_START;
        add(accountField, gc);

        // Fourth Row
        gc.gridx = 0;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        add(balanceLabel, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_START;
        add(balanceField, gc);

        // Fifth Row
        gc.gridx = 0;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_START;
        add(info_text, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        gc.anchor = GridBagConstraints.LINE_END;
        add(registerBtn, gc);

        // Add registerBtn event
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(new Random().nextInt(hashCode()));
                String name = nameField.getText();
                String password = passwordField.getText();
                String accountNo = accountField.getText();
                String status = "logout"; // by default
                String balance = balanceField.getText();
                boolean validate = !name.equals("") && !password.equals("") && !accountNo.equals("") && !balance.equals("");
                if(!DB.isValidUser(name, password) && validate) DB.storeNewUser(id, name, password, accountNo, status, balance);
            }
        });
    }
}

public class UserRegistration extends JFrame {
    private UserRegistrationForm registrationForm;
    public UserRegistration(String title) {
        // Frame Creation
        super(title);
        setSize(400, 300);
        setLocation(770, 300);

        // Create component
        registrationForm = new UserRegistrationForm();
        // Set Grid Layout
        setLayout(new GridLayout(1,1));
        // Adding the component
        add(registrationForm);

        // Make frame visible
        setVisible(true);
    }
}
