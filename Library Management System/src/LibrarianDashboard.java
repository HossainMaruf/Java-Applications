import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class AddBookPanel extends JPanel {
    private JLabel idLabel, titleLabel, authorLabel;
    private JTextField idField, titleField, authorField;
    private JButton addBtn;

    public AddBookPanel() {
        idLabel = new JLabel("Book ID: ");
        titleLabel = new JLabel("Title: ");
        authorLabel = new JLabel("Author: ");
        idField = new JTextField(10);
        titleField = new JTextField(10);
        authorField = new JTextField(10);
        addBtn = new JButton("Add");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Add Book");
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
        add(titleLabel, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(titleField, gc);

        // Third Row
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(authorLabel, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(authorField, gc);

        // Fourth Row
        gc.gridx = 1;
        gc.gridy = 3;
        gc.anchor = GridBagConstraints.LINE_END;
        add(addBtn, gc);

        // Add button event
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                String title = titleField.getText();
                String author = authorField.getText();
                boolean validate = !id.equals("") && !title.equals("") && !author.equals("");
                if(!DB.isBookExist(id) && validate) {
                    // Store the book info
                    DB.storeThisBook(id, title, author);
                }
            }
        });
    }
}

class RemoveBookPanel extends JPanel {
    private JLabel idLabel;
    private JTextField idField;
    private JButton rmvBtn;

    public RemoveBookPanel() {
        idLabel = new JLabel("Book ID: ");
        idField = new JTextField(10);
        rmvBtn = new JButton("Remove");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Remove Book");
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
        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        add(rmvBtn, gc);

        rmvBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                DB.removeThisBook(id);
            }
        });
    }
}
class LibrarianRegistrationPanel extends JPanel {
    private JLabel nameLabel, passwordLabel;
    private JTextField nameField;
    private JPasswordField passwordField;
    private JButton registerLibrarian;

    public LibrarianRegistrationPanel() {
        nameLabel = new JLabel("Name: ");
        passwordLabel = new JLabel("Password: ");
        nameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        registerLibrarian = new JButton("Add Librarian");

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
        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(registerLibrarian, gc);

        // Add button event
        registerLibrarian.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = passwordField.getText();
                boolean validate = !name.equals("") && !password.equals("");
                if(!DB.isValidLibrarian(name, password) && validate) {
                    DB.storeThisLibrarian(name, password);
                }
            }
        });
    }
}

class BookTable extends JFrame {
    public BookTable(String title, List<String[]> list) {
        super(title);
        setSize(400, 500);
        setLocation(870,50);

        // Fetching the data
        int row = list.size();
        String data[][] = new String[row][3];
        for (int i = 0; i < row; i++) {
            data[i] = list.get(i);
        }

        String column[] = {"Book ID","Title","Author"};
        JTable jt = new JTable(data,column);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)jt.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        JScrollPane sp = new JScrollPane(jt);
        add(sp);

        setVisible(true);
    }
}

class LibrarianTable extends JFrame {
    public LibrarianTable(String title) {
        super(title);
        setSize(400, 500);
        setLocation(870,50);

        // Fetching the data
        List<String[]> list = DB.getAllLibrarian();
        int row = list.size();
        String data[][] = new String[row][2];
        for (int i = 0; i < row; i++) {
            data[i] = list.get(i);
        }

        String column[] = {"Name","Password"};
        JTable jt = new JTable(data,column);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)jt.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        JScrollPane sp = new JScrollPane(jt);
        add(sp);

        setVisible(true);
    }
}
class TransactionPanel extends JPanel {
    private JLabel studentID, bookID;
    private JTextField studentField, bookField;
    private JButton addBtn, deleteBtn;

