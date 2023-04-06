import java.util.Scanner;

public class Rectangle {
    Scanner keyboard = new Scanner(System.in);

    // Instance Variables
    private int height;
    private int width;
    private char pChar; // Printing Character
    private int xPosition;
    private int yPosition;

    // Constructors
    public Rectangle() {
        setHeight();
        setWidth();
        setPChar();
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
}