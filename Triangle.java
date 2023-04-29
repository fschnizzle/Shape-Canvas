import java.util.Scanner;

public class Triangle {

    // Instance Variables
    private int sideLength; // width of the triangle
    private char pChar; // printing character for the triangle
    private int xPosition; // starting X position of the triangle
    private int yPosition; // starting Y position of the triangle
    private int rotationX90; // Shape is rotated by this many degrees (times 90)
    private Scanner keyboard;

    // Constructors
    public Triangle(Scanner kboard) {
        setSideLength();
        setPChar();
        this.keyboard = kboard;
    }

    // Accessors
    public int getSideLength() {
        return sideLength;
    }

    public char getPChar() {
        return pChar;
    }

    public int getXPosition() {
        return xPosition;
    }

    public int getYPosition() {
        return yPosition;
    }

    // Mutators
    public void setSideLength(int sideLength) {
        if (sideLength > 0) {
            this.sideLength = sideLength;
        } else {
            System.err.println("Error! Side length must be postive");
        }
    }

    public void setSideLength() {
        this.sideLength = 0;
    }

    public void setPChar(char pChar) {
        this.pChar = pChar;
    }

    public void setPChar() {
        this.pChar = '*';
    }

    public void setXPosition(int xPosition) {
        this.xPosition = xPosition;
    }

    public void setYPosition(int yPosition) {
        this.yPosition = yPosition;
    }

    // Methods

    // INIT TRI ARRAY
    public char[][] makeTriangleArray() {
        int sideLength = this.getSideLength();
        char[][] triangleArray = new char[sideLength][sideLength];

        // Case 1: No Rotation
        for (int y = 0; y < sideLength; y++) {
            // printing character portion
            for (int x = 0; x < sideLength - y; x++) {
                triangleArray[x][y] = this.getPChar();
            }
            // empty character portion
            for (int x = sideLength - y; x < sideLength; x++) {
                triangleArray[x][y] = ' ';
            }
        }

        return triangleArray;
    }

    // Method that allows a user to move this triangle on a given DrawingCanvas in
    // any direction if it remains entirely on the canvas
    public void moveTriangle(DrawingCanvas canvas, int displaceX, int displaceY) {

        String selection;
        // do-while loop until user selects other than {'a','s','w','z'}
        do {
            // Update X & Y position
            this.setXPosition(displaceX);
            this.setYPosition(displaceY);

            // Display canvas with triangle
            System.out.print(canvas.displayCanvas());

            // Prompt user for left, right, up, down (A,S,W,Z) input
            System.out.println(
                    "Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
            selection = keyboard.nextLine();

            // Moves triangle according to selection using switch cases
            switch (selection.toLowerCase()) {
                case "a": // Move left case
                    if (displaceX == 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceX--;
                    }
                    break;
                case "s": // Move right case
                    if (displaceX + this.getSideLength() == canvas.getWidth()) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceX++;
                    }
                    break;
                case "w": // Move up case
                    if (displaceY == 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceY--;
                    }
                    break;
                case "z": // Move down case
                    if (displaceY + this.getSideLength() == canvas.getHeight()) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceY++;
                    }
                    break;
                default:
            }
        } while (selection.equals("a") || selection.equals("s") || selection.equals("w") || selection.equals("z"));
    }

    // Method that allows a user to scale this triangle by one
    // if it remains entirely on the canvas
    public void zoomTriangle(DrawingCanvas canvas, int displaceX, int displaceY) {

        String selection;
        // do-while loop until user selects other than {'i','o'}
        do {
            // Update X & Y position
            this.setXPosition(displaceX);
            this.setYPosition(displaceY);

            // Prompt user for zoom in, out (I,O) input
            System.out.print(canvas.displayCanvas());
            System.out.println(
                    "Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
            selection = keyboard.nextLine();

            // Resizes triangle according to selection using switch cases
            switch (selection.toLowerCase()) {
                case "i": // Zoom in case
                    if (displaceX + this.getSideLength() == canvas.getWidth()
                            || displaceY + this.getSideLength() == canvas.getHeight()) {
                        System.out.println("This triangle reaches its limit. You cannot make it bigger!");
                    } else {
                        // Increment sidelength
                        this.setSideLength(this.getSideLength() + 1);
                    }
                    break;
                case "o": // Zoom out case
                    if (this.getSideLength() == 1) {
                        System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                    } else {
                        // Decrement sidelength
                        this.setSideLength(this.getSideLength() - 1);
                    }
                    break;
                default:
            }
        } while (selection.equals("i") || selection.equals("o"));
    }

    public void triangleInputs(DrawingCanvas canvas) {

        String selection;
        // Initialise starting coordinates
        int displaceX = 0;
        int displaceY = 0;

        // Triangle Sidelength prompt / error detection do-while loop. Until valid input
        // given
        do {
            System.out.print("Side length:\n");
            setSideLength(Integer.parseInt(keyboard.nextLine()));

            // Error detection to ensure triangle is within canvas
            if (sideLength > canvas.getHeight() || sideLength > canvas.getWidth()) {
                System.out.println(String.format(
                        "Error! The side length is too long (Current canvas size is %dx%d). Please try again.",
                        canvas.getWidth(), canvas.getHeight()));
            }

        } while (sideLength > canvas.getHeight() || sideLength > canvas.getWidth());

        // Prompts user for printing character
        System.out.print("Printing character:\n");
        setPChar(keyboard.nextLine().charAt(0));

        // Update coordinates
        this.setXPosition(displaceX);
        this.setYPosition(displaceY);

        // Zoom and Move display / prompt loop
        do {
            // Get initial coordinates
            displaceX = this.getXPosition();
            displaceY = this.getYPosition();

            // Display Canvas with Triangle
            System.out.print(canvas.displayCanvas());

            // Prompt user for Zoom / Move controls
            System.out.println("Type Z/M/R for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
            selection = keyboard.nextLine();

            // Enter move or zoom functionality according to selection
            switch (selection.toLowerCase()) {
                case "m":
                    // Enter move mode
                    moveTriangle(canvas, displaceX, displaceY);
                    break;
                case "z":
                    // Enter Zoom mode
                    zoomTriangle(canvas, displaceX, displaceY);
                    break;
                case "r":
                    // Enter Rotate mode

                default:
            }
        } while (selection.toUpperCase().equals("Z")
                || selection.toUpperCase().equals("M"));

        // Create new triangle selection prompt
        System.out.println("Draw another triangle (Y/N)?");
        selection = keyboard.nextLine();
        if (selection.toUpperCase().equals("Y")) {
            canvas.addTriangle(new Triangle(keyboard));
        }

    }

}
