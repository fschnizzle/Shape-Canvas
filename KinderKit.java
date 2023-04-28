import java.util.Scanner;

/**
 * COMP90041 Semester 1, 2023: Assignment 1
 * 
 * This program is a digital kinder kit that allows users to draw triangles and
 * rectangles on a canvas,
 * and update the canvas settings. The program takes command line arguments for
 * the canvas width, height,
 * and background character.
 * 
 * @author Flynn
 */
public class KinderKit {
    public static void main(String[] args) {

        // Define Scanner keyboard object
        // This will be used through whole program!
        Scanner keyboard = new Scanner(System.in);

        // Display Main Menu
        boolean exitMenu = false;

        // Initialise Canvas and pass initial arguments / scanner object
        DrawingCanvas canvas = new DrawingCanvas(args, keyboard);

        // Menu Interface
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
                    Triangle t1 = new Triangle(keyboard);
                    t1.triangleInputs(canvas);
                    break;
                case 2:
                    // Draw Rectangle
                    Rectangle r1 = new Rectangle(keyboard);
                    r1.rectangleInputs(canvas);
                    break;
                case 3:
                    // Update canvas settings
                    canvas.updateSettings();
                    break;
                case 4:
                    // Exit program
                    exitMenu = true;
                    break;
                default:
                    // Invalid menu option
                    System.out.println("Unsupported option. Please try again!");
            }

        }

        // Exit message
        System.out.println("Goodbye! We hope you had fun :)");
    }
}
