import java.sql.*;

public class DBConnection {

    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DATABASE_URL = "jdbc:mysql://localhost:3306/kailua"; //DB name last
    static Connection con;

    public static void main(String[] args) throws SQLException {
        String query = "SELECT * FROM car WHERE car_id = 1";
        queryDB(query);
    }

    public static void queryDB(String query) throws SQLException {
        try {
            con = null;
            Statement s = null;
            Class.forName (JDBC_DRIVER);

            con = DriverManager.getConnection(DATABASE_URL, "root", "password"); //change PW to match yours
            s = con.createStatement();

            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            if (rs != null)
                while (rs.next()) {
                    for (int i = 1; i <= columnsNumber; i++) {
                        if (i > 1) System.out.print(",  ");
                        String columnValue = rs.getString(i);
                        System.out.print(columnValue + " " + rsmd.getColumnName(i));
                    }
                    System.out.println();
                }
            s.close();
            con.close();
        }
        catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                con.close();
                System.exit(1);
            }
            catch(SQLException sql) {}
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);
        }
    }

    //Returns the first entry in the table usually an ID
    //Very useful when adding objects that are stored in multiple tables in the DB
    public static int queryID(String query) throws SQLException {
        try {
            con = null;
            Statement s = null;
            Class.forName (JDBC_DRIVER);

            con = DriverManager.getConnection(DATABASE_URL, "root", "password"); //change PW to match yours
            s = con.createStatement();

            ResultSet rs = s.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            int id = -1;
            if (rs != null) {
                while(rs.next()) {
                    id = rs.getInt(1);
                }
            }
            s.close();
            con.close();
            return id;
        }
        catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                con.close();
                System.exit(1);
            }
            catch(SQLException sql) {}
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);
        }
        return -1;
    }

    //Executes a data manipulation query
    public static void updateDB(String query) throws SQLException {
        try {
            con = null;
            Statement s = null;
            Class.forName (JDBC_DRIVER);

            con = DriverManager.getConnection(DATABASE_URL, "root", "password"); //change PW to match yours
            s = con.createStatement();

            int rs = s.executeUpdate(query);
            s.close();
            con.close();
        }
        catch (SQLException sqlex) {
            try{
                System.out.println(sqlex.getMessage());
                con.close();
                System.exit(1);
            }
            catch(SQLException sql) {}
        }
        catch (ClassNotFoundException noClass) {
            System.err.println("Driver Class not found");
            System.out.println(noClass.getMessage());
            System.exit(1);
        }
    }

}