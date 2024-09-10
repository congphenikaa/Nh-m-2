public class contiuousSignal implements Signal {

    private double amplitude;
    private double frequency;


    public contiuousSignal(double amplitude, double frequency) {
        this.amplitude = amplitude;
        this.frequency = frequency;
    }


    @Override
    public double getAmplitude() {
        return amplitude;
    }


    @Override
    public double getFrequency() {
        return frequency;
    }


    @Override
    public double getPeriod() {
        return 1 / frequency;
    }


    @Override
    public double getWavelength() {
        return 300 / frequency; 
    }


    @Override
    public void displaySignalInfo() {
        System.out.println("Continuous Signal:");
        System.out.println("Amplitude: " + getAmplitude());
        System.out.println("Frequency: " + getFrequency());
        System.out.println("Period: " + getPeriod());
        System.out.println("Wavelength: " + getWavelength());
    }

    public static void main(String[] args) { 
        contiuousSignal signal = new contiuousSignal(5.0,50.0);
        signal.displaySignalInfo();
    }
}
