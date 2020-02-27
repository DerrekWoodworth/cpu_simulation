package interfaces;

public interface CPUSchedulerInterface {
    void addProcess();
    Process getNextProcess();
    Process[] getAllFinishedProcesses();
    void schedule();
}
