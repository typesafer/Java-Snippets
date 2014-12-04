Using this with a Field
The most common reason for using the this keyword is because a field is shadowed by a method or constructor parameter.

For example, the Point class was written like this

public class Point {
    public int x = 0;
    public int y = 0;
	
    //constructor
    public Point(int a, int b) {
	x = a;
	y = b;
    }
}

but it could have been written like this:

public class Point {
    public int x = 0;
    public int y = 0;
	
    //constructor
    public Point(int x, int y) {
	this.x = x;
	this.y = y;
    }
}
