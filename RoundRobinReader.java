import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class RoundRobinReader {
	
	private Queue<Process> processQueue;
	private int quantumLength; 
	
	
	public RoundRobinReader(String filename)
	{
		Scanner file = new Scanner(new File(filename));
		
		/*Read the number of processes included in the file, the quantum length to be used, and
		 * the information about each process, from the data file.
		 * The information about each data file is read into a Queue of Process objects
		 */
        int processNum = Integer.parseInt(file.nextLine());
        quantumLength = Integer.parseInt(file.nextLine());
        processQueue = new LinkedList<>();
        while(file.hasNextLine()) {
            String[] line = file.nextLine().split(",");
            processQueue.add(new Process(Integer.parseInt(line[0]),line[1],Integer.parseInt(line[2])));
        }
        file.close();
	}
	
	public RoundRobinReader()
	{
		this("data.txt");
	}
	
	public Queue<Process> getProcessQueue() {
		return processQueue;
	}
	
	public int getQuantumLength() {
		return quantumLength;
	}
	
    

}