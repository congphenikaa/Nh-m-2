package code2.page107;

// exercise 11

public class AlwaysFinalizeDemo {

    public void finalize() {
        System.out.println("Object is being finalized!");
    }

    public static void main(String[] args) {
        AlwaysFinalizeDemo obj1 = new AlwaysFinalizeDemo();
        AlwaysFinalizeDemo obj2 = new AlwaysFinalizeDemo();

        
        obj1.finalize();
        obj2.finalize();

        
        obj1 = null;
        obj2 = null;

        
        System.gc();

        System.out.println("End of main()");
    }
}


