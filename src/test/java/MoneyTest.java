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

    @Test
    void givenAnAmountWhenMultiplyTheAmountByAFactorThemThenItShouldReturnANewAmountWithTheProduct() {
        Money ars2 = new Money(2.00, "ARS");

        var ars6 = ars2.multiplyBy(3);

        assertThat(ars6.getAmount()).isEqualTo(6.00);
    }
}
