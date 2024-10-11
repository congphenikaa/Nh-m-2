package code2.page 165;

// exercise 4

public class DerivedClass extends BaseClass {
    public DerivedClass() {
        System.out.println("DerivedClass constructor called");
    }

    public static void main(String[] args) {
        DerivedClass dc = new DerivedClass();
    }
}
