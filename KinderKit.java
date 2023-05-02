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
    static DrawingCanvas canvas;

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

        // displayMenu()
        menuSelection(bitmap, bgChar);

        // Exit message
        System.out.println("Goodbye! We hope you had fun :)");
        System.exit(0);
    }

    public static void menuSelection(char[][] bitmap, char bgChar) {
        boolean exitMenu = false;

        // Loop until option 4 is selected
        while (!exitMenu) {
            // Prompts user for menu selection
            System.out.println("Please select an option. Type 3 to exit.\n" +
                    "1. Draw a predefined object\n2. Freestyle Drawing\n3. Exit");

            // Takes input
            int menuCase = Integer.parseInt(keyboard.nextLine());
            exitMenu = handleMenuSelection(menuCase, bitmap, bgChar);
        }
    }

    public static Boolean handleMenuSelection(int selection, char[][] bitmap, char bgChar) {
        // Switch for menu items (cases: 1-4)
        switch (selection) {
            case 1:
                challengeMenu(bitmap, bgChar);

                break;
            case 2:
                freestyleMenu();
                break;
            case 3:
                // Exit program
                return true;
            case 4:
                // Update canvas settings
                // canvas.updateSettings();
                // break;
            default:
                // Invalid menu option
                System.out.println("Unsupported option. Please try again!");
        }
        return false;

    }

    public static void freestyleMenu() {
        int selection;
        final int START_EDIT_VALUE = 1;
        final int SHARE_CANVAS_VALUE = 2;
        final int EXIT_VALUE = 3;

        // Define Canvas and pass scanner object
        canvas = new DrawingCanvas(keyboard);
        do {
            // Prompt user for menu selection (1,2,3)
            System.out.println("Please select an option. Type 3 to go back to the main menu");
            System.out.println(
                    "1. Start/edit your current canvas\n2. Share your current drawing\n3. Go back to the main menu");
            selection = Integer.parseInt(keyboard.nextLine());

            // Handle Menu Selection
            switch (selection) {
                case START_EDIT_VALUE: // Start/edit your current canvas
                    canvas.startEditCanvasMenu();
                    break;
                case SHARE_CANVAS_VALUE: // Share your current drawing
                    System.out.println(canvas.displayShareCanvas());
                    break;
                case EXIT_VALUE: // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid Option. Type 1-3:");
            }

            // Exit if EXIT_VALUE (3) given
        } while (selection != EXIT_VALUE);
    }

    public static void challengeMenu(char[][] bitmap, char bgChar) {
        char[][] sampleCanvas = bitmap;
        int selection;
        final int PREVIEW_SAMPLE_VALUE = 1;
        final int START_EDIT_VALUE = 2;
        final int CHECK_RESULT_VALUE = 3;
        final int EXIT_VALUE = 4;

        // Define Canvas and pass scanner object
        canvas = new DrawingCanvas(keyboard, bitmap, bgChar);
        do {
            // Prompt user for menu selection (1,2,3,4)
            System.out.println("Please select an option. Type 4 to go back to the main menu");
            System.out.println(
                    "1. Preview the sample drawing\n2. Start/edit your current canvas\n3. Check Result\n4. Go back to the main menu");
            selection = Integer.parseInt(keyboard.nextLine());

            // Handle Menu Selection
            switch (selection) {
                case PREVIEW_SAMPLE_VALUE: // Displays sample drawing
                    System.out.println(canvas.getCanvasString(sampleCanvas));
                    break;
                case START_EDIT_VALUE: // Start/edit your current canvas
                    canvas.startEditCanvasMenu();
                    break;
                case CHECK_RESULT_VALUE:
                    // Code that compares SAMPLE and canvas
                    checkResult(canvas.getCanvasString(sampleCanvas), canvas.displayCanvas());
                    break;
                case EXIT_VALUE: // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid Option. Type 1-4:");
            }

            // Exit if EXIT_VALUE (3) given
        } while (selection != EXIT_VALUE);

    }

    public static void checkResult(String sampleCanvas, String yourCanvas) {
        boolean isMatch = true; // Assume true until proven false

        // Compare each character in sampleCanvas against corresponding yourCanvas char
        for (int i = 0; i < sampleCanvas.length(); i++) {
            if (sampleCanvas.charAt(i) != yourCanvas.charAt(i)) {
                isMatch = false;
                System.out.println("Not quite! Please edit your canvas and check the result again.");
                break;
            }
        }
        if (isMatch) {
            System.out.println("You successfully complete the drawing task. Congratulations!!");
        }

        // Display sample drawing + message
        System.out.println("This is the sample drawing:");
        System.out.println(sampleCanvas);

        // Display your drawing + message
        System.out.println("And this your drawing:");
        System.out.println(yourCanvas);
    }

}
