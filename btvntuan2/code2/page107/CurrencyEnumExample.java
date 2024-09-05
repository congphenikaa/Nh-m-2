package code2.page107;

// example 21

public class CurrencyEnumExample {
    
    public enum PaperCurrency {
        ONE_DOLLAR, 
        TWO_DOLLARS,
        FIVE_DOLLARS, 
        TEN_DOLLARS, 
        TWENTY_DOLLARS, 
        FIFTY_DOLLARS 
    }

    public static void main(String[] args) {
        System.out.println("Least-valuable paper currency:");

    
        for (PaperCurrency currency : PaperCurrency.values()) {
            System.out.println(currency + " (Ordinal: " + currency.ordinal() + ")");
        }
    }
}
