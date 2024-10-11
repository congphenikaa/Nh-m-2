package code2.page 165;

// exercise 10

public class Stem extends Root {
    private Component1 component1;
    private Component2 component2;
    private Component3 component3;

    public Stem() {
        component1 = new Component1();
        component2 = new Component2();
        component3 = new Component3();
        System.out.println("Stem constructor");
    }

    public static void main(String[] args) {
        Stem stem = new Stem();
    }
}
