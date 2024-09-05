package code2.page107;

//example 17

public class StringConstructorExample {
    private String message;

    
    public StringConstructorExample(String inputMessage) {
        message = inputMessage;
        System.out.println("Constructor called with message: " + message);
    }

    public static void main(String[] args) {
        
        StringConstructorExample[] objectReferences = new StringConstructorExample[3];

        
        if (objectReferences[0] != null) {
            System.out.println("Reference at index 0 exists.");
        } else {
            System.out.println("Reference at index 0 is null.");
        }
    }
}
