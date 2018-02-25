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
    private boolean transactionSuccessful = false;

    private final double colaPrice = 1.00;
    private final int colaItemID = 2;
    private int colaInStock = 5;
    private boolean wasColaButtonPressed = false;

    private final double candyPrice = 0.65;
    private final int candyItemID = 3;
    private int candyInStock = 5;
    private boolean wasCandyButtonPressed = false;

    private boolean wasReturnButtonPressed = false;

    int soldOutButtonCounter = 0;



    private String stateMessage = "INSERT COIN";

    double coinValue = 0;
    double totalAmountDeposited = 0;
    double coinReturnAmount;
    private double moneyInMachine;


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
            wasColaButtonPressed = true;
            dispense(colaItemID);
        }

        else if (item.equals("Candy")) {
            wasCandyButtonPressed = true;
            dispense(candyItemID);
        }
        else {
            coinReturnAmount += this.totalAmountDeposited;
            wasReturnButtonPressed = true;
            setStateMessage("INSERT COIN");
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
                    if (totalAmountDeposited > chipsPrice) {
                        makeChange(chipsPrice);
                        incrementCoinReturnAmount();
                    }
                    else if (calculateChange(chipsPrice)) {
                        setTotalAmountDeposited(0.0);
                        setStateMessage("THANK YOU");
                        transactionSuccessful = true;
                    }
                }
                break;

            case colaItemID:
                if (totalAmountDeposited < colaPrice) {
                setStateMessage("PRICE " + Double.toString(colaPrice));
            }
                if (doWeHaveItemInStock("Cola") && totalAmountDeposited >= colaPrice) {
                    colaInStock -= 1;
                    if (totalAmountDeposited > colaPrice) {
                        makeChange(colaPrice);
                        incrementCoinReturnAmount();
                    }
                    else if (calculateChange(colaPrice)) {
                        setTotalAmountDeposited(0.0);
                        setStateMessage("THANK YOU");
                    }
                }
                break;

            case candyItemID:
                if (totalAmountDeposited < candyPrice) {
                    setStateMessage("PRICE " + Double.toString(candyPrice));
                }
                if (doWeHaveItemInStock("Candy") && totalAmountDeposited >= candyPrice) {
                    candyInStock -= 1;
                    if (totalAmountDeposited > candyPrice) {
                        makeChange(candyPrice);
                        incrementCoinReturnAmount();
                    }
                    else if (calculateChange(candyPrice)) {
                        setTotalAmountDeposited(0.0);
                        setStateMessage("THANK YOU");
                    }
                }
        }
    }

    private void incrementCoinReturnAmount() {
        coinReturnAmount += totalAmountDeposited;
    }


    public boolean doWeHaveItemInStock(String itemName) {
        if (itemName.equals("Chips")) {
            if (chipsInStock > 0)
            {
                return true;
            }
            else if (soldOutButtonCounter == 0) {
                setStateMessage("SOLD OUT");
                soldOutButtonCounter++;
            }
            else {
                setStateMessage(Double.toString(this.totalAmountDeposited));
                soldOutButtonCounter = 0;
            }


        }
        else if (itemName.equals("Cola")) {
            if (colaInStock > 0) {
                return true;
            }

            else if (soldOutButtonCounter == 0) {
                setStateMessage("SOLD OUT");
                soldOutButtonCounter++;

            }
            else {
                setStateMessage(Double.toString(this.totalAmountDeposited));
                soldOutButtonCounter = 0;
            }
        }
        else if (itemName.equals("Candy")) {
            if (candyInStock > 0) {
                return true;
            }
            else if (soldOutButtonCounter == 0) {
                setStateMessage("SOLD OUT");
                soldOutButtonCounter++;
            }
            else {
                setStateMessage(Double.toString(this.totalAmountDeposited));
                soldOutButtonCounter = 0;
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
        else if (totalAmountDeposited > 0) {
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

    public int getCandyInStock() {
        return candyInStock;
    }

    public void makeChange(double itemPrice) {
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
}
