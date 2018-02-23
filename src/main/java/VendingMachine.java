public class VendingMachine {

    double coinValue = 0;
    double totalAmountDeposited = 0;
    double coinReturnAmount;


    public String getStateMessage() {
        return "INSERT COIN";
    }

    public double determineCoinValueBasedOnWeightAndSizeByDiameter(int weight, int diameter) {
        if (weight == 1 && diameter == 1) {
            coinValue = 0.25;
            totalAmountDeposited += coinValue;
            return  coinValue;
        }
        else if (weight == 2 && diameter == 2) {
            coinValue = 0.10;
            return coinValue;
        }
        else if (weight == 3 && diameter == 3) {
            coinValue = 0.05;
            return coinValue;
        }
        else
            coinValue = .01;
            coinReturnAmount += coinValue;
            return coinValue;

    }

    public double getCoinReturnAmount() {
        return this.coinReturnAmount;
    }

    public double getTotalAmountDeposited() {
        return this.totalAmountDeposited;
    }
}
