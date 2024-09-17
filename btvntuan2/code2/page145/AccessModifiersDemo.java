public class AccessModifiersDemo {

    
    public int publicField = 42;

    
    private String privateField = "Hello, private!";

    
    protected double protectedField = 3.14;

    
    int packageField = 123;

    
    public void publicMethod() {
        System.out.println("This is a public method.");
    }

    
    private void privateMethod() {
        System.out.println("This is a private method.");
    }

    
    protected void protectedMethod() {
        System.out.println("This is a protected method.");
    }

    
    void packageMethod() {
        System.out.println("This is a package-access method.");
    }

    public static void main(String[] args) {
        
        AccessModifiersDemo demo = new AccessModifiersDemo();

        
        System.out.println("Public field value: " + demo.publicField);
        System.out.println("Private field value: " + demo.privateField);
        System.out.println("Protected field value: " + demo.protectedField);
        System.out.println("Package field value: " + demo.packageField);

        demo.publicMethod();
        demo.privateMethod();
        demo.protectedMethod();
        demo.packageMethod();
    }
}

// exercise 5