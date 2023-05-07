import java.util.Scanner;
import java.util.ArrayList;

public class DrawingCanvas {
    private int width; // width of the canvas
    private int height; // height of the canvas
    private char bgChar; // background character for the canvas
    private Scanner keyboard; // scanner to read input from keyboard
    private static ArrayList<Triangle> triangleList; // ArrayList of Triangles

    // Constructor with no given parameters
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

    // Initialise 'empty' canvasArray with bgChar as background character
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
        char[][] canvasArray = cArray; // Initialise canvasArray
        char[][] triangleArray = triangle.makeTriangleArray(); // Creates tri charArray
        int tStartX = triangle.getXPosition();
        int tStartY = triangle.getYPosition();

        int width = canvasArray[0].length;
        int height = canvasArray.length;

        // Checks chars within the 'triangle-box' (assumes triangle is within a
        // sideLength by sideLength rectangle regradless of rotation etc)
        for (int y = tStartY; y < tStartY + triangle.getSideLength(); y++) {
            for (int x = tStartX; x < tStartX + triangle.getSideLength(); x++) {
                if (triangleArray[y - tStartY][x - tStartX] != ' ') {
                    // Add triangleChar to canvasArray at x,y if non bg-char found
                    canvasArray[y][x] = triangleArray[y - tStartY][x - tStartX];
                }
            }
        }

        return canvasArray;
    }

    // Creates the string needed to display a canvas with triangles
    public String getCanvasString(char[][] canvasArray) {
        String[] rowStrings = new String[canvasArray.length];

        // Convert each row of char array into string
        for (int y = 0; y < canvasArray.length; y++) {
            rowStrings[y] = new String(canvasArray[y]);
        }

        // Append row to canvasString
        String canvasString = String.join("\n", rowStrings);
        return canvasString;
    }

    // Creates the share canvas output Stringf (Header + canvas string)
    public String displayShareCanvas() {
        // Retrieve canvasString and Initialise empty csv string
        String canvasString = this.displayCanvas();
        String canvasCSVString = "";

        // Add canvas head
        canvasCSVString += String.format("%d,%d,%c\n", this.getHeight(), this.getWidth(), this.getBgChar());

        // Add character by character to canvasCSVString
        for (int i = 0; i < canvasString.length() - 1; i++) {
            char curChar = canvasString.charAt(i); // Current character

            // Append current char
            canvasCSVString += curChar;

            // Append comma if current char is not newLine character
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
    // public String displayCanvas(Rectangle rectangle) {
    // String canvas = ""; // Initialise empty canvas string
    // int fromX = rectangle.getXPosition();
    // int fromY = rectangle.getYPosition();

    // // Add to canvas row by row
    // for (int y = 0; y < height; y++) {
    // // int printX = rectangle.getWidth();
    // // Add to canvas char by char
    // for (int x = 0; x < width; x++) {
    // if ((y >= fromY && y < fromY + rectangle.getHeight())
    // && (x >= fromX && x < fromX + rectangle.getWidth())) {
    // canvas += rectangle.getPChar();
    // } else {
    // canvas += bgChar;
    // }
    // // printX--;
    // }
    // // Move to next line
    // canvas += "\n";
    // }

    // return canvas;
    // }

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
    }

    public void updateSettings() {
        // Prompt user to enter new canvas settings
        System.out.print("Canvas width: ");
        setWidth(Integer.parseInt(keyboard.nextLine()));
        System.out.print("Canvas height: ");
        setHeight(Integer.parseInt(keyboard.nextLine()));
        System.out.print("Background character: ");
        setBgChar(keyboard.nextLine().charAt(0));

        // Canvas settings update confirmation message
        System.out.println("Drawing canvas has been updated!\n");

        // Display the current canvas settings
        System.out.println("Current drawing canvas settings:");
        System.out.println(
                String.format("- Width: %d\n- Height: %d\n- Background character: %c\n",
                        this.getWidth(), this.getHeight(), this.getBgChar()));
    }

    public void startEditCanvasMenu() {
        int selection;
        final int ADD_TRI_VALUE = 1;
        final int EDIT_TRI_VALUE = 2;
        final int REMOVE_TRI_VALUE = 3;
        final int EXIT_VALUE = 4;
        do {
            System.out.println(this.displayCanvas());
            System.out.println(
                    "Please select an option. Type 4 to go back to the previous menu.\n1. Add a new Triangle\n2. Edit a triangle\n3. Remove a triangle\n4. Go back");
            selection = Integer.parseInt(keyboard.nextLine());
            switch (selection) {
                case ADD_TRI_VALUE: // Add a new Triangle (1)
                    addTriangleMenu();
                    break;
                case EDIT_TRI_VALUE: // Edit a triangle (2)
                    // Check if triangle list is empty, otherwise display triangles for selection
                    if (this.getTriangleList().size() > 0) {
                        editDeleteTriangleMenu(false);
                    } else {
                        System.out.println("The current canvas is clean; there are no shapes to edit!");
                    }
                    break;
                case REMOVE_TRI_VALUE: // Remove a triangle (3)
                    if (this.getTriangleList().size() > 0) {
                        editDeleteTriangleMenu(true);
                    } else {
                        System.out.println("The current canvas is clean; there are no shapes to remove!");
                    }
                    break;
                case EXIT_VALUE: // Go back
                    break;
                default:
                    System.out.println("Invalid Option. Type 1-4:");
            }
            // Exit if EXIT_VALUE (4) given
        } while (selection != EXIT_VALUE);

    }

    public void addTriangleMenu() {
        Triangle triangle = new Triangle(keyboard); // Initialise new tri
        this.addTriangle(triangle); // Add to canvas TriangleList
        triangle.triangleInputs(this, false); // Take input details for new tri
    }

    public void editDeleteTriangleMenu(boolean deleteShape) {
        ArrayList<Triangle> triList = this.getTriangleList();
        int selectedShape;
        int shapeNum = 1; // Triangle counter

        // Display each triangle's information
        for (Triangle tri : triList) {
            System.out.printf("Shape #%d - Triangle: xPos = %d, yPos = %d, tChar = %c\n", shapeNum, tri.getXPosition(),
                    tri.getYPosition(), tri.getPChar());
            shapeNum++;
        }
        if (deleteShape) {
            // For delete tri menu
            System.out.println("Index of the shape to remove:");
        } else {
            // For edit tri menu
            System.out.println("Index of the shape:");
        }

        // Take user input selection of tri to alter
        selectedShape = Integer.parseInt(keyboard.nextLine()) - 1;
        if (selectedShape >= 0 && selectedShape <= triList.size())
            if (deleteShape) {
                // Delete condition given
                triList.remove(selectedShape);
            } else {
                // Edit triangle
                Triangle tri = triList.get(selectedShape);
                tri.triangleInputs(this, true);
            }
        // Input error detection. Ensure index is in valid range.
        else if (selectedShape <= 0) {
            System.out.println("The shape index cannot be smaller than the number of shapes!");
        } else {
            System.out.println("The shape index cannot be larger than the number of shapes!");
        }

    }

}
