import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Toolbar extends JPanel {
    // First Window Heading
    private JLabel title;
    // First Window Button
    private JButton userBtn, officerBtn;
    public Toolbar() {
        title = new JLabel("Are you ???");
        userBtn = new JButton("USER");
        officerBtn = new JButton("OFFICER");
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(title);
        add(userBtn);
        add(officerBtn);
        // Add event to the button
        userBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println(e.getActionCommand()); // USER
                // Whenever clicked to USER then generate USER login window
                new UserLogin("User");
            }
        });
        officerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // System.out.println(e.getActionCommand()); // OFFICER
                new OfficerLogin("Officer");
            }
        });
    }
}

public class MainFrame extends JFrame {
    // Some Panel
    private Toolbar toolbar;
    public MainFrame(String title) {
        // Frame Creation
        super(title);
        setSize(400, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Popup the jframe in the middle of screen
        setLocationRelativeTo(null);

        // Creating some panel
        toolbar = new Toolbar();

        // Setting the layout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        // Only One Row
        gc.gridx = 0;
        gc.gridy = 0;

        // Adding Components to the layout
        add(toolbar, gc);

        // enable visibility
        setVisible(true);
    }
}
