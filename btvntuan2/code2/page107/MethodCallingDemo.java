package code2.page107;

// exercise 8

public class MethodCallingDemo {

    public void methodA() {
        System.out.println("Inside methodA");
        methodB(); 
        this.methodB(); 
    }

    public void methodB() {
        System.out.println("Inside methodB");
    }

    public static void main(String[] args) {
        MethodCallingDemo demo = new MethodCallingDemo();
        demo.methodA();
    }
}
