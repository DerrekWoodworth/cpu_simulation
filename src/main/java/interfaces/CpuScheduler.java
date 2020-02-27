package interfaces;

import objects.OurProcess;

import java.util.ArrayList;
import java.util.List;

public abstract class CpuScheduler {
    private ArrayList<OurProcess> finished;
    public CpuScheduler() {
        finished = new ArrayList<OurProcess>();
    }
    public abstract void addProcess(OurProcess toAdd);
    public abstract OurProcess getNextProcess();
    public abstract void schedule();
    protected void addToFinished(OurProcess process) {
        finished.add(process);
    }
    public ArrayList<OurProcess> getFinishedProcesses(){
        return finished;
    }
}
