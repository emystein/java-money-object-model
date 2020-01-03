import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ConversionRates {
    private Map<String, Set<ConversionRate>> rates = new HashMap<>();

    public void addRate(String sourceCurrency, String targetCurrency, LocalDate conversionDate, Double rate) {
        rates.putIfAbsent(sourceCurrency, new HashSet<>());

        var targetConversions = rates.get(sourceCurrency);

        targetConversions.add(new ConversionRate(targetCurrency, conversionDate, rate));

        rates.put(sourceCurrency, targetConversions);
    }

    public Money convert(Money money, String targetCurrency, LocalDate conversionDate) {
        var conversionRates = rates.get(money.getCurrency());

        var conversionRate = conversionRates.stream()
                .filter(r -> r.getTargetCurrency().equals(targetCurrency) && r.getConversionDate().equals(conversionDate))
                .findFirst().get();

        return conversionRate.convert(money);
    }
}
