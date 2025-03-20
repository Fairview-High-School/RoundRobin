import java.util.LinkedList;
public class Queue<E> {
    java.util.LinkedList<E> list; //list of items in queue

    public Queue() {
        list = new LinkedList<E>();
    }
    public void enqueue(E item) {
        list.addLast(item);
    }
    public E dequeue() {
        return list.removeFirst();
    }
    public int size() {
        return list.size();
    }
    public boolean isEmpty() {
        return list.isEmpty();
    }
    public E peek() {
        return list.peekFirst();
    }
    public E peekFront() {
        return list.peekFirst();
    }
    public E front() {
        return list.peekFirst();
    }
    public String toString(){
        return list.toString();
    }
}
