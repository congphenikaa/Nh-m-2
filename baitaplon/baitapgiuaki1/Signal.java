package baitapgiuaki1;
// Giao diện Signal
public interface Signal {
    // Phương thức để phát tín hiệu (emit)
    void emitSignal();
    
    // Phương thức để nhập dữ liệu tín hiệu
    void inputSignalData(double amplitude, double frequency, double period, double wavelength);

    // Phương thức để xuất dữ liệu tín hiệu
    void displaySignalData();
    
    // Phương thức tính toán biên độ tại một thời điểm cụ thể
    double calculateAmplitude(int time);
    
    // Phương thức để mô phỏng sự tương tác với một vật thể
    void interactWithObject(String object);
}
