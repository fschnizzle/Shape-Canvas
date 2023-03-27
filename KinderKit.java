import java.util.Scanner;

/**
 * COMP90041, Sem1, 2023: Assignment 1
 * Date: 27/03/23
 * 
 * @author: Flynn Schneider
 */
public class KinderKit {
    public static void main(String[] args) {
        // Initialise command line settings
        int canvasWidth = Integer.parseInt(args[0]);
        int canvasHeight = Integer.parseInt(args[1]);

        // Display Welcome Message
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----\n" + "Current drawing canvas settings:");

        System.out.println( // Display initial settings based on command line inputs
                String.format("- Width: %d\n- Height: %d\n- Background character: -", canvasWidth, canvasHeight));

        // Display Main Menu
    }
}