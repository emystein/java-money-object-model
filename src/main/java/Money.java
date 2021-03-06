import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class Money {
    private final Double amount;
    private final String currency;

    public Money plus(Money other) {
        return new Money(amount + other.amount, currency);
    }

    public Money multiplyBy(int factor) {
        return new Money(amount * factor, currency);
    }
}