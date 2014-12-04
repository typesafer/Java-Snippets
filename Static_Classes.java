/*
Terminology: Nested classes are divided into two categories: static and non-static. 
Nested classes that are declared static are simply called static nested classes.
Non-static nested classes are called inner classes. 
*/

class OuterClass {
    ...
    class NestedClass {
        ...
    }
}

class OuterClass {
    ...
    static class StaticNestedClass {
        ...
    }
    class InnerClass {
        ...
    }
}
