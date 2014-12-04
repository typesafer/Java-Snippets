Implementations are the data objects used to store collections, which implement the interfaces described in the Interfaces lesson.

The Java Collections Framework provides several general-purpose implementations of the core interfaces:

    For the Set interface, HashSet is the most commonly used implementation.
    For the List interface, ArrayList is the most commonly used implementation.
    For the Map interface, HashMap is the most commonly used implementation.
    For the Queue interface, LinkedList is the most commonly used implementation.

Each of the general-purpose implementations provides all optional operations contained in its interface.

The Java Collections Framework also provides several special-purpose implementations for situations that require nonstandard performance, usage restrictions, or other unusual behavior.

The java.util.concurrent package contains several collections implementations, which are thread-safe but not governed by a single exclusion lock.

The Collections class (as opposed to the Collection interface), provides static methods that operate on or return collections, which are known as Wrapper implementations.

Finally, there are several Convenience implementations, which can be more efficient than general-purpose implementations when you don't need their full power. The Convenience implementations are made available through static factory methods. 
