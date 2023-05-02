import java.util.Scanner;
import java.util.ArrayList;

public class DrawingCanvas {
    private int width; // width of the canvas
    private int height; // height of the canvas
    private char bgChar; // background character for the canvas
    private Scanner keyboard; // scanner to read input from keyboard
    private static ArrayList<Triangle> triangleList; // ArrayList of Triangles

    // Constructor with all parameters
    public DrawingCanvas(Scanner kboard) {
        this.keyboard = kboard;
        initialSettings();
        this.triangleList = new ArrayList<Triangle>();
    }

    // Constructor with sample canvas given
    public DrawingCanvas(Scanner kboard, char[][] sampleCanvas, char bgChar) {
        this.keyboard = kboard;

        // Set up canvas based on sampleCanvas details
        setWidth(sampleCanvas[1].length);
        setHeight(sampleCanvas.length);
        setBgChar(bgChar);

        // Initialise triangleList
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

    // Initialise canvasArray to bgChar
    public char[][] initCanvasArray() {
        char[][] canvasArray = new char[this.getHeight()][this.getWidth()];

        for (int y = 0; y < this.getHeight(); y++) {
            for (int x = 0; x < this.getWidth(); x++) {
                canvasArray[y][x] = this.getBgChar();
            }
        }

        return canvasArray;
    }

    // Add a Triangle to canvasArray
    public char[][] addTriToCanvasArray(char[][] cArray, Triangle triangle) {
        char[][] canvasArray = cArray;
        char[][] triangleArray = triangle.makeTriangleArray();
        int tStartX = triangle.getXPosition();
        int tStartY = triangle.getYPosition();

        int width = canvasArray[0].length;
        int height = canvasArray.length;

        for (int y = tStartY; y < tStartY + triangle.getSideLength(); y++) {
            for (int x = tStartX; x < tStartX + triangle.getSideLength(); x++) {
                if (triangleArray[y - tStartY][x - tStartX] != ' ') {
                    canvasArray[y][x] = triangleArray[y - tStartY][x - tStartX];
                }
            }
        }

        return canvasArray;
    }

    public String getCanvasString(char[][] canvasArray) {
        String[] rowStrings = new String[canvasArray.length];

        // Converts each row of char array into string
        for (int y = 0; y < canvasArray.length; y++) {
            rowStrings[y] = new String(canvasArray[y]);
        }

        // Appends row to canvasString
        String canvasString = String.join("\n", rowStrings);
        return canvasString;
    }

    public String displayShareCanvas() {
        // Retrieve canvasString and Initialise empty csv string
        String canvasString = this.displayCanvas();
        String canvasCSVString = "";

        // Add canvas head
        canvasCSVString += String.format("%d,%d,%c\n", this.getWidth(), this.getHeight(), this.getBgChar());

        // Add character by character to canvasCSVString
        for (int i = 0; i < canvasString.length() - 1; i++) {
            char curChar = canvasString.charAt(i); // Current character

            // Append current char
            canvasCSVString += curChar;

            // Append comma if current char is not newLine characyer
            if (canvasString.charAt(i) != '\n' && canvasString.charAt(i + 1) != '\n') {
                canvasCSVString += ',';
            }
        }

        // Add last character
        canvasCSVString += canvasString.charAt(canvasString.length() - 1);

        return canvasCSVString;
    }

    // Method: addTriangle(Triangle triangle)
    public void addTriangle(Triangle triangle) {
        this.triangleList.add(triangle);
    }

    // Method: deleteTriangle(Triangle triangle)
    public void deleteTriangle(Triangle triangle) {
        this.triangleList.remove(triangle);
    }

    // Creates the character string that visualises a canvas with a triangle
    public String displayCanvas() {
        ArrayList<Triangle> triangleList = this.triangleList;

        // NEW CODE
        char[][] canvasArray = this.initCanvasArray();
        for (Triangle triangle : triangleList) {
            canvasArray = this.addTriToCanvasArray(canvasArray, triangle);
        }
        return getCanvasString(canvasArray);
    }

    // NOT IN USE: Creates the character string that visualises a canvas with a
    // rectangle
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

    public void initialSettings() {
        // Parse command line arguments
        System.out.print("Canvas width: ");
        int canvasWidth = Integer.parseInt(keyboard.nextLine());
        System.out.print("Canvas height: ");
        int canvasHeight = Integer.parseInt(keyboard.nextLine());
        System.out.print("Background character: ");
        char bgChar = keyboard.nextLine().charAt(0);

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

    public void startEditCanvasMenu() {
        int selection;
        do {
            System.out.println(this.displayCanvas());
            System.out.println(
                    "Please select an option. Type 4 to go back to the previous menu.\n1. Add a new Triangle\n2. Edit a triangle\n3. Remove a triangle\n4. Go back");
            selection = Integer.parseInt(keyboard.nextLine());
            switch (selection) {
                case 1: // Add a new Triangle
                    addTriangleMenu();
                    break;
                case 2: // Edit a triangle
                    if (this.getTriangleList().size() > 0) {
                        editDeleteTriangleMenu(false);
                    } else {
                        System.out.println("The current canvas is clean; there are no shapes to remove!");
                    }
                    break;
                case 3: // Remove a triangle
                    if (this.getTriangleList().size() > 0) {
                        editDeleteTriangleMenu(true);
                    } else {
                        System.out.println("The current canvas is clean; there are no shapes to remove!");
                    }
                    break;
                case 4: // Go back
                    break;
                default:
                    System.out.println("Invalid Option. Type 1-4:");
            }
        } while (selection != 4);

    }

    public void addTriangleMenu() {
        // Draw Triangle
        Triangle triangle = new Triangle(keyboard);
        this.addTriangle(triangle);
        triangle.triangleInputs(this);
    }

    public void editDeleteTriangleMenu(boolean deleteShape) {
        ArrayList<Triangle> triList = this.getTriangleList();
        int shapeNum = 1;
        int selectedShape;

        for (Triangle tri : triList) {
            System.out.printf("Shape #%d - Triangle: xPos = %d, yPos = %d, tChar = %c\n", shapeNum, tri.getXPosition(),
                    tri.getYPosition(), tri.getPChar());
            shapeNum++;
        }
        System.out.println("Index of the shape:");
        selectedShape = Integer.parseInt(keyboard.nextLine()) - 1;
        if (selectedShape >= 0 && selectedShape <= triList.size() + 1)
            if (deleteShape) {
                // Delete condition given
                triList.remove(selectedShape);
            } else {
                // Edit triangle
                triList.get(selectedShape).triangleInputs(this);
            }
        else if (selectedShape <= 0) {
            System.out.println("The shape index cannot be smaller than the number of shapes!");
        } else {
            System.out.println("The shape index cannot be larger than the number of shapes!");
        }

    }

}
