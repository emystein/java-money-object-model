import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
public class TaxConversion {
    private final ConversionRates conversionRates;

    public Money convert(Collection<PriceTax> priceTaxes, String targetCurrency) {
        var taxSum = priceTaxes.stream().reduce(PriceTax::merge).get();
        return conversionRates.convert(taxSum.getTax(), targetCurrency, yesterday());
    }

    private LocalDate yesterday() {
        return LocalDate.now().minusDays(1);
    }
}
