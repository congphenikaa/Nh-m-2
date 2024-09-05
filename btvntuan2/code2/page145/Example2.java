package code2.page145;

// example 2

public class MyClass {
    public void sayHello() {
        System.out.println("Hello from the custom package!");
    }
}

// File: MainApp.java
public class MainApp {
    public static void main(String[] args) {
        // Create instances of both MyClass versions
        MyClass defaultPackageInstance = new MyClass();
        com.example.myapp.MyClass customPackageInstance = new com.example.myapp.MyClass();

        // Call the sayHello() method on both instances
        defaultPackageInstance.sayHello();
        customPackageInstance.sayHello();
    }
}
