public class DiscreteSignal implements Signal {

    private double amplitude;
    private double frequency;


    public DiscreteSignal(double amplitude, double frequency) {
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
        System.out.println("Discrete Signal:");
        System.out.println("Amplitude: " + getAmplitude());
        System.out.println("Frequency: " + getFrequency());
        System.out.println("Period: " + getPeriod());
        System.out.println("Wavelength: " + getWavelength());
    }

    // Phương thức main để chạy chương trình
    public static void main(String[] args) {
        // Tạo đối tượng DiscreteSignal với biên độ 10 và tần số 50
        DiscreteSignal signal = new DiscreteSignal(10, 50);

        // Hiển thị thông tin tín hiệu rời rạc
        signal.displaySignalInfo();
    }
}
