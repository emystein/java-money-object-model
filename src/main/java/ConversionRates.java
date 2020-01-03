import java.time.LocalDate;
import java.util.*;

public class ConversionRates {
    private Set<ConversionRate> rates = new LinkedHashSet<>();

    public void addRate(String sourceCurrency, String targetCurrency, LocalDate conversionDate, Double rate) {
        rates.add(new ConversionRate(sourceCurrency, targetCurrency, conversionDate, rate));
    }

    public Money convert(Money money, String targetCurrency, LocalDate conversionDate) {
        var conversionRate = rates.stream()
                .filter(r -> r.getSourceCurrency().equals(money.getCurrency()) &&
                        r.getTargetCurrency().equals(targetCurrency) &&
                        r.getConversionDate().equals(conversionDate))
                .findFirst().get();

        return conversionRate.convert(money);
    }
}
