package code2.page107;

//exercise 15

public class StringInitializerExample {
    private String myString; 

    
    {
        myString = "Hello, World!"; 
    }

    
    public StringInitializerExample() {
        
    }

    
    public String getMyString() {
        return myString;
    }

    public static void main(String[] args) {
        StringInitializerExample example = new StringInitializerExample();
        System.out.println("Initialized string: " + example.getMyString());
    }
}
