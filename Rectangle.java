// import java.util.Scanner;

// public class Rectangle {

// // Instance Variables
// private int height; // height of the rectangle
// private int width; // width of the rectangle
// private char pChar; // printing character of the rectangle
// private int xPosition; // starting X position of the rectangle
// private int yPosition; // starting Y position of the rectangle
// private Scanner keyboard;

// // Constructors
// public Rectangle(Scanner kboard) {
// setHeight();
// setWidth();
// setPChar();
// this.keyboard = kboard;
// }

// // Accessors
// public int getWidth() {
// return width;
// }

// public int getHeight() {
// return height;
// }

// public char getPChar() {
// return pChar;
// }

// public int getXPosition() {
// return xPosition;
// }

// public int getYPosition() {
// return yPosition;
// }

// // Mutators
// public void setWidth(int width) {
// if (width > 0) {
// this.width = width;
// } else {
// System.err.println("Error! Width must be postive");
// }
// }

// public void setWidth() {
// this.width = 0;
// }

// public void setHeight(int height) {
// if (height > 0) {
// this.height = height;
// } else {
// System.err.println("Error! Height must be postive");
// }
// }

// public void setHeight() {
// this.height = 0;
// }

// public void setPChar(char pChar) {
// this.pChar = pChar;
// }

// public void setPChar() {
// this.pChar = '*';
// }

// public void setXPosition(int xPosition) {
// this.xPosition = xPosition;
// }

// public void setYPosition(int yPosition) {
// this.yPosition = yPosition;
// }

// // Methods

// // Method that allows a user to move this rectangle on a given DrawingCanvas
// in
// // any direction if it remains entirely on the canvas
// public void moveRectangle(DrawingCanvas canvas, int displaceX, int displaceY)
// {

// String selection;
// // do-while loop until user selects other than {'a','s','w','z'}
// do {
// // Update X & Y position
// this.setXPosition(displaceX);
// this.setYPosition(displaceY);

// // Display canvas with rectangle
// System.out.print(canvas.displayCanvas(this));

// // Prompt user for left, right, up, down (A,S,W,Z) input
// System.out.println(
// "Type A/S/W/Z to move left/right/up/down. Use other keys to go back to the
// Zooming/Moving menu.");
// selection = keyboard.nextLine();

// // Moves rectangle according to selection using switch cases
// switch (selection.toLowerCase()) {
// case "a": // Move left case
// if (displaceX == 0) {
// System.out.println("You cannot move this rectangle outside of the drawing
// canvas!");
// } else {
// displaceX--;
// }
// break;
// case "s": // Move right case
// if (displaceX + this.getWidth() == canvas.getWidth()) {
// System.out.println("You cannot move this rectangle outside of the drawing
// canvas!");
// } else {
// displaceX++;
// }
// break;
// case "w": // Move up case
// if (displaceY == 0) {
// System.out.println("You cannot move this rectangle outside of the drawing
// canvas!");
// } else {
// displaceY--;
// }
// break;
// case "z": // Move down case
// if (displaceY + this.getHeight() == canvas.getHeight()) {
// System.out.println("You cannot move this rectangle outside of the drawing
// canvas!");
// } else {
// displaceY++;
// }
// break;
// default:
// }

// } while (selection.equals("a") || selection.equals("s")
// || selection.equals("w") || selection.equals("z"));

// }

// // Method that allows a user to scale this rectangle by one
// // in both dimensions if it remains entirely on the canvas
// public void zoomRectangle(DrawingCanvas canvas, int displaceX, int displaceY)
// {

// String selection;
// // do-while loop until user selects other than {'i','o'}
// do {
// // Update X & Y position
// this.setXPosition(displaceX);
// this.setYPosition(displaceY);

// // Prompt user for zoom in, out (I,O) input
// System.out.print(canvas.displayCanvas(this));
// System.out.println(
// "Type I/O to zoom in/out. Use other keys to go back to the Zooming/Moving
// menu.");
// selection = keyboard.nextLine();

// // Resizes triangle according to selection using switch cases
// switch (selection.toLowerCase()) {
// case "i": // Zoom in case
// if (displaceX + this.getWidth() == canvas.getWidth()
// || displaceY + this.getHeight() == canvas.getHeight()) {
// System.out.println("This rectangle reaches its limit. You cannot make it
// bigger!");
// } else {
// // Increment height and width
// this.setHeight(this.getHeight() + 1);
// this.setWidth(this.getWidth() + 1);
// }
// break;
// case "o": // Zoom in case
// if (this.getHeight() == 1 || this.getWidth() == 1) {
// System.out.println("This rectangle reaches its limit. You cannot make it
// smaller!");
// } else {
// // Decrement height and width
// this.setHeight(this.getHeight() - 1);
// this.setWidth(this.getWidth() - 1);
// }
// break;
// default:
// }
// } while (selection.equals("i") || selection.equals("o"));
// }

// public void rectangleInputs(DrawingCanvas canvas) {

// String selection;
// // do-while loop until user selects other than 'Y'
// do {
// // Initialise starting Coordinates
// int displaceX = 0;
// int displaceY = 0;

// // Rectangle dimensions prompt and error detection
// // Check width
// do {
// System.out.print("width:\n");
// setWidth(Integer.parseInt(keyboard.nextLine()));

// // Error detection to ensure triangle is within canvas
// if (this.getWidth() > canvas.getWidth()) {
// System.out.println(String.format(
// "Error! The width is too large (Current canvas size is %dx%d). Please try
// again.",
// canvas.getWidth(), canvas.getHeight()));
// }

// } while (this.getWidth() > canvas.getWidth());

// // Check height
// do {
// System.out.print("height:\n");
// setHeight(Integer.parseInt(keyboard.nextLine()));
// if (this.getHeight() > canvas.getHeight()) {
// System.out.println(String.format(
// "Error! The height is too large (Current canvas size is %dx%d). Please try
// again.",
// canvas.getWidth(), canvas.getHeight()));
// }

// } while (this.getHeight() > canvas.getHeight());

// // Prompts user for printing character
// System.out.print("Printing character:\n");
// setPChar(keyboard.nextLine().charAt(0));

// // Update coordinates
// this.setXPosition(displaceX);
// this.setYPosition(displaceY);

// // Zoom and Move display / prompt loop
// do {
// // Get initial coordinates
// displaceX = this.getXPosition();
// displaceY = this.getYPosition();

// // Display Canvas with Triangle
// System.out.print(canvas.displayCanvas(this));

// // Prompt user for Zoom / Move controls
// System.out.println("Type Z/M for zooming/moving. Use other keys to quit the
// Zooming/Moving mode.");
// selection = keyboard.nextLine();

// // Enter move or zoom functionality according to selection
// switch (selection.toLowerCase()) {
// case "m":
// // Enter move mode
// moveRectangle(canvas, displaceX, displaceY);
// break;
// case "z":
// // Enter zoom mode
// zoomRectangle(canvas, displaceX, displaceY);
// break;
// default:
// }
// } while (selection.toUpperCase().equals("Z")
// || selection.toUpperCase().equals("M"));

// // Create new rectangle selection prompt
// System.out.println("Draw another rectangle (Y/N)?");
// selection = keyboard.nextLine();

// } while (selection.toUpperCase().equals("Y"));

// }
// }