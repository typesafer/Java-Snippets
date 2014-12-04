Earlier we mentioned that English is ambiguous. The phrase "animal cage" can reasonably mean "all-animal cage", but it also suggests an entirely different concept: a cage designed not for any kind of animal, but rather for some kind of animal whose type is unknown. In generics, an unknown type is represented by the wildcard character "?".

To specify a cage capable of holding some kind of animal:

	Cage<? extends Animal> someCage = ...;

Read "? extends Animal" as "an unknown type that is a subtype of Animal, possibly Animal itself", which boils down to "some kind of animal". This is an example of a bounded wildcard, where Animal forms the upper bound of the expected type. If you're asked for a cage that simply holds some kind of animal, you're free to provide a lion cage or a butterfly cage.

    Note: It's also possible to specify a lower bound by using the super keyword instead of extends. The code <? super Animal>, therefore, would be read as "an unknown type that is a supertype of Animal, possibly Animal itself". You can also specify an unknown type with an unbounded wildcard, which simply looks like <?>. An unbounded wildcard is essentially the same as saying <? extends Object>. 

While Cage<Lion> and Cage<Butterfly> are not subtypes of Cage<Animal>, they are in fact subtypes of Cage<? extends Animal>:

	someCage = lionCage; // OK
	someCage = butterflyCage; // OK

So now the question becomes, "Can you add butterflies and lions directly to someCage?". As you can probably guess, the answer to this question is "no".

	someCage.add(king);	// compiler-time error
	someCage.add(monarch);	// compiler-time error

If someCage is a butterfly cage, it would hold butterflies just fine, but the lions would be able to break free. If it's a lion cage, then all would be well with the lions, but the butterflies would fly away. So if you can't put anything at all into someCage, is it useless? No, because you can still read its contents:

	void feedAnimals(Cage<? extends Animal> someCage) {
	    for (Animal a : someCage)
		a.feedMe();
	}

Therefore, you could house your animals in their individual cages, as shown earlier, and invoke this method first for the lions and then for the butterflies:

	feedAnimals(lionCage);
	feedAnimals(butterflyCage);

Or, you could choose to combine your animals in the all-animal cage instead:

	feedAnimals(animalCage);
