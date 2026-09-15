/* Process.java  —  MOSTLY GIVEN.  Two methods at the bottom are yours.
 *
 * One process in the simulation.  It knows when it arrived, what it is called,
 * how much CPU time it needs in total, and how much of that is still left.
 */
public class Process {

    // ---------- given ----------

    private final int arrivalTime;      // the time unit at which it appears
    private final String name;          // P1, P2, ...
    private final int executionTime;    // total CPU time it needs (its "burst")

    private int remainingTime;          // CPU time it still needs; starts equal to executionTime
    private int completionTime;         // the time at which it finished; -1 until then

    public Process(int arrivalTime, String name, int executionTime) {
        this.arrivalTime = arrivalTime;
        this.name = name;
        this.executionTime = executionTime;
        this.remainingTime = executionTime;
        this.completionTime = -1;
    }

    public int getArrivalTime()    { return arrivalTime; }
    public String getName()        { return name; }
    public int getExecutionTime()  { return executionTime; }
    public int getRemainingTime()  { return remainingTime; }

    /** Give this process one time unit of CPU. */
    public void runOneUnit() {
        remainingTime--;
    }

    /** True once it has had all the CPU time it asked for. */
    public boolean isFinished() {
        return remainingTime <= 0;
    }

    public int getCompletionTime()               { return completionTime; }
    public void setCompletionTime(int t)         { completionTime = t; }

    @Override
    public String toString() {
        return name + "(arrives " + arrivalTime + ", needs " + executionTime + ")";
    }

    // ---------- YOURS ----------
    /* ⚠ These two are the operating-system definitions, not arithmetic busywork.
     * Get them right on paper first, using the class trace, then write them.
     *
     * TURNAROUND TIME  —  how long the process was in the system, start to finish:
     *                     from the moment it ARRIVED to the moment it COMPLETED.
     *
     * WAIT TIME        —  how much of that turnaround it spent NOT running.
     *                     A process that needs 5 units and is in the system for 16
     *                     spent 11 of them waiting.
     *
     * Both assume the process has finished.  Call them after the simulation.
     */

    public int getTurnaroundTime() {
        // TODO: replace this
        return 0;
    }

    public int getWaitTime() {
        // TODO: replace this
        return 0;
    }
}
