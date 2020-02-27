
package implementations;
import java.util.ArrayList;

import interfaces.CpuScheduler;
import objects.OurProcess;

public class RoundRobin extends CpuScheduler {
	private ArrayList<OurProcess> myProcesses = new ArrayList<>();

    public RoundRobin() {
		super();
		this.myProcesses = new ArrayList<>();
	}

    @Override
    public void addProcess(OurProcess toAdd) {
        myProcesses.add(toAdd);
    }

    public OurProcess getNextProcess()
    {
      return myProcesses.get(0);
    }

    public void schedule()
    {
        removeIfFinished();
    	myProcesses.add(myProcesses.remove(0));
    }

    public void removeIfFinished() {
        if(myProcesses.size() > 0 && myProcesses.get(0).getHasLeft() == 0) {
            OurProcess removed = myProcesses.get(0);
            myProcesses.remove(0);
            addToFinished(removed);
        }

    }
}