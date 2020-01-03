import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class ConversionRate {
    private final String sourceCurrency;
    private final String targetCurrency;
    private final LocalDate conversionDate;
    @EqualsAndHashCode.Exclude
    private final Double rate;

    public boolean canConvert(String sourceCurrency, String targetCurrency, LocalDate conversionDate) {
        return this.sourceCurrency.equals(sourceCurrency) &&
                this.targetCurrency.equals(targetCurrency) &&
                this.conversionDate.equals(conversionDate);
    }

    public Money convert(Money money) {
        var convertedAmount =  money.getAmount() * rate;
        return new Money(convertedAmount, targetCurrency);
    }
}
