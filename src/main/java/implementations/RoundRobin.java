package implementations;
import java.util.ArrayList;

import interfaces.CPUSchedulerInterface;

public class RoundRobin implements CPUSchedulerInterface {
	ArrayList<Process> myProcesses = new ArrayList<Process>();

    public RoundRobin(ArrayList<Process> myProcesses) {
		super();
		this.myProcesses = myProcesses;
	}

	public void addProcess(Process toAdd) 
    {
    	myProcesses.add(toAdd);
    }

    public Process getNextProcess()
    {
      return myProcesses.get(0);
      
    }

    public void schedule()
    {
    	myProcesses.add(myProcesses.remove(0));
    }

	public Process[] getAllFinishedProcesses() {
		// TODO Auto-generated method stub
		return null;
	}
}
