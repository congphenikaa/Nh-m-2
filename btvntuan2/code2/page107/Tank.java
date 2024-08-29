package code2.page107;

// exercise 12

public class Tank {
    private boolean isFilled;

    public Tank() {
        this.isFilled = false; 
    }

    public void fill() {
        isFilled = true;
        System.out.println("Tank filled.");
    }

    public void empty() {
        isFilled = false;
        System.out.println("Tank emptied.");
    }

    public void finalize() {
        if (isFilled) {
            System.out.println("WARNING: Tank was not empty during cleanup!");
        } else {
            System.out.println("Tank successfully cleaned up.");
        }
    }

    public static void main(String[] args) {
        Tank myTank = new Tank();

        
        myTank.fill(); 
        myTank.empty();

        
        System.gc();

        
        Tank anotherTank = new Tank();
        anotherTank.fill(); // Fill it

        
        System.gc();
    }
}
