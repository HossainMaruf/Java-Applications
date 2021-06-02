import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class SearchByID extends JPanel {
    private JLabel idLabel;
    private JTextField idField;
    private JButton searchID;

    public SearchByID() {
        idLabel = new JLabel("Book ID: ");
        idField = new JTextField(10);
        searchID = new JButton("Search");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Search By ID");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Set GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        // First Row
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 0);
        add(idLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(idField, gc);

        // Second Row
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(searchID, gc);

        // add button event
        searchID.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                if(!id.equals("")) {
                    new BookTable("Matching Book", DB.SearchBook(id, "ID"));
                }
            }
        });
    }
}
class SearchByTitle extends JPanel {
    private JLabel titleLabel;
    private JTextField titleField;
    private JButton searchTitle;

    public SearchByTitle() {
        titleLabel = new JLabel("Title: ");
        titleField = new JTextField(10);
        searchTitle = new JButton("Search");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Search By Title");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Set GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        // First Row
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 0);
        add(titleLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(titleField, gc);

        // Second Row
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(searchTitle, gc);

        // add button event
        searchTitle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                if(!title.equals("")) {
                    new BookTable("Matching Book", DB.SearchBook(title, "Title"));
                }
            }
        });
    }
}
class SearchByAuthor extends JPanel {
    private JLabel authorLabel;
    private JTextField authorField;
    private JButton searchAuthor;

    public SearchByAuthor() {
        authorLabel = new JLabel("Author: ");
        authorField = new JTextField(10);
        searchAuthor = new JButton("Search");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Search By Author");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Set GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;

        // First Row
        gc.gridx = 0;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 0);
        add(authorLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        add(authorField, gc);

        // Fourth Row
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(searchAuthor, gc);

        // add button event
        searchAuthor.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String author = authorField.getText();
                if(!author.equals("")) {
                    new BookTable("Matching Book", DB.SearchBook(author, "Author"));
                }
            }
        });
    }
}
class StudentUtilities extends JPanel {
    private JButton bookList, studentState;
    public StudentUtilities() {
        Border innerBorder = BorderFactory.createTitledBorder("Utilities");
        Border outerBorder = BorderFactory.createEmptyBorder(5, 5, 5, 5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Create the componnet
        bookList = new JButton("Book List");
        studentState = new JButton("Students State");

        // Create Layout
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(bookList);
        add(studentState);

        // add button event
        bookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookTable("All Books", DB.getAllBook());
            }
        });

        studentState.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentTable("Students State");
            }
        });
    }
}

public class StudentDashboard extends JFrame {
    private SearchByID searchByID;
    private SearchByTitle searchByTitle;
    private SearchByAuthor searchByAuthor;
    private StudentUtilities studentUtilities;

    public StudentDashboard(String title) {
        // Frame Creation
        super(title);
        setSize(400, 400);
        setLocation(360, 250);

        // Creating some panel
        searchByID = new SearchByID();
        searchByTitle = new SearchByTitle();
        searchByAuthor = new SearchByAuthor();
        studentUtilities = new StudentUtilities();

        // Setting the layout
        setLayout(new GridLayout(2,2));

        // Adding Components to the layout
        add(searchByID);
        add(searchByTitle);
        add(searchByAuthor);
        add(studentUtilities);

        // enable visibility
        setVisible(true);
    }
}
