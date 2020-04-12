import java.sql.*;

public class ControllerRental {

    private static String rentalSelect = "SELECT rental.rental_id, customer.customer_id, CONCAT(customer.first_name,' ', customer.last_name) AS Name," +
            "address.address_id, address.address_details, address.zip, address.city, rental.start_date, rental.end_date," +
            "rental.max_km, car.car_id, car.reg_nr, car.odometer \n" +
            "FROM kailua.rental\n" +
            "JOIN customer ON rental.customer_id = customer.customer_id\n" +
            "JOIN car ON rental.car_id = car.car_id\n" +
            "JOIN address ON customer.address_id = address.address_id\n";

    //Creates a new rental
    public static void create() {
        //Getting the model
        int customerId= -1;
        //Showing the available customer
        try {
            String query = "SELECT * FROM customer ORDER BY customer_id";
            DBConnection.queryDB(query);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Enter the ID of the customer you wish to make a rental for");
        customerId = Validation.getInt();

        int carId = -1;
        //Showing the available customer
        try {
            String query = "SELECT * FROM car ORDER BY car_id";
            DBConnection.queryDB(query);
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }

            System.out.println("Enter the ID of the car you wish to make a rental for");
            carId = Validation.getInt();

        //Other rental information
        System.out.println("Enter the start date\nFormat: yyyy-mm-dd");
        String startDate = Validation.getDateHour();
        System.out.println("Enter the endDate\nFormat: yyyy-mm-dd");
        String endDate = Validation.getDateHour();
        System.out.println("Enter the max number of km for the rental");
        int maxKm = Validation.getInt();

        //Actually inserting in the database
        try {
            String insertQuery = "INSERT INTO rental\n" +
                    "(start_date, end_date, max_km, car_id, customer_id)\n" +
                    "VALUES \n" +
                    "(\n'" + startDate + "\', \'" + endDate + "\', " + maxKm + ", " + carId + ", " + customerId + ");";
            DBConnection.updateDB(insertQuery);
            String selectQuery = rentalSelect +
                    "ORDER BY car.car_id DESC LIMIT 1;";
            DBConnection.queryDB(selectQuery);
            System.out.println("Car successfully added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public static void mai1n(String[] args) {
        read();

    }

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

    //Shows all the rentals in the DB
    public static void readAll() {
        try {
            String query = rentalSelect +
                    "ORDER BY rental.rental_id;";
            DBConnection.queryDB(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Shows rentals after a search by attribute
    public static void readByAttribute() {
        System.out.println("By which attribute do you want to search?");
        System.out.println("[1] Rental ID\n[2] Customer ID\n[3] Customer Name\n[4] Car ID\n[5] Registration Number");
        try {
            switch (Validation.menuSelection(1,5)) {
                case 1:
                    System.out.println("Enter the Rental ID:");
                    int rentalId = Validation.getInt();
                    String query1 = rentalSelect +
                            "WHERE rental.rental_id = " + rentalId + "\n" +
                            "ORDER BY rental.rental_id;";
                    DBConnection.queryDB(query1);
                    break;
                case 2:
                    System.out.println("Enter the Customer ID:");
                    int customerId = Validation.getInt();
                    String query2 = rentalSelect +
                            "WHERE customer.customer_id = " + customerId + "\n" +
                            "ORDER BY rental.rental_id;";
                    DBConnection.queryDB(query2);
                    break;
                case 3:
                    System.out.println("Enter the customer's name");
                    String name = Validation.getString();
                    String query3 = rentalSelect +
                            "WHERE customer.first_name LIKE '%" + name + "%' || customer.first_name LIKE '%" + name + "%'\n" +
                            "ORDER BY rental.rental_id;";
                    DBConnection.queryDB(query3);
                    break;
                case 4:
                    System.out.println("Enter the Car ID");
                    int carId = Validation.getInt();
                    String query4 = rentalSelect +
                            "WHERE car.car_id = " + carId + "\n" +
                            "ORDER BY rental.rental_id;";
                    DBConnection.queryDB(query4);
                    break;
                case 5:
                    System.out.println("Enter the Registration Number");
                    String regNr = Validation.getString();
                    String query5 = rentalSelect +
                            "WHERE car.reg_nr LIKE '%" + regNr + "%'\n" +
                            "ORDER BY rental.rental_id;";
                    DBConnection.queryDB(query5);
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Updates an attribute of a rental
    public static void update() {
        //Getting the ID of the rental
        read();
        System.out.println("Enter the ID of the rental you want to update");
        int rentalId = Validation.getInt();

        //Updating a particular attribute
        System.out.println("Which field do you wish to change\n" +
                "[1] Start Date\n[2] End Date\n[3] Max km\n[4] Customer ID\n[5] Car ID");
        try {
            switch (Validation.menuSelection(1,5)) {
                case 1:
                    System.out.println("Enter the new start date");
                    String startDate = Validation.getDateHour();
                    String updateQuery1 = "UPDATE rental \n" +
                            "SET start_date = \'" + startDate + "\'\n" +
                            "WHERE rental_id = " + rentalId;
                    DBConnection.updateDB(updateQuery1);

                    String selectQuery1 = rentalSelect +
                            "WHERE rental.rental_id = " + rentalId;
                    DBConnection.queryDB(selectQuery1);
                    System.out.println("Rental successfully updated");
                    break;
                case 2:
                    System.out.println("Enter the new end date");
                    String endDate = Validation.getDateHour();
                    String updateQuery2 = "UPDATE rental \n" +
                            "SET end_date = \'" + endDate + "\'\n" +
                            "WHERE rental_id = " + rentalId;
                    DBConnection.updateDB(updateQuery2);

                    String selectQuery2 = rentalSelect +
                            "WHERE rental.rental_id = " + rentalId;
                    DBConnection.queryDB(selectQuery2);
                    System.out.println("Rental successfully updated");
                    break;
                case 3:
                    System.out.println("Enter the new max km");
                    int maxKm = Validation.getInt();
                    String updateQuery3 = "UPDATE rental \n" +
                            "SET max_km = \'" + maxKm + "\'\n" +
                            "WHERE rental_id = " + rentalId;
                    DBConnection.updateDB(updateQuery3);

                    String selectQuery3 = rentalSelect +
                            "WHERE rental.rental_id = " + rentalId;
                    DBConnection.queryDB(selectQuery3);
                    System.out.println("Rental successfully updated");
                    break;
                case 4:
                    System.out.println("Enter the new customer ID");
                    int customerId = Validation.getInt();
                    String updateQuery4 = "UPDATE rental \n" +
                            "SET customer_id = \'" + customerId + "\'\n" +
                            "WHERE rental_id = " + rentalId;
                    DBConnection.updateDB(updateQuery4);

                    String selectQuery4 = rentalSelect +
                            "WHERE rental.rental_id = " + rentalId;
                    DBConnection.queryDB(selectQuery4);
                    System.out.println("Rental successfully updated");
                    break;
                case 5:
                    System.out.println("Enter the new car ID");
                    int carId = Validation.getInt();
                    String updateQuery5 = "UPDATE rental \n" +
                            "SET car_id = \'" + carId + "\'\n" +
                            "WHERE rental_id = " + rentalId;
                    DBConnection.updateDB(updateQuery5);

                    String selectQuery5 = rentalSelect +
                            "WHERE rental.rental_id = " + rentalId;
                    DBConnection.queryDB(selectQuery5);
                    System.out.println("Rental successfully updated");
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Deletes a rental from the DB
    public static void delete() {
        //Getting the ID
        read();
        System.out.println("Enter the ID of the rental you want to delete");
        int rentalId = Validation.getInt();

        //Deleting from DB
        try {
            String query = "DELETE FROM rental WHERE rental_id = " + rentalId;
            DBConnection.updateDB(query);
            System.out.println("Rental successfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
