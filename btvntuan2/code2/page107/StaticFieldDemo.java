package code2.page107;

// exercise 13, 14

public class StaticFieldDemo {
    
    private static String staticField1 = "Initialized at definition";

    static {
        staticField1 += " (modified in static block)";
    }

    public static void printFields() {
        System.out.println("Static Field 1: " + staticField1);
    }

    public static void main(String[] args) {
        printFields();
    }
}
