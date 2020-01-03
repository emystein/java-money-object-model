import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ConversionRates {
    private Map<String, Multimap<String, ConversionRate>> rates = new HashMap<>();

    public void addRate(LocalDate conversionDate, String sourceCurrency, Double rate, String targetCurrency) {
        rates.putIfAbsent(sourceCurrency, ArrayListMultimap.create());

        var targetConversions = rates.get(sourceCurrency);

        targetConversions.put(targetCurrency, new ConversionRate(conversionDate, rate));

        rates.put(sourceCurrency, targetConversions);
    }

    public Money convert(Money money, String targetCurrency, LocalDate conversionDate) {
        var conversionRates = rates.get(money.getCurrency()).get(targetCurrency);

        var conversionRate = conversionRates.stream().filter(r -> r.getConversionDate().equals(conversionDate)).findFirst().get();

        var convertedAmount =  money.getAmount() * conversionRate.getRate();

        return new Money(convertedAmount, targetCurrency);
    }
}
