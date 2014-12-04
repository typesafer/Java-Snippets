/*
Access level modifiers determine whether other classes can use a particular field or invoke a particular method. 
There are two levels of access control:

    At the top level—public, or package-private (no explicit modifier).
    At the member level—public, private, protected, or package-private (no explicit modifier).

Modifier 	Class 	Package 	Subclass 	World
public 	    Y       	Y 	        Y 	     Y
protected 	Y       	Y 	        Y 	     N
no modifier Y 	        Y 	        N 	     N
private 	Y 	        N 	        N 	     N

*/
