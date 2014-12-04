Using Package Members
The types that comprise a package are known as the package members.

To use a public package member from outside its package, you must do one of the following:

    Refer to the member by its fully qualified name
    Import the package member
    Import the member's entire package 

Each is appropriate for different situations, as explained in the sections that follow.
Referring to a Package Member by Its Qualified Name
So far, most of the examples in this tutorial have referred to types by their simple names, such as Rectangle and StackOfInts. You can use a package member's simple name if the code you are writing is in the same package as that member or if that member has been imported.

However, if you are trying to use a member from a different package and that package has not been imported, you must use the member's fully qualified name, which includes the package name. Here is the fully qualified name for the Rectangle class declared in the graphics package in the previous example.

graphics.Rectangle

You could use this qualified name to create an instance of graphics.Rectangle:

graphics.Rectangle myRect = new graphics.Rectangle();

Qualified names are all right for infrequent use. When a name is used repetitively, however, typing the name repeatedly becomes tedious and the code becomes difficult to read. As an alternative, you can import the member or its package and then use its simple name.
Importing a Package Member
To import a specific member into the current file, put an import statement at the beginning of the file before any type definitions but after the package statement, if there is one. Here's how you would import the Rectangle class from the graphics package created in the previous section.

import graphics.Rectangle;

Now you can refer to the Rectangle class by its simple name.

Rectangle myRectangle = new Rectangle();

This approach works well if you use just a few members from the graphics package. But if you use many types from a package, you should import the entire package.
Importing an Entire Package
To import all the types contained in a particular package, use the import statement with the asterisk (*) wildcard character.

import graphics.*;

Now you can refer to any class or interface in the graphics package by its simple name.

Circle myCircle = new Circle();
Rectangle myRectangle = new Rectangle();

The asterisk in the import statement can be used only to specify all the classes within a package, as shown here. It cannot be used to match a subset of the classes in a package. For example, the following does not match all the classes in the graphics package that begin with A.

import graphics.A*;     //does not work

Instead, it generates a compiler error. With the import statement, you generally import only a single package member or an entire package.
Note: Another, less common form of import allows you to import the public nested classes of an enclosing class. For example, if the graphics.Rectangle class contained useful nested classes, such as Rectangle.DoubleWide and Rectangle.Square, you could import Rectangle and its nested classes by using the following two statements.

import graphics.Rectangle;
import graphics.Rectangle.*;

Be aware that the second import statement will not import Rectangle.

Another less common form of import, the static import statement, will be discussed at the end of this section.
For convenience, the Java compiler automatically imports three entire packages for each source file: (1) the package with no name, (2) the java.lang package, and (3) the current package (the package for the current file).
Apparent Hierarchies of Packages
At first, packages appear to be hierarchical, but they are not. For example, the Java API includes a java.awt package, a java.awt.color package, a java.awt.font package, and many others that begin with java.awt. However, the java.awt.color package, the java.awt.font package, and other java.awt.xxxx packages are not included in the java.awt package. The prefix java.awt (the Java Abstract Window Toolkit) is used for a number of related packages to make the relationship evident, but not to show inclusion.

Importing java.awt.* imports all of the types in the java.awt package, but it does not import java.awt.color, java.awt.font, or any other java.awt.xxxx packages. If you plan to use the classes and other types in java.awt.color as well as those in java.awt, you must import both packages with all their files:

import java.awt.*;
import java.awt.color.*;

Name Ambiguities
If a member in one package shares its name with a member in another package and both packages are imported, you must refer to each member by its qualified name. For example, the graphics package defined a class named Rectangle. The java.awt package also contains a Rectangle class. If both graphics and java.awt have been imported, the following is ambiguous.

Rectangle rect;

In such a situation, you have to use the member's fully qualified name to indicate exactly which Rectangle class you want. For example,

graphics.Rectangle rect;

The Static Import Statement
There are situations where you need frequent access to static final fields (constants) and static methods from one or two classes. Prefixing the name of these classes over and over can result in cluttered code. The static import statement gives you a way to import the constants and static methods that you want to use so that you do not need to prefix the name of their class.

The java.lang.Math class defines the PI constant and many static methods, including methods for calculating sines, cosines, tangents, square roots, maxima, minima, exponents, and many more. For example,

public static final double PI 3.141592653589793
public static double cos(double a)

Ordinarily, to use these objects from another class, you prefix the class name, as follows.

double r = Math.cos(Math.PI * theta);

You can use the static import statement to import the static members of java.lang.Math so that you don't need to prefix the class name, Math. The static members of Math can be imported either individually:

import static java.lang.Math.PI;

or as a group:

import static java.lang.Math.*;

Once they have been imported, the static members can be used without qualification. For example, the previous code snippet would become:

double r = cos(PI * theta);

Obviously, you can write your own classes that contain constants and static methods that you use frequently, and then use the static import statement. For example,

import static mypackage.MyConstants.*;

Note: Use static import very sparingly. Overusing static import can result in code that is difficult to read and maintain, because readers of the code won't know which class defines a particular static object. Used properly, static import makes code more readable by removing class name repetition. 
