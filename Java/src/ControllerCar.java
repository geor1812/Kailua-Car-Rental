import java.sql.*;

public class ControllerCar {
    public static void main(String[] args) {
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
        System.out.println("[1]Car ID\n[2] Model ID\n[3] Model Group\n[4] Brand\n[5] Registration Number");
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
}
