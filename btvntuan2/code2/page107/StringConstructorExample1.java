package code2.page107;

//example 18

public class StringConstructorExample1 {
    private String message;

    
    public StringConstructorExample1(String inputMessage) {
        message = inputMessage;
        System.out.println("Constructor called with message: " + message);
    }

    public static void main(String[] args) {
    
        StringConstructorExample1[] objectReferences = new StringConstructorExample1[3];

        
        objectReferences[0] = new StringConstructorExample1("Object 1");
        objectReferences[1] = new StringConstructorExample1("Object 2");
        objectReferences[2] = new StringConstructorExample1("Object 3");

        
        for (int i = 0; i < objectReferences.length; i++) {
            System.out.println("Reference at index " + i + ": " + objectReferences[i].message);
        }
    }
}
