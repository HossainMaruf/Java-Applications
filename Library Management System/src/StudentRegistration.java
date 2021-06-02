import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class StudentRegistrationFormPanel extends JPanel {
    private JLabel idLabel, nameLabel, passwordLabel;
    private JTextField idField, nameField;
    private JPasswordField passwordField;
    private JButton registerStudent;

    public StudentRegistrationFormPanel() {
        idLabel = new JLabel("ID: ");
        nameLabel = new JLabel("Name: ");
        passwordLabel = new JLabel("Password: ");
        idField = new JTextField(10);
        nameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        registerStudent = new JButton("Get");

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
        add(idLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(idField, gc);

        // Second Row
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,5,0);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(nameField, gc);

        // Third Row
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(passwordField, gc);

        // Fourth Row
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        add(registerStudent, gc);

        // add button event
        registerStudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String name = nameField.getText();
                String password = passwordField.getText();
                String issue = "0";
                boolean validate = !id.equals("") && !name.equals("") && !password.equals("");
                if(!DB.isExistStudent(id) && validate) {
                    DB.storeThisStudent(id, name, password, issue);
                }
            }
        });
    }
 }

public class StudentRegistration extends JFrame {
    private StudentRegistrationFormPanel formPanel;

    public StudentRegistration(String title) {
        // Frame Creation
        super(title);
        setSize(300, 300);
        setLocation(350, 250);

        // Creating some panel
        formPanel = new StudentRegistrationFormPanel();

        // Setting the layout
        setLayout(new GridLayout());

        // Adding Components to the layout
        add(formPanel);

        setVisible(true);
    }
}
