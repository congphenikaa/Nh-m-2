package code2.page107;

// exercise 10

public class FinalizeDemo {

    public void finalize() {
        System.out.println("Object is being finalized!");
    }

    public static void main(String[] args) {
        
        FinalizeDemo obj = new FinalizeDemo();

        
        obj = null; 

       
        System.gc();


        System.out.println("End of main()");
    }
}

