import java.sql.*;

public class ControllerCar {
    public static void main(String[] args) {
        update();
    }

    //Creates a new car
    public static void create() {
        //Getting the model
        System.out.println("[1] New Model\n[2] Existing Model");
        int modelId = -1;
        switch (Validation.menuSelection(1,2)) {
            case 1:
                modelId = createModel();
                break;
            case 2:
                //Showing the available models
                try {
                    String query = "SELECT * FROM model ORDER BY model_id";
                    DBConnection.queryDB(query);
                } catch(SQLException e) {
                    System.out.println(e.getMessage());
                }

                System.out.println("Enter the ID of the model you wish to choose for the new car");
                modelId = Validation.getInt();
                break;
        }

        //Other car information
        System.out.println("Enter the registration number");
        String regNr = Validation.getString();
        System.out.println("Enter the registration date\nFormat: yyyy-mm-dd");
        String regDate = Validation.getString();
        System.out.println("Enter the number of km on the odometer");
        int odometer = Validation.getInt();

        //Actually inserting in the database
        try {
            String insertQuery = "INSERT INTO car\n" +
                    "(reg_nr, reg_date, odometer, model_id)\n" +
                    "VALUES \n" +
                    "(\n'" + regNr + "\', \'" + regDate + "\', " + odometer + ", " + modelId + ");";
            DBConnection.updateDB(insertQuery);
            String selectQuery = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                    "FROM kailua.car\n" +
                    "JOIN model ON car.model_id = model.model_id\n" +
                    "ORDER BY car.car_id DESC LIMIT 1;";
            DBConnection.queryDB(selectQuery);
            System.out.println("Car succesfully added");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    //Creates a new model
    public static int createModel() {
        //Getting the data from the user
        System.out.println("Enter the model group:\n" +
                "[1] Luxury Car\n[2] Family Car\n[3] Sports Car");
        int modelGroup = Validation.menuSelection(1,3);

        System.out.println("Enter the brand");
        String brand = Validation.getString();

        System.out.println("Enter the model details");
        String modelDetails = Validation.getString();

        System.out.println("Enter the fuel type\n" +
                "[1] E85 Gasoline\n[2] 90+ Octane Gasoline\n[3] Diesel");
        String fuelType = "";
        switch (Validation.menuSelection(1,3)) {
            case 1:
                fuelType = "E85 Gasoline";
                break;
            case 2:
                fuelType = "90+ Octane Gasoline";
                break;
            case 3:
                fuelType = "Diesel";
                break;
        }

        //Inserting the model in the database
        try {
            String insertQuery = "INSERT INTO model\n" +
                    "(model_group, brand, model_details, fuel_type)\n" +
                    "VALUES\n" +
                    "(" + modelGroup +",\'" + brand + "\',\'" + modelDetails + "\', \'" + fuelType + "\');";
            DBConnection.updateDB(insertQuery);
            //Shows the last item added in the table
            String selectQuery = "SELECT * FROM model ORDER BY model_id DESC LIMIT 1";
            DBConnection.queryDB(selectQuery);
            System.out.println("Model succesfully added");

            //Returns the id of the model which is needed in creating the car
            return DBConnection.queryID("SELECT * FROM model ORDER BY model_id DESC LIMIT 1");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return -1;
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

    //Shows all the cars in the DB
    public static void readAll() {
        try {
            String query = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                    "FROM kailua.car\n" +
                    "JOIN model ON car.model_id = model.model_id\n" +
                    "ORDER BY car.car_id;";
            DBConnection.queryDB(query);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Shows cars after a search by attribute
    public static void readByAttribute() {
        System.out.println("By which attribute do you want to search?");
        System.out.println("[1] Car ID\n[2] Model ID\n[3] Model Group\n[4] Brand\n[5] Registration Number");
        try {
            switch (Validation.menuSelection(1,5)) {
                case 1:
                    System.out.println("Enter the Car ID:");
                    int id = Validation.getInt();
                    String query1 = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "WHERE car.car_id = " + id + "\n" +
                    "ORDER BY car.car_id;";
                    DBConnection.queryDB(query1);
                    break;
                case 2:
                    System.out.println("Enter the Model ID:");
                    int modelId = Validation.getInt();
                    String query2 = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "WHERE model.model_id = " + modelId +"\n" +
                            "ORDER BY car.car_id;";
                    DBConnection.queryDB(query2);
                    break;
                case 3:
                    System.out.println("Enter the Model Group");
                    int modelGroup = Validation.getInt();
                    String query3 = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "WHERE model.model_group = " + modelGroup + "\n" +
                            "ORDER BY car.car_id;";
                    DBConnection.queryDB(query3);
                    break;
                case 4:
                    System.out.println("Enter the Brand");
                    String brand = Validation.getString();
                    String query4 = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "WHERE model.brand = \'" + brand + "\'\n" +
                            "ORDER BY car.car_id;";
                    DBConnection.queryDB(query4);
                    break;
                case 5:
                    System.out.println("Enter the Registration Number");
                    String regNr = Validation.getString();
                    String query5 = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "WHERE car.reg_nr = \'" + regNr + "\'\n" +
                            "ORDER BY car.car_id;";
                    DBConnection.queryDB(query5);
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Updates an attribute of a car
    public static void update() {
        //Getting the ID of the car
        read();
        System.out.println("Enter the ID of the car you want to update");
        int carId = Validation.getInt();

        //Updating a particular attribute
        System.out.println("Which field do you wish to change\n" +
                "[1] Registration Number\n[2] Odometer information");
        try {
            switch (Validation.menuSelection(1,2)) {
                case 1:
                    System.out.println("Enter the new registration number");
                    String regNr = Validation.getString();
                    String updateQuery1 = "UPDATE car \n" +
                            "SET reg_nr = \'" + regNr + "\'\n" +
                            "WHERE car_id = " + carId;
                    DBConnection.updateDB(updateQuery1);

                    String selectQuery1 = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "WHERE car_id = " + carId;
                    DBConnection.queryDB(selectQuery1);
                    System.out.println("Car succesfully updated");
                    break;
                case 2:
                    System.out.println("Enter the new odometer information");
                    int odometer = Validation.getInt();
                    String updateQuery2 = "UPDATE car \n" +
                            "SET odometer = " + odometer + "\n" +
                            "WHERE car_id = " + carId;
                    DBConnection.updateDB(updateQuery2);

                    String selectQuery2 = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "WHERE car_id = " + carId;
                    DBConnection.queryDB(selectQuery2);
                    System.out.println("Car succesfully updated");
                    break;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    //Deletes a car from the DB
    public static void delete() {
        //Getting the ID
        read();
        System.out.println("Enter the ID of the car you want to delete");
        int carId = Validation.getInt();

        //Deleting from DB
        try {
            String query = "DELETE FROM car WHERE car_id = " + carId;
            DBConnection.updateDB(query);
            System.out.println("Car succesfully deleted");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
