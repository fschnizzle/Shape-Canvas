import java.util.Scanner;

public class Triangle {
    // Scanner keyboard = new Scanner(System.in);

    // Instance Variables
    private int sideLength;
    private char pChar; // Printing Character
    private int xPosition;
    private int yPosition;
    private Scanner keyboard;

    // Constructors
    public Triangle(Scanner kboard) {
        setSideLength();
        setPChar();
        this.keyboard = kboard;
    }

    public Triangle(int sideLength) {
        setSideLength(sideLength);
        setPChar();
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

    public void moveTriangle(DrawingCanvas canvas, int displaceX, int displaceY) {

        String selection = "a";
        while (selection.equals("a") || selection.equals("s")
                || selection.equals("w") || selection.equals("z")) {
            //
            this.setXPosition(displaceX);
            this.setYPosition(displaceY);
            System.out.print(canvas.canvasString(this));
            System.out.println(
                    "Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the Zooming/Moving menu.");
            selection = keyboard.nextLine();
            switch (selection.toLowerCase()) {
                case "a":
                    if (displaceX == 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceX--;
                    }
                    break;
                case "s":
                    if (displaceX + this.getSideLength() == canvas.getWidth()) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceX++;
                    }
                    break;
                case "w":
                    if (displaceY == 0) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceY--;
                    }
                    break;
                case "z":
                    if (displaceY + this.getSideLength() == canvas.getHeight()) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceY++;
                    }
                    break;
                default:
            }

        }

    }

    public void zoomTriangle(DrawingCanvas canvas, int displaceX, int displaceY) {

        String selection = "i";
        while (selection.equals("i") || selection.equals("o")) {
            //
            this.setXPosition(displaceX);
            this.setYPosition(displaceY);
            System.out.print(canvas.canvasString(this));
            System.out.println(
                    "Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving menu.");
            selection = keyboard.nextLine();
            switch (selection.toLowerCase()) {
                case "o":
                    if (displaceX + this.getSideLength() == canvas.getWidth()
                            || displaceY + this.getSideLength() == canvas.getHeight()) {
                        System.out.println("This triangle reaches its limit. You cannot make it bigger!!");
                    } else {
                        this.setSideLength(this.getSideLength() + 1);
                    }
                    break;
                case "i":
                    if (this.getSideLength() == 1) {
                        System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                    } else {
                        this.setSideLength(this.getSideLength() - 1);
                    }
                    break;
                default:
            }

        }

    }

    public void TriangleInputs(DrawingCanvas canvas) {

        String selection = "Y";

        while (selection.toUpperCase().equals("Y")) {
            // Starting Coordinates
            int displaceX = 0;
            int displaceY = 0;

            // Triangle Sidelength prompt / error detection
            do {
                System.out.print("Side length:\n");
                setSideLength(Integer.parseInt(keyboard.nextLine()));

                if (sideLength > canvas.getHeight() || sideLength > canvas.getWidth()) {
                    System.out.println(String.format(
                            "Error! The side length is too long (Current canvas size is %dx%d). Please try again.",
                            canvas.getWidth(), canvas.getHeight()));
                }

            } while (sideLength > canvas.getHeight() || sideLength > canvas.getWidth());
            System.out.print("Printing character:\n");
            setPChar(keyboard.nextLine().charAt(0));
            this.setXPosition(displaceX);
            this.setYPosition(displaceY);

            // Zoom and Move display / prompt loop
            selection = "Y";
            while (selection.toUpperCase().equals("Y") || selection.toUpperCase().equals("Z")
                    || selection.toUpperCase().equals("M")) {

                displaceX = this.getXPosition();
                displaceY = this.getYPosition();
                // Display Canvas with Triangle

                System.out.print(canvas.canvasString(this));

                // Prompt user for Zoom / Move controls
                System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
                selection = keyboard.nextLine();
                switch (selection.toLowerCase()) {
                    case "m":
                        // System.out.println("MOVE TRIANGLE SELECTED");
                        moveTriangle(canvas, displaceX, displaceY);
                        // Implement MOVE method
                        break;
                    case "z":
                        System.out.println("ZOOM IN OR OUT SELECTED");
                        zoomTriangle(canvas, displaceX, displaceY);
                        // Implement Zoom method
                        break;
                    default:
                }
            }

            // Create new triangle selection prompt
            System.out.println("Draw another triangle (Y/N)?");
            selection = keyboard.nextLine();
        }

    }

}
