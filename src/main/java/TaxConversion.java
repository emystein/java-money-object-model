import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@RequiredArgsConstructor
public class TaxConversion {
    private final ConversionRates conversionRates;

    public Money convert(Collection<PriceTax> priceTaxes, String targetCurrency) {
        var sourceCurrency = priceTaxes.stream().findFirst().get().getPrice().getCurrency();
        var taxSum = new Money(priceTaxes.stream().mapToDouble(PriceTax::getTax).sum(), sourceCurrency);
        return conversionRates.convert(taxSum, targetCurrency, yesterday());
    }

    private LocalDate yesterday() {
        return LocalDate.now().minusDays(1);
    }
}
