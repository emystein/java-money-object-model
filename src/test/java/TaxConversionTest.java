import org.assertj.core.data.Percentage;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class TaxConversionTest {
    @Test
    void convertTaxes() {
        var priceTax1 = new PriceTax(new Money(100.0, "ARS"), 21.0);
        var priceTax2 = new PriceTax(new Money(200.0, "ARS"), 42.0);
        var priceTaxes = Lists.newArrayList(priceTax1, priceTax2);

        var conversionRates = new ConversionRates();
        conversionRates.addRate("ARS", "USD", LocalDate.now().minusDays(1), 1 / 62.0);

        var taxConversion = new TaxConversion(conversionRates);

        assertThat(taxConversion.convert(priceTaxes, "USD").getAmount()).isCloseTo(1.0161, Percentage.withPercentage(99));
    }
}
