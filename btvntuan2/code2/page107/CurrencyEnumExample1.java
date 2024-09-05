package code2.page107;

//example 22

public class CurrencyEnumExample1 {
    public enum PaperCurrency {
        ONE_DOLLAR, 
        TWO_DOLLARS, 
        FIVE_DOLLARS, 
        TEN_DOLLARS, 
        TWENTY_DOLLARS, 
        FIFTY_DOLLARS 
    }

    public static void main(String[] args) {
        System.out.println("Descriptions of paper currency:");

        for (PaperCurrency currency : PaperCurrency.values()) {
            switch (currency) {
                case ONE_DOLLAR:
                    System.out.println("1 USD: A single dollar bill.");
                    break;
                case TWO_DOLLARS:
                    System.out.println("2 USD: Two-dollar bill, less common but still in circulation.");
                    break;
                case FIVE_DOLLARS:
                    System.out.println("5 USD: A five-dollar bill featuring historical figures.");
                    break;
                case TEN_DOLLARS:
                    System.out.println("10 USD: A ten-dollar bill with various designs.");
                    break;
                case TWENTY_DOLLARS:
                    System.out.println("20 USD: A twenty-dollar bill, widely used.");
                    break;
                case FIFTY_DOLLARS:
                    System.out.println("50 USD: A fifty-dollar bill, less frequently seen.");
                    break;
                default:
                    System.out.println("Unknown currency.");
            }
        }
    }
}
