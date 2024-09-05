package code2.page107;

//exercise 16

public class StringArrayExample {
    public static void main(String[] args) {
        
        String[] myStrings = new String[3]; 

        
        myStrings[0] = "Apple";
        myStrings[1] = "Banana";
        myStrings[2] = "Cherry";

        
        for (int i = 0; i < myStrings.length; i++) {
            System.out.println("Element at index " + i + ": " + myStrings[i]);
        }
    }
}
