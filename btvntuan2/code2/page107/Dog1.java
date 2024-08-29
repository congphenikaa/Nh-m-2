package code2.page107;

// exercise 6

public class Dog1 {
    // Overloaded bark method for different data types
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

    
    public void bark(String customSound, int numTimes) {
        for (int i = 0; i < numTimes; i++) {
            System.out.print(customSound + " ");
        }
        System.out.println();
    }

    public void bark(int numTimes, String customSound) {
        bark(customSound, numTimes); 
    }

    public static void main(String[] args) {
        Dog1 myDog = new Dog1();

        
        myDog.bark();                  
        myDog.bark(3);                 
        myDog.bark(0.3);              
        myDog.bark("Ruff ruff!");     
        myDog.bark("Yip yip!", 2);    
        myDog.bark(2, "Bow wow!");    
    }
}

