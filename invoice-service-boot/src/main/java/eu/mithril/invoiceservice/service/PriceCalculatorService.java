package eu.mithril.invoiceservice.service;

public class PriceCalculatorService {

    /**
     * @return price after loyalty discount applied
     */
    public double applyDiscount(double price, int loyaltyYears) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be positive");
        }
        if (loyaltyYears < 0) {
            throw new IllegalArgumentException("Years cannot be negative");
        }
        double discount = Math.min(loyaltyYears * 0.02, 0.20);
        return price * (1 - discount);
    }

}
