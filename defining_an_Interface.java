/*
Defining An Interface

An interface can contain constant declarations in addition to method declarations. 
All constant values defined in an interface are implicitly public, static, and final. 
Once again, these modifiers can be omitted.

*/
public interface GroupedInterface extends Interface1,
                                        Interface2, Interface3 {

   // constant declarations
   double E = 2.718282;  // base of natural logarithms

   // method signatures
   void doSomething (int i, double x);
   int doSomethingElse(String s);

}

