package code2.page 165;

// exercise 8

public class Derived extends Base {

    // Constructor mặc định gọi constructor của lớp cơ sở với giá trị mặc định
    public Derived() {
        super(0); // Gọi constructor của lớp Base với giá trị 0
        System.out.println("Default constructor of Derived");
    }

    // Constructor có tham số gọi constructor của lớp cơ sở với giá trị truyền vào
    public Derived(int i) {
        super(i); // Gọi constructor của lớp Base với giá trị i
        System.out.println("Non-default constructor of Derived");
    }

    public static void main(String[] args) {
        Derived d1 = new Derived();       // Sử dụng constructor mặc định
        Derived d2 = new Derived(10);     // Sử dụng constructor có tham số
    }
}
