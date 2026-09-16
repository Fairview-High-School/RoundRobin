/* RoundRobinProcess.java  —  THIS IS THE FILE YOU WRITE.
 *
 * It holds the scheduler: the thing that decides, for every time unit, which
 * process gets the CPU.
 *
 * Read README.md first.  Then run QueueDemo.  Then come back here.
 */
import java.util.ArrayList;

public class RoundRobinProcess {

    // ---------- given ----------

    private Queue<Process> arrivals;      // processes that have NOT arrived yet, earliest first
    private Queue<Process> ready;         // processes that HAVE arrived and are waiting for the CPU
    private int quantumLength;            // length of one time slice
    private ArrayList<Process> finished;  // completed processes, in the order they completed
    private int totalProcesses;           // how many there are altogether

    public RoundRobinProcess(Queue<Process> processQueue, int quantumLength) {
        this.arrivals = processQueue;
        this.quantumLength = quantumLength;
        this.ready = new Queue<Process>();
        this.finished = new ArrayList<Process>();
        this.totalProcesses = processQueue.size();
    }

    /* GIVEN — a worked example of using the Queue class, and the one piece of
     * bookkeeping that is fiddly rather than interesting.
     *
     * Moves every process whose arrival time has come out of the arrivals queue
     * and onto the back of the ready queue.  Look at how peek() is used to check
     * the front WITHOUT removing it, so nothing gets pulled out too early.
     */
    private void admitArrivals(int t) {
        while (!arrivals.isEmpty() && arrivals.peek().getArrivalTime() <= t) {
            ready.enqueue(arrivals.dequeue());
        }
    }

    // ---------- YOURS ----------

    /* Run the simulation, printing one line per time unit:
     *
     *      0: P1
     *      1: P1
     *      ...
     *     23: P2 (Complete)
     *
     * THE ALGORITHM, in words.  Keep a clock starting at 0, a variable for the
     * process currently on the CPU (null when the CPU is empty), and a count of
     * how many time units the current process has used of its quantum.
     *
     * Loop until every process has finished:
     *
     *   1.  admitArrivals(clock)  — anybody who has shown up joins the ready queue.
     *
     *   2.  If the CPU is empty and the ready queue is not, DEQUEUE the front
     *       process onto the CPU and reset the quantum counter to 0.
     *
     *   3.  If the CPU is still empty — nothing has arrived yet — print
     *       "<clock>: IDLE", add one to the clock, and go round again.
     *
     *   4.  Give the running process one time unit (runOneUnit()), add one to the
     *       quantum counter, print the line, then add one to the clock.
     *       ⚠ Print "(Complete)" on the line if that unit finished the process.
     *
     *   5.  Then decide what happens to the process that just ran:
     *         - finished?  record its completion time (that is the clock, AFTER
     *           you added one), add it to finished, and clear the CPU.
     *         - used its whole quantum but not finished?  admitArrivals(clock)
     *           FIRST, then ENQUEUE it at the back of the ready queue and clear
     *           the CPU.  ⚠ That order is the tie-break rule in the README:
     *           somebody arriving at that instant gets in line ahead of the
     *           process that was just pre-empted.
     *         - neither?  leave it on the CPU; it keeps running next time unit.
     *
     * ⚠ The step students get wrong is the requeue in 5.  If your average wait
     * comes out too low, you almost certainly let a process keep the CPU past
     * its quantum.
     */
    public void scheduleTimeUnits() {
        // TODO: write this
    }

    /* Print the statistics, in exactly this shape and in the order the
     * processes COMPLETED (that is the order they are in `finished`):
     *
     *     TOTAL TIME and WAIT TIME for each process
     *     P2 turn around time: 22 wait time: 13
     *     P3 turn around time: 18 wait time: 14
     *     P1 turn around time: 28 wait time: 16
     *     P4 turn around time: 19 wait time: 14
     *     Average wait time: 14.25
     *
     * The turnaround and wait figures come from the two methods you wrote in
     * Process.java.  For the average, print two decimal places:
     *     System.out.println(String.format("Average wait time: %.2f", average));
     * ⚠ Watch the integer division trap — (double) totalWait / count.
     */
    public void displayStats() {
        // TODO: write this
    }

    // ---------- given ----------

    public static void main(String[] args) {
        // Run a different file with:  java RoundRobinProcess day18.txt
        String filename = (args.length > 0) ? args[0] : "data.txt";
        RoundRobinReader rr = new RoundRobinReader(filename);

        Queue<Process> processQueue = rr.getProcessQueue();
        int quantumLength = rr.getQuantumLength();

        RoundRobinProcess rp = new RoundRobinProcess(processQueue, quantumLength);
        rp.scheduleTimeUnits();
        rp.displayStats();
    }
}
