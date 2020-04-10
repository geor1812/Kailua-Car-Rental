import java.sql.SQLException;

public class ControllerCar {
    public static void main(String[] args) {
        read();
    }

    public static void read() {
        System.out.println("[1] View All\n[2] Search by attribute");
        switch (Validation.menuSelection(1,2)) {
            case 1:
                try {
                    String query = "SELECT car.car_id, model.model_id, model.model_group, model.brand, model.model_details, model.fuel_type, car.reg_nr, car.reg_date, car.odometer \n" +
                            "FROM kailua.car\n" +
                            "JOIN model ON car.model_id = model.model_id\n" +
                            "ORDER BY car.car_id;";
                    DBConnection.displayRS(query);
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
                break;
            case 2:
                break;
        }
    }
}
