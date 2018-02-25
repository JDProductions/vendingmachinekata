import java.math.BigDecimal;

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

    private final double colaPrice = 1.00;
    private final int colaItemID = 2;
    private int colaInStock = 5;
    private boolean wasColaButtonPressed = false;

    private final double candyPrice = 0.65;
    private final int candyItemID = 3;
    private int candyInStock = 5;
    private boolean wasCandyButtonPressed = false;

    private boolean wasReturnButtonPressed = false;

    private int soldOutButtonCounter = 0;



    private String stateMessage = Constants.INSERT_COIN;

    private double coinValue = 0;
    private double totalAmountDeposited = 0;
    private double coinReturnAmount;
    private double moneyInMachine = 5.00;


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
        if (item.equals(Constants.CHIPS)) {
            setWasChipButtonPressed(true);
            dispense(chipsItemID);

        }
        else if (item.equals(Constants.COLA)) {
            wasColaButtonPressed = true;
            dispense(colaItemID);
        }

        else if (item.equals(Constants.CANDY)) {
            wasCandyButtonPressed = true;
            dispense(candyItemID);
        }
        else {
            coinReturnAmount += this.totalAmountDeposited;
            wasReturnButtonPressed = true;
            setStateMessage(Constants.INSERT_COIN);
        }
    }


    private void dispense(int itemID) {
        switch (itemID) {
            case chipsItemID:
                if (isTotalAmountDepositedLessThanItemPrice(this.chipsPrice)) {
                    setStateMessage(Constants.PRICE + convertDoubleToString(this.chipsPrice));
                }
                else if (doWeHaveItemInStock(Constants.CHIPS) && totalAmountDeposited >= chipsPrice) {
                    chipsInStock -= 1;
                    dispenseFlow(chipsPrice);
                    exactChangeDispenseFlow(calculateChange(chipsPrice));
                }
                break;

            case colaItemID:
                if (isTotalAmountDepositedLessThanItemPrice(this.colaPrice)) {
                setStateMessage(Constants.PRICE + convertDoubleToString(this.colaPrice));
            }
                if (doWeHaveItemInStock(Constants.COLA) && totalAmountDeposited >= colaPrice) {
                    colaInStock -= 1;
                    dispenseFlow(colaPrice);
                    exactChangeDispenseFlow(calculateChange(colaPrice));
                }
                break;

            case candyItemID:
                if (isTotalAmountDepositedLessThanItemPrice(this.candyPrice)) {
                    setStateMessage(Constants.PRICE + convertDoubleToString(this.candyPrice));
                }
                if (doWeHaveItemInStock(Constants.CANDY) && totalAmountDeposited >= candyPrice) {
                    candyInStock -= 1;
                    dispenseFlow(candyPrice);
                    exactChangeDispenseFlow(calculateChange(candyPrice));
                }
        }
    }

    private void exactChangeDispenseFlow(boolean b) {
        if (b) {
            setTotalAmountDeposited(0.0);
            setStateMessage(Constants.THANK_YOU);
        }
    }

    private boolean isTotalAmountDepositedLessThanItemPrice(double itemPrice) {
        return totalAmountDeposited < itemPrice;
    }

    private String convertDoubleToString(double itemPrice) {
        return Double.toString(itemPrice);
    }

    private void dispenseFlow(double itemPrice) {
        makeChange(itemPrice);
        incrementCoinReturnAmount();
        setStateMessage(Constants.THANK_YOU);
    }

    private void incrementCoinReturnAmount() {
        coinReturnAmount += totalAmountDeposited;
    }


    private boolean doWeHaveItemInStock(String itemName) {
        if (itemName.equals(Constants.CHIPS)) {
            return evaluator(chipsInStock > 0);


        }
        else if (itemName.equals(Constants.COLA)) {
            return evaluator(colaInStock > 0);
        }
        else if (itemName.equals(Constants.CANDY)) {
            return evaluator(candyInStock > 0);
        }
        return false;
    }

    private boolean evaluator(boolean b) {
        if (b)
        {
            return true;
        }
        else if (soldOutButtonCounter == 0) {
            setStateMessage(Constants.SOLD_OUT);
            soldOutButtonCounter++;
        }
        else {
            setStateMessage(Double.toString(this.totalAmountDeposited));
            soldOutButtonCounter = 0;
        }
        return false;
    }

    private void setWasChipButtonPressed(boolean wasChipButtonPressed) {
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

    private void setCoinValue(double coinValue) {
        this.coinValue = coinValue;
    }

    private void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    public String getStateMessage() {
        if (wasChipButtonPressed) {
            return stateMessage;
        }
        else if (wasColaButtonPressed) {
            return stateMessage;
        }

        else if (wasCandyButtonPressed) {
            return stateMessage;
        }

        else if (wasReturnButtonPressed) {
            return stateMessage;
        }

        else if (EnoughMoneyInMachineForChange()) {
            setStateMessage(Constants.EXACT_CHANGE);
            return stateMessage;
        }
        else if (totalAmountDeposited > 0) {
            return Double.toString(totalAmountDeposited);
        }
        return stateMessage;
    }

    private void setTotalAmountDeposited(double totalAmountDeposited) {
        this.totalAmountDeposited = totalAmountDeposited;
    }

    private boolean calculateChange(double itemPrice) {
        return this.totalAmountDeposited == itemPrice;
    }

    public int getColasInStock() {
        return this.colaInStock;
    }

    public int getCandyInStock() {
        return candyInStock;
    }

    private void makeChange(double itemPrice) {
        this.totalAmountDeposited = BigDecimal.valueOf(this.totalAmountDeposited).subtract(BigDecimal.valueOf(itemPrice)).doubleValue();
    }

    public void setChipsInStock(int numberInStock) {
        this.chipsInStock = numberInStock;
    }

    public void setColaInStock(int numberInStock) {
        this.colaInStock = numberInStock;
    }

    public void setCandyInStock(int numberInStock) {
        this.candyInStock = numberInStock;
    }

    public void setMoneyInMachine(double moneyInMachine) {
        this.moneyInMachine = moneyInMachine;
    }

    private boolean EnoughMoneyInMachineForChange() {
        return moneyInMachine < chipsPrice || moneyInMachine < colaPrice || moneyInMachine < candyPrice;
    }
}
