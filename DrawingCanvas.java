import java.util.Scanner;

public class DrawingCanvas {
    private int width; // width of the canvas
    private int height; // height of the canvas
    private char bgChar; // background character for the canvas
    private Scanner keyboard; // scanner to read input from keyboard

    // Constructor with all parameters
    public DrawingCanvas(int width, int height, char bgChar, Scanner kboard) {
        setWidth(width);
        setHeight(height);
        setBgChar(bgChar);
        this.keyboard = kboard;
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
    public String canvasString() {
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

    // Creates the character string that visualises a canvas with a triangle
    public String canvasString(Triangle triangle) {
        String canvas = ""; // Initialise empty canvas string
        int fromX = triangle.getXPosition();
        int fromY = triangle.getYPosition();

        // Add to canvas row by row
        int printX = triangle.getSideLength();
        for (int y = 0; y < height; y++) {
            // Add to canvas char by char
            for (int x = 0; x < width; x++) {
                if ((y >= fromY && y < fromY + triangle.getSideLength())
                        && (x >= fromX && x < fromX + printX + fromY)) {
                    canvas += triangle.getPChar();
                } else {
                    canvas += bgChar;
                }
            }
            printX--; // Ensures each row is shorter by one (to create triangle)
            canvas += "\n"; // Move to next line
        }

        return canvas;
    }

    // Creates the character string that visualises a canvas with a rectangle
    public String canvasString(Rectangle rectangle) {
        String canvas = ""; // Initialise empty canvas string
        int fromX = rectangle.getXPosition();
        int fromY = rectangle.getYPosition();

        // Add to canvas row by row
        for (int y = 0; y < height; y++) {
            int printX = rectangle.getWidth();
            // Add to canvas char by char
            for (int x = 0; x < width; x++) {
                if ((y >= fromY && y < fromY + rectangle.getHeight())
                        && (x >= fromX && x < fromX + rectangle.getWidth())) {
                    canvas += rectangle.getPChar();
                } else {
                    canvas += bgChar;
                }
                printX--;
            }
            // Move to next line
            canvas += "\n";
        }

        return canvas;
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
