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
    public String TriangleString() {
        String triangleString = "";
        for (int y = sideLength; y > 0; y--) {
            for (int x = y; x > 0; x--) {
                triangleString += pChar;
            }
            triangleString += '\n';
        }

        return triangleString;
    }

    public void TriangleInputs() {

        String selection = "Y";

        while (selection.toUpperCase().equals("Y")) {
            System.out.print("Side length: ");
            setSideLength(Integer.parseInt(keyboard.nextLine()));
            System.out.print("Printing character: ");
            setPChar(keyboard.nextLine().charAt(0));

            // Triangle Menu Selection
            selection = "Y";
            while (selection.toUpperCase().equals("Y") || selection.toUpperCase().equals("Z")
                    || selection.toUpperCase().equals("M")) {
                System.out.print(this.TriangleString()); // REPLACE with call to DrawingCanvas.displayShape(Triangle)
                                                         // method
                System.out.println("Type Z/M for zooming/moving. Use other keys to quit the Zooming/Moving mode.");
                selection = keyboard.nextLine();
                switch (selection) {
                    case "M":
                        System.out.println("MOVE TRIANGLE SELECTED");
                        break;
                    case "Z":
                        System.out.println("ZOOM IN OR OUT SELECTED");
                        break;
                    default:
                }
            }
            // Triangle new triangle selection
            System.out.println("Draw another triangle (Y/N)?");
            selection = keyboard.nextLine();
        }

    }

}
