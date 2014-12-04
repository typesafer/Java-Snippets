Type Erasure

When a generic type is instantiated, the compiler translates those types by a technique called type erasure — a process where the compiler removes all information related to type parameters and type arguments within a class or method. Type erasure enables Java applications that use generics to maintain binary compatibility with Java libraries and applications that were created before generics.

For instance, Box<String> is translated to type Box, which is called the raw type — a raw type is a generic class or interface name without any type arguments. This means that you can't find out what type of Object a generic class is using at runtime. The following operations are not possible:

    public class MyClass<E> {
        public static void myMethod(Object item) {
            if (item instanceof E) {  //Compiler error
                ...
            }
            E item2 = new E();   //Compiler error
            E[] iArray = new E[10]; //Compiler error
            E obj = (E)new Object(); //Unchecked cast warning
        }
    }

The operations shown in bold are meaningless at runtime because the compiler removes all information about the actual type argument (represented by the type parameter E) at compile time.

Type erasure exists so that new code may continue to interface with legacy code. Using a raw type for any other reason is considered bad programming practice and should be avoided whenever possible.

When mixing legacy code with generic code, you may encounter warning messages similar to the following:

Note: WarningDemo.java uses unchecked or unsafe operations.
Note: Recompile with -Xlint:unchecked for details.

This can happen when using an older API that operates on raw types, as shown in the following WarningDemo program:


public class WarningDemo {
    public static void main(String[] args){
        Box<Integer> bi;
        bi = createBox();
    }

    static Box createBox(){
        return new Box();
    }
}

Recompiling with -Xlint:unchecked reveals the following additional information:

WarningDemo.java:4: warning: [unchecked] unchecked conversion
found   : Box
required: Box<java.lang.Integer>
        bi = createBox();
                      ^
1 warning

