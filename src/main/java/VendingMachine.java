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
        else
            return coinValue = .01;

    }
}
