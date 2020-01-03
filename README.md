Complete the implementation of the Money Model:
 - Review the current implementation and make any change you consider to improve this Model.
 - Add the behaviour needed to multiply a Money to a factor.
 - Add the behaviour needed to convert Money to a different currency given a Date (the Date of the conversion rate).
 - Finally, given a list of Items, where each PriceTax has a Price in ARS and a Tax, it is necessary to calculate
   the sum of all the Items (PriceTax) and then convert the total to Dollars with the conversion rate of the
   previous Date.
 
```code
public class ShowMeTheMoney {

    public static class Money {
        private Double amount;
        private String currency;

        Money(Double amount, String currency) {
            this.amount = amount;
            this.currency = currency;
        }

        public void plus(Double amount) {
            this.amount += amount;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Money)) return false;
            Money money = (Money) obj;
            return money.amount.equals(this.amount) && money.currency.equals(this.currency);
        }

    }

    public static void main(String[] args) {
        Money ars25 = new Money(25.00, "ARS");
        Money ars10 = new Money(10.00, "ARS");

        ars25.plus(10.00);
        ars10.plus(25.00);

        assert ars25.equals(ars10);
    }

}
```