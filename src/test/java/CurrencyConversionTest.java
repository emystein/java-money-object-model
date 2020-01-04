import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrencyConversionTest {
    @Test
    void givenPesosWhenConvertThePesosToDollarsThenItShouldReturnDollars() {
        var ars63 = new Money(63.0, "ARS");

        var rates = new ConversionRates();

        rates.addRate("ARS", "USD", LocalDate.now(), 1 / 63.0);

        assertThat(rates.convert(ars63, "USD", LocalDate.now())).isEqualTo(new Money(1.0, "USD"));
    }

    @Test
    void givenPesosWhenConvertThePesosToDollarsUsingYesterdayRateThenItShouldReturnDollars() {
        var ars62 = new Money(62.0, "ARS");

        var rates = new ConversionRates();

        rates.addRate("ARS", "USD", LocalDate.now().minusDays(1), 1 / 62.0);
        rates.addRate("ARS", "USD", LocalDate.now(), 1 / 63.0);

        var usd = rates.convert(ars62, "USD", LocalDate.now().minusDays(1));

        assertThat(usd).isEqualTo(new Money(1.0, "USD"));
    }

    @Test
    void givenEmptyConversionRatesWhenConvertToCurrencyThenItShouldThrowAnException() {
        var ars62 = new Money(62.0, "ARS");

        var rates = new ConversionRates();

        assertThrows(Exception.class, () -> rates.convert(ars62, "NOTFOUND", LocalDate.now()));
    }

    @Test
    void givenACurrencyWhenConvertToAnUnknownCurrencyThenItShouldThrowAnException() {
        var ars62 = new Money(62.0, "ARS");

        var rates = new ConversionRates();
        rates.addRate("ARS", "USD", LocalDate.now(), 1 / 63.0);

        assertThrows(NoSuchElementException.class, () -> rates.convert(ars62, "NOTFOUND", LocalDate.now()));
    }

    @Test
    void givenTwoConversionRatesForTheSameTargetCurrencyAndDateWhenAddTheConversionRatesThenTheSecondConversionRateShouldOverrideTheFirstOne() {
        var ars63 = new Money(62.0, "ARS");

        var rates = new ConversionRates();

        rates.addRate("ARS", "USD", LocalDate.now(), 1 / 62.0);
        rates.addRate("ARS", "USD", LocalDate.now(), 1 / 63.0);

        assertThat(rates.convert(ars63, "USD", LocalDate.now())).isEqualTo(new Money(1.0, "USD"));
    }
}
