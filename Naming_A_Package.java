Naming a Package
With programmers worldwide writing classes and interfaces using the Java programming language, it is likely that many programmers will use the same name for different types. In fact, the previous example does just that: It defines a Rectangle class when there is already a Rectangle class in the java.awt package. Still, the compiler allows both classes to have the same name if they are in different packages. The fully qualified name of each Rectangle class includes the package name. That is, the fully qualified name of the Rectangle class in the graphics package is graphics.Rectangle, and the fully qualified name of the Rectangle class in the java.awt package is java.awt.Rectangle.

This works well unless two independent programmers use the same name for their packages. What prevents this problem? Convention.

Naming Conventions
Package names are written in all lowercase to avoid conflict with the names of classes or interfaces.

Companies use their reversed Internet domain name to begin their package names—for example, com.example.orion for a package named orion created by a programmer at example.com.

Name collisions that occur within a single company need to be handled by convention within that company, perhaps by including the region or the project name after the company name (for example, com.company.region.package).

Packages in the Java language itself begin with java. or javax.

In some cases, the internet domain name may not be a valid package name. This can occur if the domain name contains a hyphen or other special character, if the package name begins with a digit or other character that is illegal to use as the beginning of a Java name, or if the package name contains a reserved Java keyword, such as "int". In this event, the suggested convention is to add an underscore. For example:

Legalizing Package Names Domain Name 	Package Name Prefix
clipart-open.org 	org.clipart_open
free.fonts.int 	int_.fonts.free
poetry.7days.com 	com._7days.poetry
