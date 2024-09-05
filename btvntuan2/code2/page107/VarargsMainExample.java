package code2.page107;

//example 20

public class VarargsMainExample {
    public static void main(String... args) {
        System.out.println("Received command-line arguments:");
        for (String arg : args) {
            System.out.println(arg);
        }
    }
}
