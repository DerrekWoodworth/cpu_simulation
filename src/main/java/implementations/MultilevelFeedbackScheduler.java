package implementations;

import java.util.ArrayList;

import interfaces.CPUSchedulerInterface;

public class MultilevelFeedbackScheduler implements CPUSchedulerInterface {
	
	ArrayList <Process> myProcesses = new ArrayList <Process>();
	
    public MultilevelFeedbackScheduler(ArrayList<Process> myProcesses) {
		super();
		this.myProcesses = myProcesses;
	}

	public void addProcess(Process toAdd) {
    	myProcesses.add(toAdd);
    }

    public Process getNextProcessFirst() {
    	return myProcesses.get(0);
    }
    
    public Process getNextProcessSecond() {
    	return myProcesses.get(0);
    }
    
    public Process getNextProcessBase() {
    	return myProcesses.get(0);
    }

    public Process[] getAllFinishedProcesses() {
    	return new Process[0];
    }

    public void firstQueue() {
    	myProcesses.add(myProcesses.remove(0));
    }
    
    public void secondQueue() {
    	myProcesses.add(myProcesses.remove(0));
    }
    
    public void baseQueue() {
    	myProcesses.add(myProcesses.remove(0));
    }

	public Process getNextProcess() {
		// TODO Auto-generated method stub
		return null;
	}

	public void schedule() {
		// TODO Auto-generated method stub
		
	}
}
