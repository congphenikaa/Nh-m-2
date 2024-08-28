package code2.page107;

public class StringReferenceDemo {
    private String myString; 

    public void printString() {
        System.out.println("Value of myString: " + myString);
    }

    public static void main(String[] args) {
        StringReferenceDemo demo = new StringReferenceDemo();
        demo.printString();
    }
}
// exercise 1