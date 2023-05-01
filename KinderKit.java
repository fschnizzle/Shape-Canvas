import java.util.Scanner;
import java.util.ArrayList;

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
    static Scanner keyboard = new Scanner(System.in); // Scanner Object
    static DrawingCanvas canvas;

    public static void main(String[] args) {

        // displayMenu()
        menuSelection();

        // Exit message
        System.out.println("Goodbye! We hope you had fun :)");
        System.exit(0);
    }

    public static void menuSelection() {
        boolean exitMenu = false;

        // Loop until option 4 is selected
        while (!exitMenu) {
            // Prompts user for menu selection
            System.out.println("Please select an option. Type 3 to exit.\n" +
                    "1. Draw triangles [CHANGE LATER]\n2. Freestyle Drawing\n3. Exit");

            // Takes input
            int menuCase = Integer.parseInt(keyboard.nextLine());
            exitMenu = handleMenuSelection(menuCase);
        }
    }

    public static Boolean handleMenuSelection(int selection) {
        // Switch for menu items (cases: 1-4)
        switch (selection) {
            case 1:

                break;
            case 2:
                freestyleMenu();

                // Draw Rectangle
                // Rectangle r1 = new Rectangle(keyboard);
                // r1.rectangleInputs(canvas);
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
        // Define Canvas and pass scanner object
        canvas = new DrawingCanvas(keyboard);
        do {

            System.out.println("Please select an option. Type 3 to go back to the main menu");
            System.out.println(
                    "1. Start/edit your current canvas\n2. Share your current drawing\n3. Go back to the main menu");
            selection = Integer.parseInt(keyboard.nextLine());
            switch (selection) {
                case 1: // Start/edit your current canvas
                    startEditCanvasMenu();
                    break;
                case 2: // Share your current drawing
                    break;
                case 3: // Go back to the main menu
                    break;
                default:
                    System.out.println("Invalid Option. Type 1-3:");
            }

        } while (selection != 3);
    }

    public static void startEditCanvasMenu() {
        int selection;
        do {
            System.out.println(canvas.displayCanvas());
            System.out.println(
                    "Please select an option. Type 4 to go back to the previous menu.\n1. Add a new Triangle\n2. Edit a triangle\n3. Remove a triangle\n4. Go back");
            selection = Integer.parseInt(keyboard.nextLine());
            switch (selection) {
                case 1: // Add a new Triangle
                    addTriangleMenu();
                    break;
                case 2: // Edit a triangle
                    editDeleteTriangleMenu(false);
                    break;
                case 3: // Remove a triangle
                    editDeleteTriangleMenu(true);
                    break;
                case 4: // Go back
                    break;
                default:
                    System.out.println("Invalid Option. Type 1-4:");
            }
        } while (selection != 4);

    }

    public static void addTriangleMenu() {
        // Draw Triangle
        Triangle triangle = new Triangle(keyboard);
        canvas.addTriangle(triangle);
        triangle.triangleInputs(canvas);
    }

    public static void editDeleteTriangleMenu(boolean deleteShape) {
        // Draw Triangle
        ArrayList<Triangle> triList = canvas.getTriangleList();
        int shapeNum = 1;
        int selectedShape;
        for (Triangle tri : triList) {
            System.out.printf("Shape #%d - Triangle: xPos = %d, yPos = %d, tChar = %c\n", shapeNum, tri.getXPosition(),
                    tri.getYPosition(), tri.getPChar());
            shapeNum++;
        }
        System.out.println("Index of the shape:");
        selectedShape = Integer.parseInt(keyboard.nextLine());
        if (deleteShape) {
            triList.remove(selectedShape);
        } else {
            triList.get(selectedShape).triangleInputs(canvas);
        }

    }
}
