// exercise 6
public class Main1 {
    public static void main(String[] args) {
        ProtectedDataHolder dataHolder = new ProtectedDataHolder(42);
        DataManipulator manipulator = new DataManipulator();

        System.out.println("Initial protected value: " + dataHolder.protectedValue);

 
        manipulator.incrementProtectedValue(dataHolder);

        System.out.println("Modified protected value: " + dataHolder.protectedValue);
    }
}
