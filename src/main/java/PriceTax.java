import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PriceTax {
    private final Money price;
    private final Money tax;

    public PriceTax(PriceTax a, PriceTax b) {
        this(new Money(a.price.plus(b.price).getAmount(), a.getCurrency()), a.tax.plus(b.tax));
    }

    public String getCurrency() {
        return price.getCurrency();
    }
}
