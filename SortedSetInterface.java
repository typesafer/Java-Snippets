The SortedSet Interface
A SortedSet is a Set that maintains its elements in ascending order, sorted according to the elements' natural ordering or according to a Comparator provided at SortedSet creation time. In addition to the normal Set operations, the SortedSet interface provides operations for the following:

    Range view — allows arbitrary range operations on the sorted set
    Endpoints — returns the first or last element in the sorted set
    Comparator access — returns the Comparator, if any, used to sort the set 

The code for the SortedSet interface follows.

public interface SortedSet<E> extends Set<E> {
    // Range-view
    SortedSet<E> subSet(E fromElement, E toElement);
    SortedSet<E> headSet(E toElement);
    SortedSet<E> tailSet(E fromElement);

    // Endpoints
    E first();
    E last();

    // Comparator access
    Comparator<? super E> comparator();
}

Set Operations

    The operations that SortedSet inherits from Set behave identically on sorted sets and normal sets with two exceptions:

        The Iterator returned by the iterator operation traverses the sorted set in order.
        The array returned by toArray contains the sorted set's elements in order. 

    Although the interface doesn't guarantee it, the toString method of the Java platform's SortedSet implementations returns a string containing all the elements of the sorted set, in order. 

Standard Constructors

    By convention, all general-purpose Collection implementations provide a standard conversion constructor that takes a Collection; SortedSet implementations are no exception. In TreeSet, this constructor creates an instance that sorts its elements according to their natural ordering. This was probably a mistake. It would have been better to check dynamically to see whether the specified collection was a SortedSet instance and, if so, to sort the new TreeSet according to the same criterion (comparator or natural ordering). Because TreeSet took the approach that it did, it also provides a constructor that takes a SortedSet and returns a new TreeSet containing the same elements sorted according to the same criterion. Note that it is the compile-time type of the argument, not its runtime type, that determines which of these two constructors is invoked (and whether the sorting criterion is preserved).

    SortedSet implementations also provide, by convention, a constructor that takes a Comparator and returns an empty set sorted according to the specified Comparator. If null is passed to this constructor, it returns a set that sorts its elements according to their natural ordering. 

Range-view Operations

    The range-view operations are somewhat analogous to those provided by the List interface, but there is one big difference. Range views of a sorted set remain valid even if the backing sorted set is modified directly. This is feasible because the endpoints of a range view of a sorted set are absolute points in the element space rather than specific elements in the backing collection, as is the case for lists. A range-view of a sorted set is really just a window onto whatever portion of the set lies in the designated part of the element space. Changes to the range-view write back to the backing sorted set and vice versa. Thus, it's okay to use range-views on sorted sets for long periods of time, unlike range-views on lists.

    Sorted sets provide three range-view operations. The first, subSet, takes two endpoints, like subList. Rather than indices, the endpoints are objects and must be comparable to the elements in the sorted set, using the Set's Comparator or the natural ordering of its elements, whichever the Set uses to order itself. Like subList, the range is half open, including its low endpoint but excluding the high one.

    Thus, the following line of code tells you how many words between "doorbell" and "pickle", including "doorbell" but excluding "pickle", are contained in a SortedSet of strings called dictionary:

    int count = dictionary.subSet("doorbell", "pickle").size();

    In like manner, the following one-liner removes all the elements beginning with the letter f.

    dictionary.subSet("f", "g").clear();

    A similar trick can be used to print a table telling you how many words begin with each letter.

    for (char ch = 'a'; ch <= 'z'; ) {
        String from = String.valueOf(ch++);
        String to = String.valueOf(ch);
        System.out.println(from + ": " +
            dictionary.subSet(from, to).size());
    }

    Suppose you want to view a closed interval, which contains both of its endpoints, instead of an open interval. If the element type allows for the calculation of the successor of a given value in the element space, merely request the subSet from lowEndpoint to successor(highEndpoint). Although it isn't entirely obvious, the successor of a string s in String's natural ordering is s + "\0" — that is, s with a null character appended.

    Thus, the following one-liner tells you how many words between "doorbell" and "pickle", including doorbell and pickle, are contained in the dictionary.

    count = dictionary.subSet("doorbell", "pickle\0").size();

    A similar technique can be used to view an open interval, which contains neither endpoint. The open-interval view from lowEndpoint to highEndpoint is the half-open interval from successor(lowEndpoint) to highEndpoint. Use the following to calculate the number of words between "doorbell" and "pickle", excluding both.

    count = dictionary.subSet("doorbell\0", "pickle").size();

    The SortedSet interface contains two more range-view operations — headSet and tailSet, both of which take a single Object argument. The former returns a view of the initial portion of the backing SortedSet, up to but not including the specified object. The latter returns a view of the final portion of the backing SortedSet, beginning with the specified object and continuing to the end of the backing SortedSet. Thus, the following code allows you to view the dictionary as two disjoint volumes (a-m and n-z).

    SortedSet<String> volume1 = dictionary.headSet("n");
    SortedSet<String> volume2 = dictionary.tailSet("n");

Endpoint Operations

    The SortedSet interface contains operations to return the first and last elements in the sorted set, not surprisingly called first and last. In addition to their obvious uses, last allows a workaround for a deficiency in the SortedSet interface. One thing you'd like to do with a SortedSet is to go into the interior of the Set and iterate forward or backward. It's easy enough to go forward from the interior: Just get a tailSet and iterate over it. Unfortunately, there's no easy way to go backward.

    The following idiom obtains the first element that is less than a specified object o in the element space.

    Object predecessor = ss.headSet(o).last();

    This is a fine way to go one element backward from a point in the interior of a sorted set. It could be applied repeatedly to iterate backward, but this is very inefficient, requiring a lookup for each element returned. 

Comparator Accessor

    The SortedSet interface contains an accessor method called comparator that returns the Comparator used to sort the set, or null if the set is sorted according to the natural ordering of its elements. This method is provided so that sorted sets can be copied into new sorted sets with the same ordering. It is used by the SortedSet constructor described previously. 
