Type parameters can also be declared within method and constructor signatures to create generic methods and generic constructors. This is similar to declaring a generic type, but the type parameter's scope is limited to the method or constructor in which it's declared.

/**
 * This version introduces a generic method.
 */
public class Box<T> {

    private T t;          

    public void add(T t) {
        this.t = t;
    }

    public T get() {
        return t;
    }

    public <U> void inspect(U u){
        System.out.println("T: " + t.getClass().getName());
        System.out.println("U: " + u.getClass().getName());
    }

    public static void main(String[] args) {
        Box<Integer> integerBox = new Box<Integer>();
        integerBox.add(new Integer(10));
        integerBox.inspect("some text");
    }
}

Here we've added one generic method, named inspect, that defines one type parameter, named U. This method accepts an object and prints its type to standard output. For comparison, it also prints out the type of T. For convenience, this class now also has a main method so that it can be run as an application.

The output from this program is:

T: java.lang.Integer
U: java.lang.String

By passing in different types, the output will change accordingly.

A more realistic use of generic methods might be something like the following, which defines a static method that stuffs references to a single item into multiple boxes:

public static <U> void fillBoxes(U u, List<Box<U>> boxes) {
    for (Box<U> box : boxes) {
        box.add(u);
    }
}

To use this method, your code would look something like the following:

Crayon red = ...;
List<Box<Crayon>> crayonBoxes = ...;

The complete syntax for invoking this method is:

Box.<Crayon>fillBoxes(red, crayonBoxes);

Here we've explicitly provided the type to be used as U, but more often than not, this can be left out and the compiler will infer the type that's needed:

Box.fillBoxes(red, crayonBoxes); // compiler infers that U is Crayon

This feature, known as type inference, allows you to invoke a generic method as you would an ordinary method, without specifying a type between angle brackets.
