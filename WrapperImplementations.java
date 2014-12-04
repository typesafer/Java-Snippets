Wrapper implementations delegate all their real work to a specified collection but add extra functionality on top of what this collection offers. For design pattern fans, this is an example of the decorator pattern. Although it may seem a bit exotic, it's really pretty straightforward.

These implementations are anonymous; rather than providing a public class, the library provides a static factory method. All these implementations are found in the Collections class, which consists solely of static methods.
Synchronization Wrappers

    The synchronization wrappers add automatic synchronization (thread-safety) to an arbitrary collection. Each of the six core collection interfaces — Collection, Set, List, Map, SortedSet, and SortedMap — has one static factory method.

    public static <T> Collection<T>
        synchronizedCollection(Collection<T> c);
    public static <T> Set<T>
        synchronizedSet(Set<T> s);
    public static <T> List<T>
        synchronizedList(List<T> list);
    public static <K,V> Map<K,V>
        synchronizedMap(Map<K,V> m);
    public static <T> SortedSet<T>
        synchronizedSortedSet(SortedSet<T> s);
    public static <K,V> SortedMap<K,V>
        synchronizedSortedMap(SortedMap<K,V> m);

    Each of these methods returns a synchronized (thread-safe) Collection backed up by the specified collection. To guarantee serial access, all access to the backing collection must be accomplished through the returned collection. The easy way to guarantee this is not to keep a reference to the backing collection. Create the synchronized collection with the following trick.

    List<Type> list =
        Collections.synchronizedList(new ArrayList<Type>());

    A collection created in this fashion is every bit as thread-safe as a normally synchronized collection, such as a Vector.

    In the face of concurrent access, it is imperative that the user manually synchronize on the returned collection when iterating over it. The reason is that iteration is accomplished via multiple calls into the collection, which must be composed into a single atomic operation. The following is the idiom to iterate over a wrapper-synchronized collection.

    Collection<Type> c =
        Collections.synchronizedCollection(myCollection);
    synchronized(c) {
        for (Type e : c)
            foo(e);
    }

    If an explicit iterator is used, the iterator method must be called from within the synchronized block. Failure to follow this advice may result in nondeterministic behavior. The idiom for iterating over a Collection view of a synchronized Map is similar. It is imperative that the user synchronize on the synchronized Map when iterating over any of its Collection views rather than synchronizing on the Collection view itself, as shown in the following example.

    Map<KeyType, ValType> m =
        Collections.synchronizedMap(new HashMap<KeyType, ValType>());
        ...
    Set<KeyType> s = m.keySet();
        ...
    synchronized(m) {  // Synchronizing on m, not s!
        while (KeyType k : s)
            foo(k);
    }

    One minor downside of using wrapper implementations is that you do not have the ability to execute any noninterface operations of a wrapped implementation. So, for instance, in the preceding List example, you cannot call ArrayList's ensureCapacity operation on the wrapped ArrayList. 

Unmodifiable Wrappers

    Unlike synchronization wrappers, which add functionality to the wrapped collection, the unmodifiable wrappers take functionality away. In particular, they take away the ability to modify the collection by intercepting all the operations that would modify the collection and throwing an UnsupportedOperationException. Unmodifiable wrappers have two main uses, as follows:

        To make a collection immutable once it has been built. In this case, it's good practice not to maintain a reference to the backing collection. This absolutely guarantees immutability.
        To allow certain clients read-only access to your data structures. You keep a reference to the backing collection but hand out a reference to the wrapper. In this way, clients can look but not modify, while you maintain full access. 

    Like synchronization wrappers, each of the six core Collection interfaces has one static factory method.

    public static <T> Collection<T>
        unmodifiableCollection(Collection<? extends T> c);
    public static <T> Set<T>
        unmodifiableSet(Set<? extends T> s);
    public static <T> List<T>
        unmodifiableList(List<? extends T> list);
    public static <K,V> Map<K, V>
        unmodifiableMap(Map<? extends K, ? extends V> m);
    public static <T> SortedSet<T>
        unmodifiableSortedSet(SortedSet<? extends T> s);
    public static <K,V> SortedMap<K, V>
        unmodifiableSortedMap(SortedMap<K, ? extends V> m);

Checked Interface Wrappers

    The Collections.checked interface wrappers are provided for use with generic collections. These implementations return a dynamically type-safe view of the specified collection, which throws a ClassCastException if a client attempts to add an element of the wrong type. The generics mechanism in the language provides compile-time (static) type-checking, but it is possible to defeat this mechanism. Dynamically type-safe views eliminate this possibility entirely. 
