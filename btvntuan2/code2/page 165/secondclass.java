package code2.page 165;

// exercise 1

public class SecondClass {
    private SimpleClass simpleClass;

    public void initialize() {
        if (simpleClass == null) {
            simpleClass = new SimpleClass();
        }
        simpleClass.display();
    }

    public static void main(String[] args) {
        SecondClass secondClass = new SecondClass();
        secondClass.initialize();  // This will initialize SimpleClass and call display
    }
}
