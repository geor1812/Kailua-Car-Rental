import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    //gets a menu selection from the user
    public static int menuSelection(int lowerIndex, int upperIndex) {
        int selection = getInt();
        while (selection < lowerIndex || selection > upperIndex) {
            System.out.println("Your selection is invalid");
            selection = getInt();
        }
        return selection;
    }

    //returns an int from the user
    public static int getInt() {
        Scanner console = new Scanner(System.in);
        while (!console.hasNextInt()) {
            System.out.println("You must enter an integer");
            console.next();
        }
        return console.nextInt();
    }

    //returns a string from the user
    public static String getString() {
        Scanner console = new Scanner(System.in);
        return console.nextLine();
    }

    public static String getDate() {
        Scanner console = new Scanner(System.in);
        String regex = "^\\d{4}\\-(0[1-9]|1[012])\\-(0[1-9]|[12][0-9]|3[01])$";
        Pattern pattern = Pattern.compile(regex);
        String date = console.next();
        Matcher matcher = pattern.matcher(date);
        Boolean match = matcher.matches();
        while (!match) {
            System.out.println("Please enter date in format: yyyy-mm-dd");
            date = console.next();
            matcher = pattern.matcher(date);
            match = matcher.matches();
        }
        return date;
    }

    public static String getRegNr() {
        Scanner console = new Scanner(System.in);
        String regex = "[a-zA-Z]{2}\\d{5}";
        Pattern pattern = Pattern.compile(regex);
        String regNr = console.next();
        Matcher matcher = pattern.matcher(regNr);
        Boolean match = matcher.matches();
        while (!match) {
            System.out.println("Please enter registration number in format: AB12345");
            regNr = console.next();
            matcher = pattern.matcher(regNr);
            match = matcher.matches();
        }
        return regNr;
    }

    public static String getDateHour() {
        String date = getDate();
        Scanner console = new Scanner(System.in);
        System.out.println("Please specify from what hour you would like to rent");
        int hour = getInt();
        while (9 > hour && hour < 23) {
            System.out.println("Please use a number between 10-22");
            hour = getInt();
        }
        String time = hour + ":00:00.0";
        return date + " " + time;
    }

    public static int intCheck(int min, int max){
        Scanner scanner = new Scanner(System.in);
        boolean correct = false;

        String input = "";

        while(!correct){
            correct = true;

            if(scanner.hasNextInt()){

                input = scanner.next().trim();

                if((Integer.parseInt(input) > min - 1 && Integer.parseInt(input) < max + 1)){

                    correct = true;
                } else {
                    correct = false;
                    System.out.printf("\t%-25s", "Please choose a valid number between " + min + " and " + max + ": ");
                    input = "";
                }
            } else {
                correct = false;
                scanner.next();
                System.out.printf("\t%-25s", "Please choose a valid number between " + min + " and " + max + ": ");
            }
        }
        scanner.nextLine();
        return Integer.parseInt(input);
    }

}