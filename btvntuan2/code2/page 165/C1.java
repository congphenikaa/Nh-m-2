package code2.page 165;

// exercise 7

public class C1 extends A1 {
    private B1 b;

    public C1(String message, int value) {
        super(message); // Gọi constructor của A
        b = new B1(value); // Khởi tạo đối tượng B
    }

    public static void main(String[] args) {
        C1 c = new C1("Hello from C", 42);
    }
}

