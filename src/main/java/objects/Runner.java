package objects;

import interfaces.CpuScheduler;
import interfaces.ProcessGeneratorInterface;

public class Runner {
    private ProcessGeneratorInterface processGenerator;
    private CpuScheduler cpuScheduler;

    // Running details
    private int currentTime;

    public Runner(ProcessGeneratorInterface processGenerator, CpuScheduler cpuScheduler) {
        this.processGenerator = processGenerator;
        this.cpuScheduler = cpuScheduler;

        currentTime = 0;
    }

    public void addProcess() {
        cpuScheduler.addProcess(
                processGenerator.generateProcess().addToScheduler(currentTime)
        );
    }
    public void step() {
        cpuScheduler.schedule();
        cpuScheduler.tick();
        OurProcess ourobj =  cpuScheduler.getCurrentProcess();
        if(ourobj == null)
            System.out.println("Yikers");
        ourobj.runForOneTick(currentTime++);
    }

    public void printFinsihedProcessStats(){
        cpuScheduler.getFinishedProcesses().stream()
                .forEach(System.out::println);
    }

    public void runUntilDone() {
        while(cpuScheduler.hasMore())
            step();
    }

}
