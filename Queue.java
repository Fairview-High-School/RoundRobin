/* Queue.java  —  GIVEN TO YOU, COMPLETE.  DO NOT MODIFY.
 *
 * A Queue is an abstract data type (ADT).  "Abstract" means you are told WHAT it
 * does, not HOW it does it.  You will build one yourself later in the year; for
 * this lab you only have to use one.
 *
 * A queue is a line.  Items join at the back and leave from the front.
 * First in, first out (FIFO).  That is the whole idea.
 *
 *     Queue<String> line = new Queue<String>();
 *     line.enqueue("A");      // line is now:  A
 *     line.enqueue("B");      // line is now:  A B
 *     String next = line.dequeue();   // next is "A", line is now:  B
 *
 * The five operations you need:
 *
 *     enqueue(item)   join the back of the line
 *     dequeue()       remove and return the item at the front
 *     peek()          look at the front item WITHOUT removing it
 *     isEmpty()       true if there is nobody in line
 *     size()          how many items are in line
 *
 * WHY A QUEUE, FOR THIS LAB:  an operating system keeps processes that are
 * ready to run in a "ready queue".  Round robin takes the process at the FRONT,
 * runs it for one quantum, and — if it is not finished — puts it at the BACK.
 * The queue is not a detail of the program.  The queue IS round robin.
 *
 * The <E> means the queue can hold any one type.  Queue<Process> holds Processes,
 * exactly the way ArrayList<String> holds Strings.
 */
import java.util.LinkedList;

public class Queue<E> {

    private LinkedList<E> list;   // the items currently in the queue

    public Queue() {
        list = new LinkedList<E>();
    }

    /** Add an item to the BACK of the queue. */
    public void enqueue(E item) {
        list.addLast(item);
    }

    /** Remove and return the item at the FRONT of the queue. */
    public E dequeue() {
        return list.removeFirst();
    }

    /** Return the item at the front WITHOUT removing it (null if empty). */
    public E peek() {
        return list.peekFirst();
    }

    /** Same as peek(). Some textbooks call it front(). */
    public E front() {
        return list.peekFirst();
    }

    /** How many items are in the queue. */
    public int size() {
        return list.size();
    }

    /** True when there is nothing in the queue. */
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public String toString() {
        return list.toString();
    }
}
