import java.util.Scanner;

public class DrawingTask {
    public static void freestyleMenu(Scanner keyboard) {
        int selection;
        final int START_EDIT_VALUE = 1;
        final int SHARE_CANVAS_VALUE = 2;
        final int EXIT_VALUE = 3;

        // Define new Canvas and pass scanner object
        DrawingCanvas canvas = new DrawingCanvas(keyboard);
        do {
            // Prompt user for menu selection (1,2,3)
            System.out.println("Please select an option. Type 3 to go back to the main menu.");
            System.out.println(
                    "1. Start/edit your current canvas\n2. Share your current drawing\n3. Go back to the main menu");
            selection = Integer.parseInt(keyboard.nextLine());

            // Handle Menu Selection
            switch (selection) {
                case START_EDIT_VALUE: // Start/edit your current canvas
                    canvas.startEditCanvasMenu();
                    break;
                case SHARE_CANVAS_VALUE: // Share your current drawing
                    System.out.println("This is the detail of your current drawing");
                    System.out.println(canvas.displayShareCanvas());
                    break;
                case EXIT_VALUE: // Go back to the main menu
                    break;
                default:
                    System.out.println("Unsupported option. Please try again.");
            }

            // Exit if EXIT_VALUE (3) given
        } while (selection != EXIT_VALUE);
    }

    public static void challengeMenu(Scanner keyboard, char[][] bitmap, char bgChar) {
        char[][] sampleCanvas = bitmap;
        int selection;
        final int PREVIEW_SAMPLE_VALUE = 1;
        final int START_EDIT_VALUE = 2;
        final int CHECK_RESULT_VALUE = 3;
        final int EXIT_VALUE = 4;

        // Define Canvas and pass scanner object
        DrawingCanvas canvas = new DrawingCanvas(keyboard, bitmap, bgChar);
        do {
            // Prompt user for menu selection (1,2,3,4)
            System.out.println("Please select an option. Type 4 to go back to the main menu.");
            System.out.println(
                    "1. Preview the sample drawing\n2. Start/edit the current canvas\n3. Check result\n4. Go back to the main menu");
            selection = Integer.parseInt(keyboard.nextLine());

            // Handle Menu Selection
            switch (selection) {
                case PREVIEW_SAMPLE_VALUE: // Displays sample drawing
                    System.out.println("This is your task. Just try to draw the same object. Enjoy!");
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
                    System.out.println("Unsupported option. Please try again.");
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
        System.out.println("And this is your drawing:");
        System.out.println(yourCanvas);
    }
}
