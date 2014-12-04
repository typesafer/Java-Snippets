/*
From within a constructor, you can also use the this keyword to call
another constructor in the same class. Doing so is called an explicit constructor invocation. 
Here's another Rectangle class, with a different
implementation from the one in the Objects section. 
*/

public class Rectangle {
    private int x, y;
    private int width, height;
	
    public Rectangle() {
        this(0, 0, 0, 0);
    }
    public Rectangle(int width, int height) {
        this(0, 0, width, height);
    }
    public Rectangle(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    ...
}
