package code2.page 165;

// exercise 11

public class Detergent1 {
    // Thay vì kế thừa từ Cleanser, chúng ta tạo đối tượng của Cleanser
    private Cleanser cleanser = new Cleanser();

    // Sử dụng delegation để gọi các phương thức của Cleanser
    public void dilute() {
        cleanser.dilute();
    }

    public void apply() {
        cleanser.apply();
    }

    public void scrub() {
        cleanser.scrub();
        cleanser.append(" Detergent.scrub()");  // Ghi đè
    }

    public void foam() {
        cleanser.append(" foam()");
    }

    public String toString() {
        return cleanser.toString();
    }

    public static void main(String[] args) {
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        System.out.println(x);
    }
}
