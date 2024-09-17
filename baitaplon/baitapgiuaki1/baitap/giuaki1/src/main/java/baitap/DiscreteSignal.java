package baitapgiuaki1;



public class DiscreteSignal implements Signal {
    
    private double amplitude;    
    private double frequency;    
    private double period;       
    private double wavelength;   

    
    public DiscreteSignal(double amplitude, double frequency, double period, double wavelength) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.period = period;
        this.wavelength = wavelength;
    }

    
    public void emitSignal() {
        System.out.println("Discrete Signal Emitted!");
    }

    
    public void inputSignalData(double amplitude, double frequency, double period, double wavelength) {
        this.amplitude = amplitude;
        this.frequency = frequency;
        this.period = period;
        this.wavelength = wavelength;
    }

    
    public void displaySignalData() {
        System.out.println("Discrete Signal Data:");
        System.out.println("Amplitude: " + amplitude);
        System.out.println("Frequency: " + frequency);
        System.out.println("Period: " + period);
        System.out.println("Wavelength: " + wavelength);
    }

    
    public double calculateAmplitude(int time) {
        
        return amplitude * Math.cos(2 * Math.PI * frequency * time);
    }

    

    public void interactWithObject(String object) {
        System.out.println("Discrete Signal interacted with object: " + object);
        
    }
    
    
    public double getAmplitude() {
        return amplitude;
    }

    public void setAmplitude(double amplitude) {
        this.amplitude = amplitude;
    }

    public double getFrequency() {
        return frequency;
    }

    public void setFrequency(double frequency) {
        this.frequency = frequency;
    }

    public double getPeriod() {
        return period;
    }

    public void setPeriod(double period) {
        this.period = period;
    }

    public double getWavelength() {
        return wavelength;
    }

    public void setWavelength(double wavelength) {
        this.wavelength = wavelength;
    }
}