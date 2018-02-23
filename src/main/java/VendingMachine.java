public class VendingMachine {

    double coinValue = 0;


    public String getStateMessage() {
        return "INSERT COIN";
    }

    public double determineCoinValueBasedOnWeightAndSizeByDiameter(int weight, int diameter) {
        if (weight == 1 && diameter == 1) {
            coinValue = 0.25;
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
            return coinValue = .01;

    }

    public double getCoinReturnAmount() {
        return 0.0;
    }
}
