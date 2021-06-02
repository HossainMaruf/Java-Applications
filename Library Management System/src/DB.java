import java.sql.*;
import java.util.*;

public class DB {
    private String DBport = "3306";
    private String DBname= "library";
    private String DBuser = "root";
    private String DBpassword = "";

    private static Connection con = null;
    public DB() throws SQLException, ClassNotFoundException {
        try {
            // Register mysql driver
            Class.forName("com.mysql.jdbc.Driver");
            // Get the connection with DB
            con = DriverManager.getConnection("jdbc:mysql://localhost:"+DBport+"/"+DBname,DBuser,DBpassword);
        } catch(SQLException e) {
            System.out.println("Something Went Wrong");
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Book Area
    public static boolean isBookExist(String id) {
        try {
            String query = "select * from book where id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next() == false) return false;
            else {
                // do something if needed
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void storeThisBook(String id, String title, String author) {
        try {
            String query = "insert into book values (?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, title);
            statement.setString(3, author);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void removeThisBook(String id) {
        try {
            String query = "delete from book where id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getAllBook() {
        List<String[]> list = new ArrayList<String[]>();
        try {
            String query = "select * from book";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String[] row = {new String(rs.getString(1)), new String(rs.getString(2)), new String(rs.getString(3))};
                list.add(row);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Librarian Area
    public static boolean isValidLibrarian(String name, String password) {
        try {
            String query = "select * from librarian where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next() == false) return false;
            else {
                // do something if needed
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static void storeThisLibrarian(String name, String password) {
        try {
            String query = "insert into librarian values (?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getAllLibrarian() {
        List<String[]> list = new ArrayList<String[]>();
        try {
            String query = "select * from librarian";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String[] row = {new String(rs.getString(1)), new String(rs.getString(2))};
                list.add(row);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Student Area
    public static void storeThisStudent(String id, String name, String password, String issue) {
        try {
            String query = "insert into student values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, issue);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isExistStudent(String id) {
        try {
            String query = "select * from student where id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            ResultSet rs = statement.executeQuery();
            if(rs.next() == false) return false;
            else {
                // do something if needed
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean isValidStudent(String name, String password) {
        try {
            String query = "select * from student where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            if(rs.next() == false) return false;
            else {
                // do something if needed
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static List<String[]> SearchBook(String value, String searchBy) {
        List<String[]> list = new ArrayList<String[]>();
        String query;
        try {
            if(searchBy == "ID") {
                query = "select * from book where id = ?";
            } else if(searchBy == "Title") {
                query = "select * from book where title = ?";
            } else {
                query = "select * from book where author = ?";
            }
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, value);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String[] row = {new String(rs.getString(1)), new String(rs.getString(2)),  new String(rs.getString(3))};
                list.add(row);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static String getCurrentStudentIssue(String stdID) {
        try {
            String query = "select issue from student where id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, stdID);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getString("issue");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "Dummy Text"; // For valid student, we must find the issue
    }

    public static void updateStudentIssue(String id, Integer currentIssue) {
        try {
            String query = "update student set issue = ? where id = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, String.valueOf(currentIssue));
            statement.setString(2, id);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static List<String[]> getAllStudents() {
        List<String[]> list = new ArrayList<String[]>();
        try {
            String query = "select * from student";
            PreparedStatement statement = con.prepareStatement(query);
            ResultSet rs = statement.executeQuery();
            while(rs.next()) {
                String[] row = {new String(rs.getString(1)), new String(rs.getString(2)), new String(rs.getString(4))};
                list.add(row);
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
