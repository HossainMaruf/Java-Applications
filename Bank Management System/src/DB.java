import java.sql.*;

public class DB {
    private String DBport = "3306";
    private String DBname= "bank";
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
            e.printStackTrace();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Users Area
    public static boolean isValidUser(String userName, String userPassword) {
        try {
            String query = "select * from users where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, userPassword);
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

    public static boolean accountExist(String userName, String accountNO) {
        try {
            String query = "select * from users where name = ? and account = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, accountNO);
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

    public static String getAccountNo(String userName, String userPassword) {
        try {
            String query = "select account from users where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, userPassword);
            ResultSet rs = statement.executeQuery();
            rs.next();
            // System.out.println(rs.getString("account"));
            return rs.getString("account");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "Dummy Text"; // Because after checking the valid user, we must find the accountNo
    }

    public static String getStatus(String userName, String userPassword) {
        try {
            String query = "select status from users where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, userPassword);
            ResultSet rs = statement.executeQuery();
            rs.next();
            // System.out.println(rs.getString("status"));
            return rs.getString("status");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "Dummy Text"; // Because after checking the valid user, we must find the status
    }

    public static String userStatusByAccount(String userName, String account) {
        try {
            String query = "select status from users where name = ? and account = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, account);
            ResultSet rs = statement.executeQuery();
            rs.next();
            // System.out.println(rs.getString("status"));
            return rs.getString("status");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "Dummy Text"; // Because after checking the valid user, we must find the status
    }

    public static String userBalanceByAccount(String userName, String account) {
        try {
            String query = "select balance from users where name = ? and account = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, account);
            ResultSet rs = statement.executeQuery();
            rs.next();
            // System.out.println(rs.getString("status"));
            return rs.getString("balance");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "Dummy Text"; // Because after checking the valid user, we must find the balance
    }


    public static String getBalance(String userName, String userPassword) {
        try {
            String query = "select balance from users where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, userName);
            statement.setString(2, userPassword);
            ResultSet rs = statement.executeQuery();
            rs.next();
            // System.out.println(rs.getString("status"));
            return rs.getString("balance");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "Dummy Text"; // Because after checking the valid user, we must find the balance
    }

    public static void setStatus(String userName, String userPassword, String status) {
        try {
            String query = "update users set status = ? where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, status);
            statement.setString(2, userName);
            statement.setString(3, userPassword);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateUserBalance(String name, String accountNo, Integer currentBalance) {
        try {
            String query = "update users set balance = ? where name = ? and account = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, String.valueOf(currentBalance));
            statement.setString(2, name);
            statement.setString(3, accountNo);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void storeNewUser(String id, String name, String password, String account, String status, String balance) {
        try {
            String query = "insert into users values (?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, account);
            statement.setString(5, status);
            statement.setString(6, balance);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }


    // Officers Area
    public static boolean isValidOfficer(String name, String password) {
        try {
            String query = "select * from officers where name = ? and password = ?";
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

    public static String getOfficerStatus(String name, String password) {
        try {
            String query = "select status from officers where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, name);
            statement.setString(2, password);
            ResultSet rs = statement.executeQuery();
            rs.next();
            return rs.getString("status");
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return "Dummy Text"; // For valid officer, we must find the status
    }

    public static void setOfficerStatus(String name, String password, String status) {
        try {
            String query = "update officers set status = ? where name = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, status);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public static void storeOfficerRecord(String id, String name, String password, String status) {
        try {
            String query = "insert into officers values (?, ?, ?, ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, id);
            statement.setString(2, name);
            statement.setString(3, password);
            statement.setString(4, status);
            statement.executeUpdate();
        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}
