import java.math.BigDecimal;

public class MoneyHandler {

    private double coinValue = 0;

    private double totalAmountDeposited = 0;


    void setCoinValue(double coinValue) {
        this.coinValue = coinValue;
    }

    public double getCoinValue() {
        return coinValue;
    }

    public double getTotalAmountDeposited() {
        return totalAmountDeposited;
    }

    public void incrementTotalAmountDepositedByCoinValue() {
        this.totalAmountDeposited += this.getCoinValue();
    }

    public void makeChange(double itemPrice) {
        this.totalAmountDeposited = BigDecimal.valueOf(this.getTotalAmountDeposited()).subtract(BigDecimal.valueOf(itemPrice)).doubleValue();
    }

}
