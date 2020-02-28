package objects;

import interfaces.ProcessGeneratorInterface;

import java.util.Random;

public class ProcessGenerator implements ProcessGeneratorInterface {
    private int lastId;
    private Random random;
    public ProcessGenerator(){
        lastId = 0;
        random = new Random();
    }
    @Override
    public OurProcess generateProcess() {
        // Randomly generate burst time
        int burstTime = random.nextInt(10) + 1;
        return new OurProcess(++lastId, burstTime);

    }
}
