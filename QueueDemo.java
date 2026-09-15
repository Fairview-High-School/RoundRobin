/* QueueDemo.java  —  run this FIRST, before you touch anything else.
 *
 *     javac QueueDemo.java
 *     java QueueDemo
 *
 * It takes two minutes and it is the entire queue lesson.  Read the code,
 * predict the output line by line, then run it and check yourself.
 */
public class QueueDemo {

    public static void main(String[] args) {

        Queue<String> line = new Queue<String>();

        System.out.println("Empty to start? " + line.isEmpty());

        line.enqueue("Ada");
        line.enqueue("Blaise");
        line.enqueue("Charles");
        System.out.println("The line: " + line);          // front is on the LEFT
        System.out.println("Size:     " + line.size());
        System.out.println("Front:    " + line.peek());   // looks, does not remove
        System.out.println("The line: " + line);          // unchanged

        String served = line.dequeue();                   // removes from the FRONT
        System.out.println("Served:   " + served);
        System.out.println("The line: " + line);

        // This is the round robin move: take from the front, put it back at the back.
        String slice = line.dequeue();
        System.out.println("Ran one quantum of: " + slice);
        line.enqueue(slice);
        System.out.println("The line: " + line);

        while (!line.isEmpty()) {
            System.out.println("Draining: " + line.dequeue());
        }
        System.out.println("Empty now? " + line.isEmpty());

        /* ⚠ One warning.  dequeue() on an empty queue crashes.
         * Always guard it:   if (!q.isEmpty()) { ... q.dequeue() ... }
         */
    }
}
