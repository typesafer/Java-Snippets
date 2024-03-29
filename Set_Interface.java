A Set is a Collection that cannot contain duplicate elements. It models the mathematical set abstraction. The Set interface contains only methods inherited from Collection and adds the restriction that duplicate elements are prohibited. Set also adds a stronger contract on the behavior of the equals and hashCode operations, allowing Set instances to be compared meaningfully even if their implementation types differ. Two Set instances are equal if they contain the same elements.

The following is the Set interface.

public interface Set<E> extends Collection<E> {
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

    // Array Operations
    Object[] toArray();
    <T> T[] toArray(T[] a);
}

The Java platform contains three general-purpose Set implementations: HashSet, TreeSet, and LinkedHashSet. HashSet, which stores its elements in a hash table, is the best-performing implementation; however it makes no guarantees concerning the order of iteration. TreeSet, which stores its elements in a red-black tree, orders its elements based on their values; it is substantially slower than HashSet. LinkedHashSet, which is implemented as a hash table with a linked list running through it, orders its elements based on the order in which they were inserted into the set (insertion-order). LinkedHashSet spares its clients from the unspecified, generally chaotic ordering provided by HashSet at a cost that is only slightly higher.

Here's a simple but useful Set idiom. Suppose you have a Collection, c, and you want to create another Collection containing the same elements but with all duplicates eliminated. The following one-liner does the trick.

Collection<Type> noDups = new HashSet<Type>(c);

It works by creating a Set (which, by definition, cannot contain a duplicate), initially containing all the elements in c. It uses the standard conversion constructor described in the The Collection Interface section.

Here is a minor variant of this idiom that preserves the order of the original collection while removing duplicate element.

Collection<Type> noDups = new LinkedHashSet<Type>(c);

The following is a generic method that encapsulates the preceding idiom, returning a Set of the same generic type as the one passed.

public static <E> Set<E> removeDups(Collection<E> c) {
    return new LinkedHashSet<E>(c);
}

Set Interface Basic Operations

    The size operation returns the number of elements in the Set (its cardinality). The isEmpty method does exactly what you think it would. The add method adds the specified element to the Set if it's not already present and returns a boolean indicating whether the element was added. Similarly, the remove method removes the specified element from the Set if it's present and returns a boolean indicating whether the element was present. The iterator method returns an Iterator over the Set.

    The following program takes the words in its argument list and prints out any duplicate words, the number of distinct words, and a list of the words with duplicates eliminated.


    import java.util.*;

    public class FindDups {
        public static void main(String[] args) {
            Set<String> s = new HashSet<String>();
            for (String a : args)
                if (!s.add(a))
                    System.out.println("Duplicate detected: " + a);

            System.out.println(s.size() + " distinct words: " + s);
        }
    }

    Now run the program.

    java FindDups i came i saw i left

    The following output is produced.

    Duplicate detected: i
    Duplicate detected: i
    4 distinct words: [i, left, saw, came]

    Note that the code always refers to the Collection by its interface type (Set) rather than by its implementation type (HashSet). This is a strongly recommended programming practice because it gives you the flexibility to change implementations merely by changing the constructor. If either of the variables used to store a collection or the parameters used to pass it around are declared to be of the Collection's implementation type rather than its interface type, all such variables and parameters must be changed in order to change its implementation type.

    Furthermore, there's no guarantee that the resulting program will work. If the program uses any nonstandard operations present in the original implementation type but not in the new one, the program will fail. Referring to collections only by their interface prevents you from using any nonstandard operations.

    The implementation type of the Set in the preceding example is HashSet, which makes no guarantees as to the order of the elements in the Set. If you want the program to print the word list in alphabetical order, merely change the Set's implementation type from HashSet to TreeSet. Making this trivial one-line change causes the command line in the previous example to generate the following output.

    java FindDups i came i saw i left
    Duplicate detected: i
    Duplicate detected: i
    4 distinct words: [came, i, left, saw]

Set Interface Bulk Operations

    Bulk operations are particularly well suited to Sets; when applied, they perform standard set-algebraic operations. Suppose s1 and s2 are sets. Here's what bulk operations do:

        s1.containsAll(s2) — returns true if s2 is a subset of s1. (s2 is a subset of s1 if set s1 contains all of the elements in s2.)
        s1.addAll(s2) — transforms s1 into the union of s1 and s2. (The union of two sets is the set containing all of the elements contained in either set.)
        s1.retainAll(s2) — transforms s1 into the intersection of s1 and s2. (The intersection of two sets is the set containing only the elements common to both sets.)
        s1.removeAll(s2) — transforms s1 into the (asymmetric) set difference of s1 and s2. (For example, the set difference of s1 minus s2 is the set containing all of the elements found in s1 but not in s2.) 

    To calculate the union, intersection, or set difference of two sets nondestructively (without modifying either set), the caller must copy one set before calling the appropriate bulk operation. The following are the resulting idioms.

    Set<Type> union = new HashSet<Type>(s1);
    union.addAll(s2);

    Set<Type> intersection = new HashSet<Type>(s1);
    intersection.retainAll(s2);

    Set<Type> difference = new HashSet<Type>(s1);
    difference.removeAll(s2);

    The implementation type of the result Set in the preceding idioms is HashSet, which is, as already mentioned, the best all-around Set implementation in the Java platform. However, any general-purpose Set implementation could be substituted.

    Let's revisit the FindDups program. Suppose you want to know which words in the argument list occur only once and which occur more than once, but you do not want any duplicates printed out repeatedly. This effect can be achieved by generating two sets — one containing every word in the argument list and the other containing only the duplicates. The words that occur only once are the set difference of these two sets, which we know how to compute. Here's how the resulting program looks.


    import java.util.*;

    public class FindDups2 {
        public static void main(String[] args) {
            Set<String> uniques = new HashSet<String>();
            Set<String> dups    = new HashSet<String>();

            for (String a : args)
                if (!uniques.add(a))
                    dups.add(a);

    	// Destructive set-difference
            uniques.removeAll(dups);

            System.out.println("Unique words:    " + uniques);
            System.out.println("Duplicate words: " + dups);
        }
    }

    When run with the same argument list used earlier (i came i saw i left), the program yields the following output.

    Unique words:    [left, saw, came]
    Duplicate words: [i]

    A less common set-algebraic operation is the symmetric set difference — the set of elements contained in either of two specified sets but not in both. The following code calculates the symmetric set difference of two sets nondestructively.

    Set<Type> symmetricDiff = new HashSet<Type>(s1);
    symmetricDiff.addAll(s2);
    Set<Type> tmp = new HashSet<Type>(s1);
    tmp.retainAll(s2));
    symmetricDiff.removeAll(tmp);

Set Interface Array Operations

    The array operations don't do anything special for Sets beyond what they do for any other Collection. These operations are described in The Collection Interface section. 
