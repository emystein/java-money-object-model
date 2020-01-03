import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @Test
    void givenTwoAmountsWhenSumThemThenItShouldReturnANewAmountWithTheSum() {
        Money ars25 = new Money(25.00, "ARS");
        Money ars10 = new Money(10.00, "ARS");

        var ars35 = ars25.plus(ars10);

        assertThat(ars35.getAmount()).isEqualTo(35.00);
    }
}
