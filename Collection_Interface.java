A Collection represents a group of objects known as its elements. The Collection interface is used to pass around collections of objects where maximum generality is desired. For example, by convention all general-purpose collection implementations have a constructor that takes a Collection argument. This constructor, known as a conversion constructor, initializes the new collection to contain all of the elements in the specified collection, whatever the given collection's subinterface or implementation type. In other words, it allows you to convert the collection's type.

Suppose, for example, that you have a Collection<String> c, which may be a List, a Set, or another kind of Collection. This idiom creates a new ArrayList (an implementation of the List interface), initially containing all the elements in c.

List<String> list = new ArrayList<String>(c);

The following shows the Collection interface.

public interface Collection<E> extends Iterable<E> {
    // Basic operations
    int size();
    boolean isEmpty();
    boolean contains(Object element);
    boolean add(E element);         //optional
    boolean remove(Object element); //optional
    Iterator<E> iterator();

    // Bulk operations
    boolean containsAll(Collection<?> c);
    boolean addAll(Collection<? extends E> c); //optional
    boolean removeAll(Collection<?> c);        //optional
    boolean retainAll(Collection<?> c);        //optional
    void clear();                              //optional

    // Array operations
    Object[] toArray();
    <T> T[] toArray(T[] a);
}

The interface does about what you'd expect given that a Collection represents a group of objects. The interface has methods to tell you how many elements are in the collection (size, isEmpty), to check whether a given object is in the collection (contains), to add and remove an element from the collection (add, remove), and to provide an iterator over the collection (iterator).

The add method is defined generally enough so that it makes sense for collections that allow duplicates as well as those that don't. It guarantees that the Collection will contain the specified element after the call completes, and returns true if the Collection changes as a result of the call. Similarly, the remove method is designed to remove a single instance of the specified element from the Collection, assuming that it contains the element to start with, and to return true if the Collection was modified as a result.
Traversing Collections

    There are two ways to traverse collections: (1) with the for-each construct and (2) by using Iterators.
    for-each Construct
    The for-each construct allows you to concisely traverse a collection or array using a for loop — see The for Statement. The following code uses the for-each construct to print out each element of a collection on a separate line.

    for (Object o : collection)
        System.out.println(o);

    Iterators
    An Iterator is an object that enables you to traverse through a collection and to remove elements from the collection selectively, if desired. You get an Iterator for a collection by calling its iterator method. The following is the Iterator interface.

    public interface Iterator<E> {
        boolean hasNext();
        E next();
        void remove(); //optional
    }

    The hasNext method returns true if the iteration has more elements, and the next method returns the next element in the iteration. The remove method removes the last element that was returned by next from the underlying Collection. The remove method may be called only once per call to next and throws an exception if this rule is violated.

    Note that Iterator.remove is the only safe way to modify a collection during iteration; the behavior is unspecified if the underlying collection is modified in any other way while the iteration is in progress.

    Use Iterator instead of the for-each construct when you need to:

        Remove the current element. The for-each construct hides the iterator, so you cannot call remove. Therefore, the for-each construct is not usable for filtering.
        Iterate over multiple collections in parallel. 

    The following method shows you how to use an Iterator to filter an arbitrary Collection — that is, traverse the collection removing specific elements.

    static void filter(Collection<?> c) {
        for (Iterator<?> it = c.iterator(); it.hasNext(); )
            if (!cond(it.next()))
                it.remove();
    }

    This simple piece of code is polymorphic, which means that it works for any Collection regardless of implementation. This example demonstrates how easy it is to write a polymorphic algorithm using the Java Collections Framework. 

Collection Interface Bulk Operations

    Bulk operations perform an operation on an entire Collection. You could implement these shorthand operations using the basic operations, though in most cases such implementations would be less efficient. The following are the bulk operations:

        containsAll — returns true if the target Collection contains all of the elements in the specified Collection.
        addAll — adds all of the elements in the specified Collection to the target Collection.
        removeAll — removes from the target Collection all of its elements that are also contained in the specified Collection.
        retainAll — removes from the target Collection all its elements that are not also contained in the specified Collection. That is, it retains only those elements in the target Collection that are also contained in the specified Collection.
        clear — removes all elements from the Collection. 

    The addAll, removeAll, and retainAll methods all return true if the target Collection was modified in the process of executing the operation.

    As a simple example of the power of bulk operations, consider the following idiom to remove all instances of a specified element, e, from a Collection, c.

    c.removeAll(Collections.singleton(e));

    More specifically, suppose you want to remove all of the null elements from a Collection.

    c.removeAll(Collections.singleton(null));

    This idiom uses Collections.singleton, which is a static factory method that returns an immutable Set containing only the specified element. 

Collection Interface Array Operations

    The toArray methods are provided as a bridge between collections and older APIs that expect arrays on input. The array operations allow the contents of a Collection to be translated into an array. The simple form with no arguments creates a new array of Object. The more complex form allows the caller to provide an array or to choose the runtime type of the output array.

    For example, suppose that c is a Collection. The following snippet dumps the contents of c into a newly allocated array of Object whose length is identical to the number of elements in c.

    Object[] a = c.toArray();

    Suppose that c is known to contain only strings (perhaps because c is of type Collection<String>). The following snippet dumps the contents of c into a newly allocated array of String whose length is identical to the number of elements in c.

    String[] a = c.toArray(new String[0]);

