package code2.page107;

//example 19

public class VarargExample {
    public static void printStrings(String... strings) {
        for (String s : strings) {
            System.out.println("Received: " + s);
        }
    }

    public static void main(String[] args) {
        
        printStrings("Apple", "Banana", "Cherry");

    
        String[] fruits = {"Grapes", "Orange", "Pineapple"};
        printStrings(fruits);
    }
}
