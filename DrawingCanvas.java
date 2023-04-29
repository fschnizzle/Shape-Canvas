import java.util.Scanner;
import java.util.ArrayList;

public class DrawingCanvas {
    private int width; // width of the canvas
    private int height; // height of the canvas
    private char bgChar; // background character for the canvas
    private Scanner keyboard; // scanner to read input from keyboard
    private static ArrayList<Triangle> triangleList; // ArrayList of Triangles

    // Constructor with all parameters
    public DrawingCanvas(String[] kKargs, Scanner kboard) {
        initialSettings(kKargs);
        this.keyboard = kboard;
        this.triangleList = new ArrayList<Triangle>();
    }

    // Accessor method for the width
    public int getWidth() {
        return width;
    }

    // Accessor method for the height
    public int getHeight() {
        return height;
    }

    // Accessor method for the background character
    public char getBgChar() {
        return bgChar;
    }

    // Accessor method for the background character
    public ArrayList<Triangle> getTriangleList() {
        return triangleList;
    }

    // Mutator method for the width
    public void setWidth(int width) {
        if (width >= 0) {
            this.width = width;
        } else {
            System.err.println("Invalid width value!");
        }
    }

    // Mutator method for the height
    public void setHeight(int height) {
        if (height >= 0) {
            this.height = height;
        } else {
            System.err.println("Invalid height value!");
        }
    }

    // Mutator method for the background character
    public void setBgChar(char bgChar) {
        this.bgChar = bgChar;
    }

    // Creates the character string that visualises an empty canvas.
    public String displayCanvas(Boolean isEmpty) {
        String canvas = ""; // Initialise empty canvas string

        // Add to canvas row by row
        for (int y = 0; y < height; y++) {
            // Add to canvas char by char
            for (int x = 0; x < width; x++) {
                canvas += bgChar;
            }
            // Move to next line
            canvas += "\n";
        }

        return canvas;
    }

    // Method: addTriangle(Triangle triangle)
    public void addTriangle(Triangle triangle) {
        this.triangleList.add(triangle);
        System.out.println("Number of Triangles is now: " + this.triangleList.size());
    }

    // Method: deleteTriangle(Triangle triangle)
    public void deleteTriangle(Triangle triangle) {
        this.triangleList.remove(triangle);
    }

    // Creates the character string that visualises a canvas with a triangle
    public String displayCanvas() {
        String canvas = ""; // Initialise empty canvas string
        ArrayList<Triangle> triangleList = this.triangleList;
        Triangle curTriangle;

        // Perform following for all triangles in triangleList
        for (int i = 0; i < triangleList.size(); i++) {
            curTriangle = triangleList.get(i);
            int fromX = curTriangle.getXPosition();
            int fromY = curTriangle.getYPosition();

            // Add to canvas row by row
            int printX = curTriangle.getSideLength();
            for (int y = 0; y < height; y++) {
                // Add to canvas char by char
                for (int x = 0; x < width; x++) {
                    if ((y >= fromY && y < fromY + curTriangle.getSideLength())
                            && (x >= fromX && x < fromX + printX + fromY)) {
                        canvas += curTriangle.getPChar();
                    } else {
                        canvas += bgChar;
                    }
                }
                printX--; // Ensures each row is shorter by one (to create triangle)
                canvas += "\n"; // Move to next line
            }
        }

        return canvas;
    }

    // Creates the character string that visualises a canvas with a rectangle
    public String displayCanvas(Rectangle rectangle) {
        String canvas = ""; // Initialise empty canvas string
        int fromX = rectangle.getXPosition();
        int fromY = rectangle.getYPosition();

        // Add to canvas row by row
        for (int y = 0; y < height; y++) {
            // int printX = rectangle.getWidth();
            // Add to canvas char by char
            for (int x = 0; x < width; x++) {
                if ((y >= fromY && y < fromY + rectangle.getHeight())
                        && (x >= fromX && x < fromX + rectangle.getWidth())) {
                    canvas += rectangle.getPChar();
                } else {
                    canvas += bgChar;
                }
                // printX--;
            }
            // Move to next line
            canvas += "\n";
        }

        return canvas;
    }

    public void initialSettings(String[] kKargs) {
        // Check for correct number of command line arguments
        while (kKargs.length != 3) {
            System.out
                    .println("Error: Invalid number of arguments. Usage: java KinderKit <width> <height> <background>");
            kKargs[0] = "s";
            System.exit(0);
        }

        // Parse command line arguments
        int canvasWidth = Integer.parseInt(kKargs[0]);
        int canvasHeight = Integer.parseInt(kKargs[1]);
        char bgChar = kKargs[2].charAt(0);

        setWidth(canvasWidth);
        setHeight(canvasHeight);
        setBgChar(bgChar);

        // Display Welcome Message and initial canvas settings
        System.out.println("----DIGITAL KINDER KIT: LET'S PLAY & LEARN----\nCurrent drawing canvas settings:");
        System.out.printf("- Width: %d\n- Height: %d\n- Background character: %c\n\n", canvasWidth, canvasHeight,
                bgChar);
    }

    public void updateSettings() {

        // Prompt the user to enter the new canvas settings
        System.out.print("Canvas width: ");
        setWidth(Integer.parseInt(keyboard.nextLine()));
        System.out.print("Canvas height: ");
        setHeight(Integer.parseInt(keyboard.nextLine()));
        System.out.print("Background character: ");
        setBgChar(keyboard.nextLine().charAt(0));

        // Notify the user that the canvas settings have been updated
        System.out.println("Drawing canvas has been updated!\n");

        // Display the current canvas settings
        System.out.println("Current drawing canvas settings:");

        // Use String.format to display the current canvas settings
        System.out.println(
                String.format("- Width: %d\n- Height: %d\n- Background character: %c\n",
                        this.getWidth(), this.getHeight(), this.getBgChar()));
    }

}
