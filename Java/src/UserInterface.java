import java.util.Scanner;

public class UserInterface {
    private static int menuWidth = 50;

    public static void main(String[] args) {
        mainMenu();
    }

    public static void mainMenu(){
        int choice = -1;

        do{
            menuTitle("Main menu");
            System.out.println();
            System.out.printf("");
            System.out.printf("\t%-37s | %-35s \n", "1. Rental Contracts Menu", "2. Car Menu");
            System.out.printf("\t%-37s | %-35s \n\n", "3. Customer Menu", "4. Close Program");
            visualFillerLine();
            System.out.print("\tPlease select from the options above: ");
            choice = Validation.intCheck(1, 4);
            switch (choice){
                case 1:
                    rentalMenu();
                    break;
                case 2:
                    carMenu();
                    break;
                case 3:
                    customerMenu();
                    break;
                case 4:
                    System.exit(0);
                    break;
            }



        } while (choice != -1);
    }

    public static void rentalMenu(){
        int choice = -1;

        do{
            menuTitle("Rental Menu");
            System.out.println();
            System.out.printf("\t%-37s | %-35s \n", "1. Create new rental contract", "2. Update rental contracts");
            System.out.printf("\t%-37s | %-35s \n", "3. Delete rental contracts", "4. View rental contracts");
            System.out.printf("\t%-37s | \n", "5. Back");
            visualFillerLine();
            System.out.print("\tPlease select from the options above: ");
            choice = Validation.intCheck(1, 5);
            switch (choice){
                case 1:
                    ControllerRental.create();
                    pressKey();
                    rentalMenu();
                    break;
                case 2:
                    ControllerRental.update();
                    pressKey();
                    rentalMenu();
                    break;
                case 3:
                    ControllerRental.delete();
                    pressKey();
                    rentalMenu();
                    break;
                case 4:
                    ControllerRental.read();
                    pressKey();
                    rentalMenu();
                    break;
                case 5:
                    mainMenu();
                    break;
                default:
            }
        } while (choice != -1);
    }

    public static void carMenu(){
        int choice = -1;

        do{
            menuTitle("Car Menu");
            System.out.println();
            System.out.printf("\t%-37s | %-35s \n", "1. Create new car", "2. Update cars");
                System.out.printf("\t%-37s | %-35s \n", "3. Delete cars", "4. View cars");
            System.out.printf("\t%-37s | \n", "5. Back");
            visualFillerLine();
            System.out.print("\tPlease select from the options above: ");
            choice = Validation.intCheck(1, 5);
            switch (choice){
                case 1:
                    ControllerCar.create();
                    pressKey();
                    carMenu();
                    break;
                case 2:
                    ControllerCar.update();
                    pressKey();
                    carMenu();
                    break;
                case 3:
                    ControllerCar.delete();
                    pressKey();
                    carMenu();
                    break;
                case 4:
                    ControllerCar.read();
                    pressKey();
                    carMenu();
                    break;
                case 5:
                    mainMenu();
                    break;
                default:
            }
        } while (choice != -1);
    }

    public static void customerMenu(){
        int choice = -1;

        do{
            menuTitle("Customer Menu");
            System.out.println();
            System.out.printf("\t%-37s | %-35s \n", "1. Create new customer", "2. Update customers");
            System.out.printf("\t%-37s | %-35s \n", "3. Delete customers", "4. View customers");
            System.out.printf("\t%-37s | \n", "5. Back");
            visualFillerLine();
            System.out.print("\tPlease select from the options above: ");
            choice = Validation.intCheck(1, 5);
            switch (choice){
                case 1:
                    ControllerCustomer.create();
                    pressKey();
                    customerMenu();
                    break;
                case 2:
                    ControllerCustomer.update();
                    pressKey();
                    customerMenu();
                    break;
                case 3:
                    ControllerCustomer.delete();
                    pressKey();
                    customerMenu();
                    break;
                case 4:
                    ControllerCustomer.read();
                    pressKey();
                    customerMenu();
                    break;
                case 5:
                    mainMenu();
                    break;
                default:
            }
        } while (choice != -1);
    }

    //Prints menu title in the center of the menu, with visual filler over and under. EG, lines, stars what ever
    private static void menuTitle(String title){

        title = title.toUpperCase();
        String visualFiller = "";
        String visualFillerGap = "";
        int menuCenter =  menuWidth - (title.length() / 2);


        for(int i = 0; i < menuWidth; i++){
            visualFiller += "/";
            visualFiller += " ";
        }

        for(int j = 0; j < menuCenter; j++){
            visualFillerGap += " ";
        }

        System.out.println( visualFiller + "\n" + visualFillerGap + title + "\n" + visualFiller );

    }

    private static void visualFillerLine(){
        for(int i = 0; i < menuWidth; i++){
            System.out.print("/ ");
        }
        System.out.println();
    }

    private static void pressKey() {
        System.out.println("Press enter to continue");
        Scanner console = new Scanner(System.in);
        console.nextLine();
    }

}
