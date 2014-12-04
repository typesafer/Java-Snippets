The Queue implementations are grouped into general-purpose and concurrent implementations.
General-Purpose Queue Implementations
As mentioned in the previous section, LinkedList implements the Queue interface, providing first in, first out (FIFO) queue operations for add, poll, and so on.

The PriorityQueue class is a priority queue based on the heap data structure. This queue orders elements according to the order specified at construction time, which can be the elements' natural ordering or the ordering imposed by an explicit Comparator.

The queue retrieval operations — poll, remove, peek, and element — access the element at the head of the queue. The head of the queue is the least element with respect to the specified ordering. If multiple elements are tied for least value, the head is one of those elements; ties are broken arbitrarily.

PriorityQueue and its iterator implement all of the optional methods of the Collection and Iterator interfaces. The iterator provided in method iterator is not guaranteed to traverse the elements of the PriorityQueue in any particular order. For ordered traversal, consider using Arrays.sort(pq.toArray()).
Concurrent Queue Implementations
The java.util.concurrent package contains a set of synchronized Queue interfaces and classes. BlockingQueue extends Queue with operations that wait for the queue to become nonempty when retrieving an element and for space to become available in the queue when storing an element. This interface is implemented by the following classes:

    LinkedBlockingQueue — an optionally bounded FIFO blocking queue backed by linked nodes
    ArrayBlockingQueue — a bounded FIFO blocking queue backed by an array
    PriorityBlockingQueue — an unbounded blocking priority queue backed by a heap
    DelayQueue — a time-based scheduling queue backed by a heap
    SynchronousQueue — a simple rendezvous mechanism that uses the BlockingQueue interface

In JDK 7, TransferQueue is a specialized BlockingQueue in which code that adds an element to the queue has the option of waiting (blocking) for code in another thread to retrieve the element. TransferQueue has a single implementation:

    LinkedTransferQueue — an unbounded TransferQueue based on linked nodes
