import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * COMP90041 Semester 1, 2023: Assignment 1 (part two)
 * 
 * This program is a digital kinder kit that allows users to draw triangles on a
 * canvas in one of two modes - (1) Challenge mode: where users attempt to match
 * a predefined shape according to a comma seperated txt file, and (2) Freestyle
 * mode: where users can define canvas and draw shapes freely
 * The program takes command line arguments for the predefined shape (.txt)
 * file.
 * 
 * @author Flynn Schneider
 *         Student ID number: 982143
 *         Student email: fschneider@student.unimelb.edu.au
 **/
public class KinderKit {
    static Scanner keyboard = new Scanner(System.in); // Scanner Object

    public static void main(String[] args) {
        // DON'T TOUCH LINES 23 to 72: File I/O given code
        // Check program arguments
        if (args.length != 1) {
            System.out.println("This program takes exactly one argument!");
            return;
        }

        // Read sample drawing from file
        Scanner inputStream = null;
        char[][] bitmap = null;
        int rows = 0, cols = 0;
        char bgChar;

        try {
            inputStream = new Scanner(new FileInputStream(args[0]));
            String line;

            // Read the first line
            if (inputStream.hasNextLine()) {
                line = inputStream.nextLine();
                String[] tmps = line.split(",");
                if (tmps.length != 3) {
                    System.out.println("The given file is in wrong format!");
                    return;
                } else {
                    rows = Integer.parseInt(tmps[0]);
                    cols = Integer.parseInt(tmps[1]);
                    bgChar = tmps[2].charAt(0);
                    bitmap = new char[rows][cols];

                }
            } else {
                System.out.println("The given file seems empty!");
                return;
            }

            // Read the remaining lines
            int rowIndex = 0;
            while (inputStream.hasNextLine()) {
                line = inputStream.nextLine();
                String[] tmps = line.split(",");
                for (int i = 0; i < tmps.length; i++) {
                    bitmap[rowIndex][i] = tmps[i].charAt(0);
                }
                rowIndex++;
            }
            // Close the file input stream
            inputStream.close();
        } catch (FileNotFoundException e) {
            System.out.println("The given file is not found!");
            return;
        }

        // Start Menu Selection
        programMenu(bitmap, bgChar);

        // Exit message
        System.out.println("Goodbye! We hope you had fun :)");
        System.exit(0);
    }

    public static void programMenu(char[][] bitmap, char bgChar) {
        // Print Program Command Line Header
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----");

        // Loop until EXIT_VALUE in sub-menus is selected
        boolean exitMenu = false;
        while (!exitMenu) {
            // Prompts user for menu selection
            System.out.println("Please select an option. Type 3 to exit.\n" +
                    "1. Draw a predefined object\n2. Freestyle Drawing\n3. Exit");

            // Takes input
            int menuCase = Integer.parseInt(keyboard.nextLine());

            // Menu Handler returns true if EXIT_VALUE given
            exitMenu = handleMenuSelection(menuCase, bitmap, bgChar);
        }
    }

    public static Boolean handleMenuSelection(int selection, char[][] bitmap, char bgChar) {
        // Switch for menu items
        final int CHALLENGE_MODE_VALUE = 1;
        final int FREESTYLE_MODE_VALUE = 2;
        final int EXIT_VALUE = 3;

        switch (selection) {
            case CHALLENGE_MODE_VALUE: // Enter Challenge Mode (1)
                DrawingTask.challengeMenu(keyboard, bitmap, bgChar);
                break;
            case FREESTYLE_MODE_VALUE: // Enter Freestyle Mode (2)
                DrawingTask.freestyleMenu(keyboard);
                break;
            case EXIT_VALUE: // Exit program (3)
                return true;
            default: // Invalid menu selection
                System.out.println("Unsupported option. Please try again!");
        }
        return false;

    }

}
