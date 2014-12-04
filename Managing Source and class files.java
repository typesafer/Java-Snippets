Managing Source and Class Files
Many implementations of the Java platform rely on hierarchical file systems to manage source and class files, although The Java Language Specification does not require this. The strategy is as follows.

Put the source code for a class, interface, enumeration, or annotation type in a text file whose name is the simple name of the type and whose extension is .java. For example:

//in the Rectangle.java file 
package graphics;
public class Rectangle {
   . . . 
}

Then, put the source file in a directory whose name reflects the name of the package to which the type belongs:

.....\graphics\Rectangle.java

The qualified name of the package member and the path name to the file are parallel, assuming the Microsoft Windows file name separator backslash (for Unix, use the forward slash).
class name 	graphics.Rectangle
pathname to file 	graphics\Rectangle.java

As you should recall, by convention a company uses its reversed Internet domain name for its package names. The Example company, whose Internet domain name is example.com, would precede all its package names with com.example. Each component of the package name corresponds to a subdirectory. So, if the Example company had a com.example.graphics package that contained a Rectangle.java source file, it would be contained in a series of subdirectories like this:

....\com\example\graphics\Rectangle.java

When you compile a source file, the compiler creates a different output file for each type defined in it. The base name of the output file is the name of the type, and its extension is .class. For example, if the source file is like this

//in the Rectangle.java file
package com.example.graphics;
public class Rectangle {
      . . . 
}

class Helper{
      . . . 
}

then the compiled files will be located at:

<path to the parent directory of the output files>\com\example\graphics\Rectangle.class
<path to the parent directory of the output files>\com\example\graphics\Helper.class

Like the .java source files, the compiled .class files should be in a series of directories that reflect the package name. However, the path to the .class files does not have to be the same as the path to the .java source files. You can arrange your source and class directories separately, as:

<path_one>\sources\com\example\graphics\Rectangle.java

<path_two>\classes\com\example\graphics\Rectangle.class

By doing this, you can give the classes directory to other programmers without revealing your sources. You also need to manage source and class files in this manner so that the compiler and the Java Virtual Machine (JVM) can find all the types your program uses.

The full path to the classes directory, <path_two>\classes, is called the class path, and is set with the CLASSPATH system variable. Both the compiler and the JVM construct the path to your .class files by adding the package name to the class path. For example, if

<path_two>\classes

is your class path, and the package name is

com.example.graphics,

then the compiler and JVM look for .class files in

<path_two>\classes\com\example\graphics.

A class path may include several paths, separated by a semicolon (Windows) or colon (Unix). By default, the compiler and the JVM search the current directory and the JAR file containing the Java platform classes so that these directories are automatically in your class path.
Setting the CLASSPATH System Variable
To display the current CLASSPATH variable, use these commands in Windows and Unix (Bourne shell):

In Windows:   C:\> set CLASSPATH
In Unix:      % echo $CLASSPATH

To delete the current contents of the CLASSPATH variable, use these commands:

In Windows:   C:\> set CLASSPATH=
In Unix:      % unset CLASSPATH; export CLASSPATH

To set the CLASSPATH variable, use these commands (for example):

In Windows:   C:\> set CLASSPATH=C:\users\george\java\classes
In Unix:      % CLASSPATH=/home/george/java/classes; export CLASSPATH

