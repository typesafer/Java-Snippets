As you already know, it's possible to assign an object of one type to an object of another type provided that the types are compatible. For example, you can assign an Integer to an Object, since Object is one of Integer's supertypes:

    Object someObject = new Object();
    Integer someInteger = new Integer(10);
    someObject = someInteger; // OK

In object-oriented terminology, this is called an "is a" relationship. Since an Integer is a kind of Object, the assignment is allowed. But Integer is also a kind of Number, so the following code is valid as well:

    public void someMethod(Number n){
        // method body omitted 
    }

    someMethod(new Integer(10)); // OK
    someMethod(new Double(10.1)); // OK

The same is also true with generics. You can perform a generic type invocation, passing Number as its type argument, and any subsequent invocation of add will be allowed if the argument is compatible with Number:

    Box<Number> box = new Box<Number>();
    box.add(new Integer(10)); // OK
    box.add(new Double(10.1)); // OK

Now consider the following method:

    public void boxTest(Box<Number> n){
        // method body omitted 
    }

What type of argument does it accept? By looking at its signature, we can see that it accepts a single argument whose type is Box<Number>. But what exactly does that mean? Are you allowed to pass in Box<Integer> or Box<Double>, as you might expect? Surprisingly, the answer is "no", because Box<Integer> and Box<Double> are not subtypes of Box<Number>.

Understanding why becomes much easier if you think of tangible objects — things you can actually picture — such as a cage:

	// A cage is a collection of things, with bars to keep them in.
	interface Cage<E> extends Collection<E>;

    Note: The Collection interface is the root interface of the collection hierarchy; it represents a group of objects. Since a cage would be used for holding a collection of objects (the animals), it makes sense to include it in this example. 

A lion is a kind of animal, so Lion would be a subtype of Animal:

	interface Lion extends Animal {}
	Lion king = ...;

Where we need some animal, we're free to provide a lion:

	Animal a = king;

A lion can of course be put into a lion cage:

	Cage<Lion> lionCage = ...;
	lionCage.add(king);

and a butterfly into a butterfly cage:

	interface Butterfly extends Animal {}
	Butterfly monarch = ...;
	Cage<Butterfly> butterflyCage = ...;
	butterflyCage.add(monarch);

But what about an "animal cage"? English is ambiguous, so to be precise let's assume we're talking about an "all-animal cage":

	Cage<Animal> animalCage = ...;

This is a cage designed to hold all kinds of animals, mixed together. It must have bars strong enough to hold in the lions, and spaced closely enough to hold in the butterflies. Such a cage might not even be feasible to build, but if it is, then:

	animalCage.add(king);
	animalCage.add(monarch);

Since a lion is a kind of animal (Lion is a subtype of Animal), the question then becomes, "Is a lion cage a kind of animal cage? Is Cage<Lion> a subtype of Cage<Animal>?". By the above definition of animal cage, the answer must be "no". This is surprising! But it makes perfect sense when you think about it: A lion cage cannot be assumed to keep in butterflies, and a butterfly cage cannot be assumed to hold in lions. Therefore, neither cage can be considered an "all-animal" cage:

	animalCage = lionCage;	// compile-time error
        animalCage = butterflyCage; // compile-time error

Without generics, the animals could be placed into the wrong kinds of cages, where it would be possible for them to escape. 
