package baitapgiuaki1;


interface Signal {
    
    void emitSignal();
}


class DiscreteSignal implements Signal {
    
    public void emitSignal() {
        System.out.println("Discrete Signal Emitted!");
    }
}


class ContinuousSignal implements Signal {
    
    public void emitSignal() {
        System.out.println("Continuous Signal Emitted!");
    }
}

public class Main {
    public static void main(String[] args) {
        
        Signal discreteSignal = new DiscreteSignal();
        
        Signal continuousSignal = new ContinuousSignal();

        
        discreteSignal.emitSignal();
        continuousSignal.emitSignal();
    }
}
