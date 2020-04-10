import java.util.*;

public class Validation {
    //gets a menu selection from the user
    public static int menuSelection(int lowerIndex, int upperIndex) {
        int selection = getInt();
        while(selection < lowerIndex || selection > upperIndex) {
            System.out.println("Your selection is invalid");
            selection = getInt();
        }
        return selection;
    }

    //returns an int from the user
    public static int getInt() {
        Scanner console = new Scanner(System.in);
        while(!console.hasNextInt()) {
            System.out.println("You must enter an integer");
            console.next();
        }
        return console.nextInt();
    }
}
