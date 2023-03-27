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
        Scanner keyboard = new Scanner(System.in);
        boolean exitMenu = false;
        while (!exitMenu) {
            // Prompts user for menu selection
            System.out.println("Please select an option. Type 4 to exit.\n" +
                    "1. Draw triangles\n2. Draw rectangles\n3. Update drawing canvas settings\n4. Exit");

            // Takes input
            int menuCase = Integer.parseInt(keyboard.nextLine());

            // Switch for menu items (cases: 1-4)
            switch (menuCase) {
                case 1:
                    // Draw Triangle
                case 2:
                    // Draw Rectangle
                case 3:
                    // Update Canvas Settings
                case 4:
                    exitMenu = true;
                    break;
            }
            // System.out.println("Selected: " + menuCase); // Debugging purposes

        }

    }
}