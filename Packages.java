Creating and Using Packages
To make types easier to find and use, to avoid naming conflicts, and to control access, programmers bundle groups of related types into packages.

Definition:  A package is a grouping of related types providing access protection and name space management. Note that types refers to classes, interfaces, enumerations, and annotation types. Enumerations and annotation types are special kinds of classes and interfaces, respectively, so types are often referred to in this lesson simply as classes and interfaces.

The types that are part of the Java platform are members of various packages that bundle classes by function: fundamental classes are in java.lang, classes for reading and writing (input and output) are in java.io, and so on. You can put your types in packages too.

Suppose you write a group of classes that represent graphic objects, such as circles, rectangles, lines, and points. You also write an interface, Draggable, that classes implement if they can be dragged with the mouse.

//in the Draggable.java file
public interface Draggable {
    . . .
}

//in the Graphic.java file
public abstract class Graphic {
    . . .
}

//in the Circle.java file
public class Circle extends Graphic implements Draggable {
    . . .
}

//in the Rectangle.java file
public class Rectangle extends Graphic implements Draggable {
    . . .
}

//in the Point.java file
public class Point extends Graphic implements Draggable {
    . . .
}

//in the Line.java file
public class Line extends Graphic implements Draggable {
    . . .
}

You should bundle these classes and the interface in a package for several reasons, including the following:

    You and other programmers can easily determine that these types are related.
    You and other programmers know where to find types that can provide graphics-related functions.
    The names of your types won't conflict with the type names in other packages because the package creates a new namespace.
    You can allow types within the package to have unrestricted access to one another yet still restrict access for types outside the package. 

