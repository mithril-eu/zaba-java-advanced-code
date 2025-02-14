package eu.mithril.invoiceservice.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PriceCalculatorServiceTest {

    private final PriceCalculatorService calculator = new PriceCalculatorService();

    @ParameterizedTest
    @ValueSource(doubles = {-10, -1, 0})
    @DisplayName("Invalid price values should throw exception")
    void applyDiscount_InvalidPrices(double invalidPrice) {
        // when
        Executable executable = () -> calculator.applyDiscount(invalidPrice, 5);

        // then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @Test
    @DisplayName("Negative years should throw exception")
    void applyDiscount_NegativeYear() {
        // when
        Executable executable = () -> calculator.applyDiscount(100.00, -5);

        // then
        assertThrows(IllegalArgumentException.class, executable);
    }

    @ParameterizedTest(name = "#{index} - Loyalty years: {0}, Expected price: {1}")
    @CsvSource({
            "0, 100.0",
            "1, 98.0",
            "5, 90.00",
            "10, 80.00",
            "39, 80.00"
    })
    void applyDiscount(int years, double expectedPrice) {
        // when
        double result = calculator.applyDiscount(100.00, years);

        // then
        assertEquals(expectedPrice, result);
    }

}