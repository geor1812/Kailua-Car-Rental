import java.sql.*;
public class ControllerCustomer {
    public static void read() {
        System.out.println("[1] View All\n[2] Search by attribute");
        switch (Validation.menuSelection(1,2)) {
            case 1:
                readAll();
                break;
            case 2:
                readByAttribute();
                break;
        }
    }

    public static void create() {
        System.out.println("Enter customer's first name");
        String firstName = Validation.getString();
        System.out.println("Enter the customer's last name");
        String lastName = Validation.getString();
        System.out.println("Enter the customer's phone number");
        String phoneNo = Validation.getString();
        System.out.println("Enter the customer's email");
        String email = Validation.getString();
        int addressID = createAddress();
        String licenceNo = createLicence();

        //Actually inserting in the database
        try {
            String insertQuery = "INSERT INTO customer\n" +
                    "(first_name, last_name, phone_no, email, licence_no, address_id)\n" +
                    "VALUES \n" +
                    "(\n'" + firstName + "\', \'" + lastName + "\', " + phoneNo + ",\'" + email + "\', " + licenceNo + ", " + addressID +");";
            DBConnection.updateDB(insertQuery);
            String selectQuery = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no,\n" +
                    "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city\n" +
                    "FROM kailua.customer\n" +
                    "JOIN address ON customer.address_id = address.address_id\n" +
                    "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                    "ORDER BY customer.customer_id DESC LIMIT 1;";
            DBConnection.queryDB(selectQuery);
            System.out.println("Customer successfully added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static String createLicence(){

        System.out.println("Enter the customer's licence number");
        String licenceNo = Validation.getString();
        System.out.println("Enter the licence date\nFormat: yyyy-mm-dd");
        String licenceDate = Validation.getString();

        try {
            String insertQuery = "INSERT INTO licence\n" +
                    "(licence_no, licence_date)\n" +
                    "VALUES\n" +
                    "(" + licenceNo + ", \'" + licenceDate + "\');";
            DBConnection.updateDB(insertQuery);
            System.out.println("Licence successfully added");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return licenceNo;
    }
    //Create a new address
    public static int createAddress() {
        System.out.println("Enter the customer's address");
        String addressDetails = Validation.getString();
        System.out.println("Enter the customer's zip");
        String zip = Validation.getString();
        System.out.println("Enter the customer's city");
        String city = Validation.getString();
        try {
            String insertQuery = "INSERT INTO address\n" +
                    "(address_details, zip, city)\n" +
                    "VALUES\n" +
                    "(\'" + addressDetails +"\', \'" + zip + "\',\'" + city + "\');";
            DBConnection.updateDB(insertQuery);
            //Shows the last item added in the table
            String selectQuery = "SELECT * FROM address ORDER BY address_id DESC LIMIT 1";
            DBConnection.queryDB(selectQuery);
            System.out.println("Address successfully added");

            //Returns the id of the address
            return DBConnection.queryID("SELECT * FROM address ORDER BY address_id DESC LIMIT 1");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
    }

    //Shows all the customers in the DB
    public static void readAll() {
        try {
            String query = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                    "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                    "FROM kailua.customer\n" +
                    "JOIN address ON customer.address_id = address.address_id\n" +
                    "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                    "ORDER BY customer.customer_id;";
            DBConnection.queryDB(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Shows cars after a search by attribute
    public static void readByAttribute() {
        System.out.println("By which attribute do you want to search?");
        System.out.println("[1] Customer ID\n[2] First Name\n[3] Last Name\n[4] Phone Number\n[5] Email \n[6] License Number");
        try {
            switch (Validation.menuSelection(1, 6)) {
                case 1:
                    System.out.println("Enter the Customer ID:");
                    int id = Validation.getInt();
                    String query1 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.customer_id = " + id + "\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(query1);
                    break;
                case 2:
                    System.out.println("Enter the First Name:");
                    String firstName = Validation.getString();
                    String query2 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.first_name = \'" + firstName + "\'\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(query2);
                    break;
                case 3:
                    System.out.println("Enter the Last Name");
                    String lastName = Validation.getString();
                    String query3 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.last_name = \'" + lastName + "\'\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(query3);
                    break;
                case 4:
                    System.out.println("Enter the Phone Number");
                    int phoneNo = Validation.getInt();
                    String query4 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.phone_no = \'" + phoneNo + "\'\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(query4);
                    break;
                case 5:
                    System.out.println("Enter the Email");
                    String email = Validation.getString();
                    String query5 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.email = \'" + email + "\'\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(query5);
                    break;
                case 6:
                    System.out.println("Enter the Email");
                    int licenceNo = Validation.getInt();
                    String query6 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no, licence.licence_date, customer.address_id, address.address_details" +
                            "address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.email = " + licenceNo + "\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(query6);
                    break;


            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


    public static void update() {
        //Getting the ID of the customer
        read();
        System.out.println("Enter the ID of the customer you want to update");
        int customerId = Validation.getInt();

        //Updating a particular attribute
        System.out.println("Which field do you wish to change\n" +
                "[1] Phone Number\n[2] Email\n[3] Address");
        try {
            switch (Validation.menuSelection(1,3)) {
                case 1:
                    System.out.println("Enter the new phone number");
                    String phoneNo = Validation.getString();
                    String updateQuery1 = "UPDATE customer \n" +
                            "SET phone_no = \'" + phoneNo + "\'\n" +
                            "WHERE customer_id = " + customerId;
                    DBConnection.updateDB(updateQuery1);

                    String selectQuery1 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.customer_id = " + customerId + "\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(selectQuery1);
                    System.out.println("Customer successfully updated");
                    break;
                case 2:
                    System.out.println("Enter the new email");
                    String email = Validation.getString();
                    String updateQuery2 = "UPDATE customer \n" +
                            "SET email = \'" + email + "\'\n" +
                            "WHERE customer_id = " + customerId;
                    DBConnection.updateDB(updateQuery2);

                    String selectQuery2 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.customer_id = " + customerId + "\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(selectQuery2);
                    System.out.println("Customer successfully updated");
                    break;

                case 3:
                    int addressID = createAddress();
                    String updateQuery3 = "UPDATE customer \n" +
                            "SET address_id = " + addressID + "\n" +
                            "WHERE customer_id = " + customerId;
                    DBConnection.updateDB(updateQuery3);

                    String selectQuery3 = "SELECT customer.customer_id, customer.first_name, customer.last_name, customer.phone_no, customer.email, customer.licence_no," +
                            "licence.licence_date, customer.address_id, address.address_details, address.zip, address.city \n" +
                            "FROM kailua.customer\n" +
                            "JOIN address ON customer.address_id = address.address_id\n" +
                            "JOIN licence ON customer.licence_no = licence.licence_no\n" +
                            "WHERE customer.customer_id = " + customerId + "\n" +
                            "ORDER BY customer.customer_id;";
                    DBConnection.queryDB(selectQuery3);
                    System.out.println("Customer successfully updated");
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void delete() {
        //Getting the ID
        read();
        System.out.println("Enter the ID of the customer you want to delete");
        int customerId = Validation.getInt();

        //Deleting from DB
        try {
            String query = "DELETE FROM customer WHERE customer_id = " + customerId;
            DBConnection.updateDB(query);
            System.out.println("Customer successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
