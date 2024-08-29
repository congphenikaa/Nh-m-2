package code2.page107;

// excersise 4

public class Greeting {
    private String message;

    
    public Greeting() {
        this.message = "Hello, world!";
    }

    
    public Greeting(String customMessage) {
        this.message = customMessage;
    }

    
    public void printMessage() {
        System.out.println(message);
    }

    public static void main(String[] args) {
        Greeting defaultGreeting = new Greeting();
        Greeting customGreeting = new Greeting("Hey there!");

        defaultGreeting.printMessage(); 
        customGreeting.printMessage();  
    }
}

