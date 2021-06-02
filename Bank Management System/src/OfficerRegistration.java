import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

class OfficerRegistrationPanel extends JPanel {
    private JLabel nameLabel, passwordLabel, info_text;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton signup;

    public OfficerRegistrationPanel() {
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
        info_text = new JLabel("Check Status After SignUp");
        signup = new JButton("Sign Up");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Registration Form");
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
        add(info_text, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(signup, gc);

        // Add button event
        signup.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = String.valueOf(new Random().nextInt(hashCode()));
                String name = nameField.getText();
                String password = passwordField.getText();
                String status = "logout"; // by default
                if(!DB.isValidOfficer(name, password) && !name.equals("") && !password.equals("")) {
                    DB.storeOfficerRecord(id, name, password, status);
                }
            }
        });
    }
}

public class OfficerRegistration extends JFrame {
    private OfficerRegistrationPanel officerRegistrationPanel;
    public OfficerRegistration(String title) {
        // Frame Creation
        super(title);
        setSize(400, 300);
        setLocation(400, 200);

        // Creating the components
        officerRegistrationPanel = new OfficerRegistrationPanel();

        // Grid Layout
        setLayout(new GridLayout(1,1));
        add(officerRegistrationPanel);

        setVisible(true);
    }
}
