public class VendingMachine {

    private final int ONE_GRAM = 1;
    private final int ONE_MILLIMETER = 1;
    private final int TWO_GRAMS = 2;
    private final int TWO_MILLIMETERS = 2;
    private final int THREE_GRAMS = 3;
    private final int THREE_MILLIMETERS = 3;

    private final int chipsItemID = 1;

    private int chipsInStock = 5;
    private double chipsPrice = 0.50;
    private boolean wasChipButtonPressed = false;
    private boolean transactionSuccessful = false;

    private final double colaPrice = 1.00;
    private final int colaItemID = 2;
    private int colaInStock = 5;



    private String stateMessage = "INSERT COIN";

    double coinValue = 0;
    double totalAmountDeposited = 0;
    double coinReturnAmount;


    public double determineCoinValueBasedOnWeightAndSizeByDiameter(int weight, int diameter) {
        if (weight == ONE_GRAM && diameter == ONE_MILLIMETER) {
            setCoinValue(0.25);
            incrementTotalAmountDepositedByCoinValue();
        } else if (weight == TWO_GRAMS && diameter == TWO_MILLIMETERS) {
            setCoinValue(0.10);
            incrementTotalAmountDepositedByCoinValue();
        } else if (weight == THREE_GRAMS && diameter == THREE_MILLIMETERS) {
            setCoinValue(0.05);
            incrementTotalAmountDepositedByCoinValue();
        } else {
            setCoinValue(0.01);
            coinReturnAmount += coinValue;
        }
        return coinValue;

    }

    public void pressedButton(String item) {
        if (item.equals("Chips")) {
            setWasChipButtonPressed(true);
            dispense(chipsItemID);

        }
        else if (item.equals("Cola")) {
            dispense(colaItemID);
        }
    }


    public void dispense(int itemID) {
        switch (itemID) {
            case chipsItemID:
                if (transactionSuccessful) {
                    setStateMessage("INSERT COIN");
                }
                else if (totalAmountDeposited < chipsPrice) {
                    setStateMessage("PRICE " + Double.toString(chipsPrice));
                }
                if (doWeHaveItemInStock("Chips") && totalAmountDeposited >= chipsPrice) {
                    chipsInStock -= 1;

                    if (calculateChange(chipsPrice)) {
                        setTotalAmountDeposited(0.0);
                        setStateMessage("THANK YOU");
                        transactionSuccessful = true;
                    }
                }
                break;

            case colaItemID:
                if (doWeHaveItemInStock("Cola") && totalAmountDeposited >= colaPrice) {
                    colaInStock -= 1;
                    if (calculateChange(colaPrice)) {
                        setTotalAmountDeposited(0.0);
                        setStateMessage("THANK YOU");
                    }
                }

            default:
        }
    }


    public boolean doWeHaveItemInStock(String itemName) {
        if (itemName.equals("Chips")) {
            if (chipsInStock > 0)
            {
                return true;
            }
        }
        else if (itemName.equals("Cola")) {
            if (colaInStock > 0) {
                return true;
            }
        }
        return false;
    }

    public void setWasChipButtonPressed(boolean wasChipButtonPressed) {
        this.wasChipButtonPressed = wasChipButtonPressed;
    }

    public int getChipsInStock() {
        return chipsInStock;
    }

    private void incrementTotalAmountDepositedByCoinValue() {
        totalAmountDeposited += coinValue;
    }

    public double getCoinReturnAmount() {
        return this.coinReturnAmount;
    }

    public double getTotalAmountDeposited() {
        return this.totalAmountDeposited;
    }

    public void setCoinValue(double coinValue) {
        this.coinValue = coinValue;
    }

    public void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    public String getStateMessage() {
        if (wasChipButtonPressed) {
            return stateMessage;
        } else if (totalAmountDeposited > 0) {
            return Double.toString(totalAmountDeposited);
        }
        return stateMessage;
    }

    public void setTotalAmountDeposited(double totalAmountDeposited) {
        this.totalAmountDeposited = totalAmountDeposited;
    }

    public boolean calculateChange(double itemPrice) {
        if (this.totalAmountDeposited == itemPrice) {
            return true;
        }
        return false;
    }

    public int getColasInStock() {
        return this.colaInStock;
    }
}
