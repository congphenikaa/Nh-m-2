package code2.page145;

// exercise 4

public class AnotherClass extends MyClass {
    public void someMethod() {
        
        myProtectedMethod();
    }

    protected void myProtectedMethod() {
        System.out.println("This is a protected method.");
    }
}
