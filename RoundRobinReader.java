/* RoundRobinReader.java  —  GIVEN TO YOU, COMPLETE.  DO NOT MODIFY.
 *
 * Reads a data file and hands you back two things: the quantum length, and the
 * processes in ARRIVAL ORDER inside a Queue.
 *
 * File format (see data.txt):
 *
 *     4              <- how many processes
 *     3              <- the quantum (length of one time slice)
 *     0,P1,12        <- arrivalTime , name , executionTime
 *     2,P2,9
 *     7,P3,4
 *     11,P4,5
 *
 * ⚠ READ THIS TWICE.  The queue this class gives you is the ARRIVALS queue —
 * processes that have not shown up yet, earliest first.  It is NOT the ready
 * queue.  The ready queue is the one you build in RoundRobinProcess, and it
 * holds only the processes that have already arrived and are waiting for the CPU.
 * Two different queues.  Confusing them is the single most common way to get
 * this lab wrong.
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RoundRobinReader {

    private Queue<Process> processQueue;
    private int quantumLength;

    public RoundRobinReader(String filename) {
        Scanner file = null;
        try {
            file = new Scanner(new File(filename));
            int processNum = Integer.parseInt(file.nextLine().trim());
            quantumLength = Integer.parseInt(file.nextLine().trim());
            processQueue = new Queue<Process>();
            while (file.hasNextLine()) {
                String line = file.nextLine().trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(",");
                processQueue.enqueue(new Process(Integer.parseInt(parts[0].trim()),
                                                parts[1].trim(),
                                                Integer.parseInt(parts[2].trim())));
            }
            file.close();
        }
        catch (FileNotFoundException e) {
            System.err.println("Could not find the data file: " + filename);
            throw new RuntimeException(e);
        }
    }

    public RoundRobinReader() {
        this("data.txt");
    }

    /** The processes, in arrival order. These have NOT arrived yet. */
    public Queue<Process> getProcessQueue() {
        return processQueue;
    }

    /** The length of one time slice. */
    public int getQuantumLength() {
        return quantumLength;
    }
}
