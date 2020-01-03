import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class PriceTax {
    private final Money price;
    private final Double tax;
}
