package code2.page107;

// exercise 9

public class MyClass {
    private int value;

    
    public MyClass() {
        
        this(42); 
    }

    
    public MyClass(int initialValue) {
        this.value = initialValue;
        System.out.println("Initialized with value: " + value);
    }

    public static void main(String[] args) {
       
        MyClass obj1 = new MyClass();

        
        MyClass obj2 = new MyClass(99);
    }
}
