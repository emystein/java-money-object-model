import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ConversionRates {
    private Map<String, Multimap<String, ConversionRate>> rates = new HashMap<>();

    public void addRate(String sourceCurrency, String targetCurrency, LocalDate conversionDate, Double rate) {
        rates.putIfAbsent(sourceCurrency, ArrayListMultimap.create());

        var targetConversions = rates.get(sourceCurrency);

        targetConversions.put(targetCurrency, new ConversionRate(targetCurrency, conversionDate, rate));

        rates.put(sourceCurrency, targetConversions);
    }

    public Money convert(Money money, String targetCurrency, LocalDate conversionDate) {
        var conversionRates = rates.get(money.getCurrency()).get(targetCurrency);

        var conversionRate = conversionRates.stream()
                .filter(r -> r.getTargetCurrency().equals(targetCurrency) && r.getConversionDate().equals(conversionDate))
                .findFirst().get();

        return conversionRate.convert(money);
    }
}
