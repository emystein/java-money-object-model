import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class ConversionRate {
    private final String targetCurrency;
    private final LocalDate conversionDate;
    private final Double rate;

    public Money convert(Money money) {
        var convertedAmount =  money.getAmount() * rate;
        return new Money(convertedAmount, targetCurrency);
    }
}
