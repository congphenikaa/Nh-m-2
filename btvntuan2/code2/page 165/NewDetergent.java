package code2.page 165;

// exercise 2, 3

public class NewDetergent extends Detergent {
    @Override
    public void scrub() {
        System.out.println("NewDetergent scrub()");
    }

    public void sterilize() {
        System.out.println("NewDetergent sterilize()");
    }

    public static void main(String[] args) {
        NewDetergent nd = new NewDetergent();
        nd.scrub();        // Gọi phương thức scrub() đã được ghi đè
        nd.sterilize();    // Gọi phương thức sterilize() mới
    }
}
