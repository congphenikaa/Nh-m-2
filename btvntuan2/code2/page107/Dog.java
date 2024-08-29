package code2.page107;

// exercise 5

public class Dog {
    public void bark() {
        System.out.println("Woof! (Default bark)");
    }

    public void bark(int numBarks) {
        for (int i = 0; i < numBarks; i++) {
            System.out.print("Woof! ");
        }
        System.out.println();
    }

    public void bark(double pitch) {
        if (pitch < 0.5) {
            System.out.println("Howl! (Low pitch)");
        } else {
            System.out.println("Bark! (Normal pitch)");
        }
    }

    public void bark(String customSound) {
        System.out.println("Custom sound: " + customSound);
    }

    public static void main(String[] args) {
        Dog myDog = new Dog();


        myDog.bark();                  
        myDog.bark(3);                 
        myDog.bark(0.3);               
        myDog.bark("Ruff ruff!");    
}
}
