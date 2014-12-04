Rewriting Interfaces
Consider an interface that you have developed called DoIt:

public interface DoIt {
   void doSomething(int i, double x);
   int doSomethingElse(String s);
}

Suppose that, at a later time, you want to add a third method to DoIt, so that the interface now becomes:

public interface DoIt {

   void doSomething(int i, double x);
   int doSomethingElse(String s);
   boolean didItWork(int i, double x, String s);
   
}

If you make this change, all classes that implement the old DoIt interface will break because they don't implement the interface anymore. Programmers relying on this interface will protest loudly.

Try to anticipate all uses for your interface and to specify it completely from the beginning. Given that this is often impossible, you may need to create more interfaces later. For example, you could create a DoItPlus interface that extends DoIt:

public interface DoItPlus extends DoIt {

   boolean didItWork(int i, double x, String s);
   
}

Now users of your code can choose to continue to use the old interface or to upgrade to the new interface. 
