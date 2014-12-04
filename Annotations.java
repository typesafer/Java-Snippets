/*
Annotations provide data about a program that is not part of the program itself. They have no direct effect on the operation of the code they annotate.

Annotations have a number of uses, among them:

    Information for the compiler — Annotations can be used by the compiler to detect errors or suppress warnings.

    Compiler-time and deployment-time processing — Software tools can process annotation information to generate code, XML files, and so forth.

    Runtime processing — Some annotations are available to be examined at runtime.

Annotations can be applied to a program's declarations of classes, fields, methods, and other program elements.

The annotation appears first, often (by convention) on its own line, and may include elements with named or unnamed values:

@Author(
   name = "Benjamin Franklin",
   date = "3/27/2003"
)
class MyClass() { }

or

@SuppressWarnings(value = "unchecked")
void myMethod() { }

If there is just one element named "value," then the name may be omitted, as in:

@SuppressWarnings("unchecked")
void myMethod() { }

Also, if an annotation has no elements, the parentheses may be omitted, as in:

@Override
void mySuperMethod() { }

*/
