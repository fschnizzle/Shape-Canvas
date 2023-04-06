import java.util.Scanner;

public class DrawingCanvas {
    // Instance Variables
    private int width;
    private int height;
    private char bgChar;
    private Scanner keyboard;

    // Constructors
    public DrawingCanvas(int width, int height, char bgChar, Scanner kboard) {
        setWidth(width);
        setHeight(height);
        setBgChar(bgChar);

        this.keyboard = kboard;

        String canvas = canvasString();
    }

    public DrawingCanvas(int width, int height) {
        setWidth(width);
        setHeight(height);
        setBgChar();

        String canvas = canvasString();
    }

    // Accessors
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public char getBgChar() {
        return bgChar;
    }

    // Mutators
    public void setWidth(int width) {
        if (width >= 0) {
            this.width = width;
        } else {
            System.err.println("Invalid width value!");
        }
    }

    public void setHeight(int height) {
        if (height >= 0) {
            this.height = height;
        } else {
            System.err.println("Invalid height value!");
        }
    }

    public void setBgChar(char bgChar) {
        this.bgChar = bgChar;
    }

    // Default background character if none given
    public void setBgChar() {
        this.bgChar = '-';
    }

    // Creates the character string that visualises an empty canvas.
    public String canvasString() {
        String Canvas = ""; // Initialise empty canvas string

        // Add to canvas row by row
        for (int y = 0; y < height; y++) {
            // Add to canvas char by char
            for (int x = 0; x < width; x++) {
                Canvas += bgChar;
            }
            // Move to next line
            Canvas += "\n";
        }

        return Canvas;
    }

    // Creates the character string that visualises a canvas with a triangle
    public String canvasString(Triangle triangle) {
        String Canvas = ""; // Initialise empty canvas string
        int fromX = triangle.getXPosition();
        int fromY = triangle.getYPosition();

        // Add to canvas row by row
        int printX = triangle.getSideLength();
        for (int y = 0; y < height; y++) {
            // Add to canvas char by char
            for (int x = 0; x < width; x++) {
                if ((y >= fromY && y < fromY + triangle.getSideLength())
                        && (x >= fromX && x < fromX + printX + fromY)) {
                    Canvas += triangle.getPChar();

                } else {
                    Canvas += bgChar;
                }
            }
            printX--;
            // Move to next line
            Canvas += "\n";
        }

        return Canvas;
    }

    // Creates the character string that visualises a canvas with a triangle
    public String canvasString(Rectangle rectangle) {
        String Canvas = ""; // Initialise empty canvas string
        int fromX = rectangle.getXPosition();
        int fromY = rectangle.getYPosition();

        // Add to canvas row by row
        int printX = rectangle.getWidth();
        for (int y = 0; y < height; y++) {
            // Add to canvas char by char
            for (int x = 0; x < width; x++) {
                if ((y >= fromY && y < fromY + rectangle.getHeight())
                        && (x >= fromX && x < fromX + rectangle.getWidth())) {
                    Canvas += rectangle.getPChar();

                } else {
                    Canvas += bgChar;
                }
            }
            // Move to next line
            Canvas += "\n";
        }

        return Canvas;
    }

    public void updateSettings() {

        System.out.print("Canvas width: ");
        setWidth(Integer.parseInt(keyboard.nextLine()));
        System.out.print("Canvas height: ");
        setHeight(Integer.parseInt(keyboard.nextLine()));
        System.out.print("Background Character: ");
        setBgChar(keyboard.nextLine().charAt(0));

        System.out.print("Drawing canvas has been updated! ");

        System.out.println("Current drawing canvas settings:");

        System.out.println( // Display initial settings based on command line inputs
                String.format("- Width: %d\n- Height: %d\n- Background character: %c", this.getWidth(),
                        this.getHeight(), this.getBgChar()));
        System.out.println("\n");

    }

}