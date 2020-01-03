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

    public Money convert(Money money) {
        var convertedAmount =  money.getAmount() * rate;
        return new Money(convertedAmount, targetCurrency);
    }
}
