package implementations;

import interfaces.CpuScheduler;
import objects.OurProcess;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;


public class MultilevelFeedbackScheduler extends CpuScheduler {
	
	ArrayList <Process> myProcesses = new ArrayList <Process>();

	private int[] queueQuantums;
	private int total;
	private ArrayList<OurProcess>[] processQueues;
	private OurProcess currentlyRunning;
	
    public MultilevelFeedbackScheduler(int[] queueQuantums) {
		super();
		this.queueQuantums = queueQuantums;
		total = Arrays.stream(queueQuantums).sum();
		// Please work
		processQueues = (ArrayList<OurProcess>[]) Array.newInstance(ArrayList.class, 3);
		processQueues[0] = new ArrayList<>();
		processQueues[1] = new ArrayList<>();
		processQueues[2] = new ArrayList<>();

		currentlyRunning = null;
	}

	@Override
	public void addProcess(OurProcess toAdd) {
    	processQueues[0].add(toAdd);
	}

	public OurProcess getCurrentProcess() {
		return currentlyRunning != null ? currentlyRunning : new OurProcess(-1,10);
	}

	public void schedule() {
		int total = Arrays.stream(queueQuantums).sum();
		int current = time % total;
		if(currentlyRunning != null && currentlyRunning.getHasLeft() == 0){
			processQueues[0].remove(currentlyRunning);
			processQueues[1].remove(currentlyRunning);
			processQueues[2].remove(currentlyRunning);

			addToFinished(currentlyRunning);
		}
		 currentlyRunning = null;
		// High priority queue
		if(current < queueQuantums[0]) {
			currentlyRunning = processQueues[0].size() > 0 ? processQueues[0].get(0) : null;
		} else if(current < queueQuantums[1]) {
			currentlyRunning = processQueues[1].size() > 0 ? processQueues[1].get(0) : null;
		} else if(current < queueQuantums[2]) {
			currentlyRunning = processQueues[2].size() > 0 ? processQueues[2].get(0) : null;
		}
		// Demote processes if they have been running for too long
		if(current == queueQuantums[0]) {
			OurProcess toAdd = processQueues[0].size() > 0 ? processQueues[0].get(0) : null;
			if (toAdd != null)
				processQueues[1].add(toAdd);
		} else if(current == queueQuantums[1]) {
			OurProcess toAdd = processQueues[0].size() > 0 ? processQueues[0].get(0) : null;
			if (toAdd != null)
				processQueues[2].add(toAdd);
		}
	}

	@Override
	public boolean hasMore() {
		return !(processQueues[0].isEmpty() && processQueues[1].isEmpty() && processQueues[2].isEmpty());
	}
}
