import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Money {
    private final Double amount;
    private final String currency;

    public Money plus(Money other) {
        return new Money(this.amount + other.amount, currency);
    }
}