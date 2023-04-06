import java.util.Scanner;

public class Rectangle {
    // Scanner keyboard = new Scanner(System.in);

    // Instance Variables
    private int height;
    private int width;
    private char pChar;
    private int xPosition;
    private int yPosition;
    private Scanner keyboard;

    // Constructors
    public Rectangle(Scanner kboard) {
        setHeight();
        setWidth();
        setPChar();
        this.keyboard = kboard;
    }

    public Rectangle(int height, int width, char pChar) {
        setHeight(height);
        setWidth(width);
        setPChar(pChar);
    }

    // Accessors
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
    public void setWidth(int width) {
        if (width > 0) {
            this.width = width;
        } else {
            System.err.println("Error! Width must be postive");
        }
    }

    public void setWidth() {
        this.width = 0;
    }

    public void setHeight(int height) {
        if (height > 0) {
            this.height = height;
        } else {
            System.err.println("Error! Height must be postive");
        }
    }

    public void setHeight() {
        this.height = 0;
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

    // METHODS

    public void moveRectangle(DrawingCanvas canvas, int displaceX, int displaceY) {

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
                    if (displaceX + this.getWidth() == canvas.getWidth()) {
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
                    if (displaceY + this.getHeight() == canvas.getHeight()) {
                        System.out.println("You cannot move this triangle outside of the drawing canvas!");
                    } else {
                        displaceY++;
                    }
                    break;
                default:
            }

        }

    }

    public void zoomRectangle(DrawingCanvas canvas, int displaceX, int displaceY) {

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
                    if (displaceX + this.getWidth() == canvas.getWidth()
                            || displaceY + this.getHeight() == canvas.getHeight()) {
                        System.out.println("This triangle reaches its limit. You cannot make it bigger!!");
                    } else {
                        this.setHeight(this.getHeight() + 1);
                        this.setWidth(this.getWidth() + 1);
                    }
                    break;
                case "i":
                    if (this.getHeight() == 1 || this.getWidth() == 1) {
                        System.out.println("This triangle reaches its limit. You cannot make it smaller!");
                    } else {
                        this.setHeight(this.getHeight() - 1);
                        this.setWidth(this.getWidth() - 1);
                    }
                    break;
                default:
            }

        }

    }

    public void RectangleInputs(DrawingCanvas canvas) {

        String selection = "Y";

        while (selection.toUpperCase().equals("Y")) {
            // Starting Coordinates
            int displaceX = 0;
            int displaceY = 0;

            // Rectangle properties prompt and error detection
            do {
                System.out.print("width:\n");
                setWidth(Integer.parseInt(keyboard.nextLine()));
                System.out.print("height:\n");
                setHeight(Integer.parseInt(keyboard.nextLine()));
                System.out.print("Printing character:\n");
                setPChar(keyboard.nextLine().charAt(0));

                if (this.getHeight() > canvas.getHeight()) {
                    System.out.println(String.format(
                            "Error! The height is too large (Current canvas size is %dx%d). Please try again.",
                            canvas.getWidth(), canvas.getHeight()));
                } else if (this.getWidth() > canvas.getWidth()) {
                    System.out.println(String.format(
                            "Error! The width is too large (Current canvas size is %dx%d). Please try again.",
                            canvas.getWidth(), canvas.getHeight()));
                }
                this.setXPosition(displaceX);
                this.setYPosition(displaceY);
            } while (this.getHeight() > canvas.getHeight() || this.getWidth() > canvas.getWidth());

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
                        moveRectangle(canvas, displaceX, displaceY);
                        // Implement MOVE method
                        break;
                    case "z":
                        System.out.println("ZOOM IN OR OUT SELECTED");
                        zoomRectangle(canvas, displaceX, displaceY);
                        // Implement Zoom method
                        break;
                    default:
                }
            }

            System.out.print(canvas.canvasString(this));

            // Create new rectangle selection prompt
            System.out.println("Draw another rectangle (Y/N)?");
            selection = keyboard.nextLine();
        }

    }
}