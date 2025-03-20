import java.util.ArrayList;
public class RoundRobinProcess {
    private Queue<Process> processQueue; //processes that have not arrived yet
    private int quantumLength;


    public RoundRobinProcess(Queue<Process> processQueue, int quantumLength) {
        this.processQueue = processQueue;
        this.quantumLength = quantumLength;
    }

	/* Now, write the code to process the Queue of Processes using the Round Robin algorithm.
	 * Display each time segment, and the process that is executing at that time segment.
	 * Indicate when each process is complete.
	 * Display the total turn around time, and total wait time for each process.
	 * Also display the average wait time.  (the average of the wait time of all processes)
	 
	 * I would recommend creating a RoundRobinProcess constructor, and calling any necessary
	 * methods on it to create the appropriate result.
	 */
	public static void main(String[] args) {
        String filename = "data.txt";
        RoundRobinReader rr = new RoundRobinReader(filename);

		Queue<Process> processQueue = rr.getProcessQueue();
        System.out.println("Process Queue: " + processQueue);
		int quantumLength = rr.getQuantumLength();
        System.out.println("Quantum Length: " + quantumLength);

		RoundRobinProcess rp = new RoundRobinProcess(processQueue, quantumLength);
        //rp.scheduleTimeUnits();  // modify to use whatever methods / method names you write
        //rp.displayStats(); // modify to use whatever methods / method names you write
    }

}