    public TransactionPanel() {
        studentID = new JLabel("Student ID: ");
        bookID = new JLabel("Book ID: ");
        studentField = new JTextField(10);
        bookField = new JTextField(10);
        addBtn = new JButton("ADD");
        deleteBtn = new JButton("DELETE");

        // Set the border using BorderFactory
        Border innerBorder = BorderFactory.createTitledBorder("Book Transaction");
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
        add(studentID, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        add(studentField, gc);

        // Second Row
        gc.gridx = 0;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 5, 0);
        add(bookID, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        add(bookField, gc);

        // Third Row
        gc.gridx = 0;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(deleteBtn, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        gc.anchor = GridBagConstraints.LINE_END;
        add(addBtn, gc);

        // add button event
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stdID = studentField.getText();
                String bookID = bookField.getText();
                if(DB.isExistStudent(stdID) && DB.isBookExist(bookID)) {
                    // valid student and valid book
                    String currentIssue = DB.getCurrentStudentIssue(stdID);
                    Integer intIssue = Integer.parseInt(currentIssue);
                    if(intIssue < 3) {
                        DB.updateStudentIssue(stdID, intIssue + 1);
                    }
                }
            }
        });

        deleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String stdID = studentField.getText();
                String bookID = bookField.getText();
                if(DB.isExistStudent(stdID) && DB.isBookExist(bookID)) {
                    // valid student and valid book
                    String currentIssue = DB.getCurrentStudentIssue(stdID);
                    Integer intIssue = Integer.parseInt(currentIssue);
                    if(intIssue > 0) {
                        DB.updateStudentIssue(stdID, intIssue - 1);
                    }
                }
            }
        });
    }
}

class StudentTransaction extends JFrame {
    private TransactionPanel transactionPanel;
    public StudentTransaction(String title) {
        // Frame Creation
        super(title);
        setSize(400, 400);
        setLocation(870, 50);

        // Creating some panel
        transactionPanel = new TransactionPanel();

        // Setting the layout
        setLayout(new GridLayout(1,1));

        // Adding Components to the layout
        add(transactionPanel);


        // enable visibility
        setVisible(true);
    }
}

class StudentTable extends JFrame {
    public StudentTable(String title) {
        super(title);
        setSize(400, 500);
        setLocation(870,50);

        // Fetching the data
        List<String[]> list = DB.getAllStudents();
        int row = list.size();
        String data[][] = new String[row][3];
        for (int i = 0; i < row; i++) {
            data[i] = list.get(i);
        }

        String column[] = {"ID","Name","Issued"};
        JTable jt = new JTable(data,column);
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer)jt.getDefaultRenderer(Object.class);
        renderer.setHorizontalAlignment( SwingConstants.CENTER );
        JScrollPane sp = new JScrollPane(jt);
        add(sp);

        setVisible(true);
    }
}

class LibrarianUtilities extends JPanel {
    private JButton bookList, librarianList, transaction, stdDetails;
    public LibrarianUtilities() {
        Border innerBorder = BorderFactory.createTitledBorder("Utilities");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        // Create the componnet
        bookList = new JButton("Book List");
        librarianList = new JButton("Librarian List");
        transaction = new JButton("Transaction");
        stdDetails = new JButton("Students State");

        // Create Layout
        // setLayout(new GridLayout(2,1,5,0));
        setLayout(new FlowLayout(FlowLayout.CENTER));
        add(bookList);
        add(librarianList);
        add(transaction);
        add(stdDetails);

        // add button event
        bookList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BookTable("All Books", DB.getAllBook());
            }
        });

        librarianList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LibrarianTable("All Librarian");
            }
        });

        transaction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentTransaction("Transaction");
            }
        });

        stdDetails.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new StudentTable("Students State");
            }
        });
    }
}


public class LibrarianDashboard  extends JFrame{
    private AddBookPanel addBookPanel;
    private RemoveBookPanel removeBookPanel;
    private LibrarianRegistrationPanel librarianRegistrationPanel;
    private LibrarianUtilities librarianUtilities;

    public LibrarianDashboard(String title) {
        // Frame Creation
        super(title);
        setSize(500, 500);
        setLocation(360, 50);

        // Creating some panel
        addBookPanel = new AddBookPanel();
        removeBookPanel = new RemoveBookPanel();
        librarianRegistrationPanel = new LibrarianRegistrationPanel();
        librarianUtilities = new LibrarianUtilities();

        // Setting the layout
        setLayout(new GridLayout(2,2));

        // Adding Components to the layout
        add(addBookPanel);
        add(removeBookPanel);
        add(librarianRegistrationPanel);
        add(librarianUtilities);

        // enable visibility
        setVisible(true);
    }
}
