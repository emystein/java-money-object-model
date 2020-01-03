import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class CurrencyConversionTest {
    @Test
    void givenPesosWhenConvertThePesosToDollarsThenItShouldReturnDollars() {
        Money ars63 = new Money(63.0, "ARS");

        var rates = new ConversionRates();

        rates.addRate(LocalDate.now(), "ARS",  1 / 63.0, "USD");

        assertThat(rates.convert(ars63, "USD", LocalDate.now()).getAmount()).isEqualTo(1.0);
    }

    @Test
    void givenPesosWhenConvertThePesosToDollarsUsingYesterdayRateThenItShouldReturnDollars() {
        Money ars62 = new Money(62.0, "ARS");

        var rates = new ConversionRates();

        rates.addRate(LocalDate.now().minusDays(1), "ARS",  1 / 62.0, "USD");
        rates.addRate(LocalDate.now(), "ARS",  1 / 63.0, "USD");

        assertThat(rates.convert(ars62, "USD", LocalDate.now().minusDays(1)).getAmount()).isEqualTo(1.0);
    }
}
