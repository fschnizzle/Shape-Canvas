import java.util.Scanner;

public class Triangle {
    Scanner keyboard = new Scanner(System.in);

    // Instance Variables
    private int sideLength;
    private char pChar; // Printing Character

    // Constructors
    public Triangle() {
        setSideLength();
        setPChar();
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

    // Mutators
    public void setSideLength(int sideLength) {
        if (sideLength > 0) {
            this.sideLength = sideLength;
        } else {
            System.err.println("Error! Side lengthust be postive");
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

    // Methods
    // public String TriangleString() {
    // int startX = 0;
    // int startY = 0;
    // String triangleString = "";
    // for (int y = sideLength + startY; y > startY; y--) {
    // for (int x = y; x > startX; x--) {
    // triangleString += pChar;
    // }
    // triangleString += '\n';
    // }

    // return triangleString;
    // }

    public void moveTriangle(DrawingCanvas canvas, int displaceX, int displaceY) {
        String selection = "a";
        while (selection.equals("a") || selection.equals("s")
                || selection.equals("w") || selection.equals("z")) {
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
                    if (displaceX + this.sideLength == canvas.getWidth()) {
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
                    if (displaceY + this.sideLength == canvas.getHeight()) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceY++;
                    }
                    break;
                default:
            }
            System.out.print(canvas.canvasString(this, displaceX, displaceY));

        }

    }

    public void TriangleInputs(DrawingCanvas canvas) {

        String selection = "Y";

        while (selection.toUpperCase().equals("Y")) {
            // Starting Coordinates
            int displaceX = 0;
            int displaceY = 0;

            // Triangle properties prompt and error detection
            do {
                System.out.print("Side length: ");
                setSideLength(Integer.parseInt(keyboard.nextLine()));
                System.out.print("Printing character: ");
                setPChar(keyboard.nextLine().charAt(0));

                if (sideLength > canvas.getHeight() || sideLength > canvas.getWidth()) {
                    System.out.println(String.format(
                            "Error! The side length is too long (Current canvas size is %dx%d). Please try again.",
                            canvas.getWidth(), canvas.getHeight()));
                }
            } while (sideLength > canvas.getHeight() || sideLength > canvas.getWidth());

            // Zoom and Move display / prompt loop
            selection = "Y";
            while (selection.toUpperCase().equals("Y") || selection.toUpperCase().equals("Z")
                    || selection.toUpperCase().equals("M")) {

                // Display Canvas with Triangle
                System.out.print(canvas.canvasString(this, displaceX, displaceY));

                // Prompt user for Zoom / Move controls
                System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
                selection = keyboard.nextLine();
                switch (selection.toLowerCase()) {
                    case "m":
                        System.out.println("MOVE TRIANGLE SELECTED");
                        moveTriangle(canvas, displaceX, displaceY);
                        // Implement MOVE method
                        break;
                    case "z":
                        System.out.println("ZOOM IN OR OUT SELECTED");
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
