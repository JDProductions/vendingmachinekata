//  Created by James DuBois on 2/21/18.
//  Copyright © 2018 VendingMachineKata. All rights reserved.


import java.math.BigDecimal;

public class VendingMachine {
    /* I decided to not make the item prices constants because when the owner of this
       "machine" wants to update the price it will be possible  */

    private final int ONE_GRAM = 1;
    private final int ONE_MILLIMETER = 1;
    private final int TWO_GRAMS = 2;
    private final int TWO_MILLIMETERS = 2;
    private final int THREE_GRAMS = 3;
    private final int THREE_MILLIMETERS = 3;

    private final int CHIPS_ITEM_ID = 1;
    private int chipsInStock = 5;
    private double chipsPrice = 0.50;
    private boolean wasChipButtonPressed = false;

    private final double colaPrice = 1.00;
    private final int COLA_ITEM_ID = 2;
    private int colaInStock = 5;
    private boolean wasColaButtonPressed = false;

    private final double candyPrice = 0.65;
    private final int CANDY_ITEM_ID = 3;
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
            this.setCoinValue(0.25);
            this.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == TWO_GRAMS && diameter == TWO_MILLIMETERS) {
            this.setCoinValue(0.10);
            this.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == THREE_GRAMS && diameter == THREE_MILLIMETERS) {
            this.setCoinValue(0.05);
            this.incrementTotalAmountDepositedByCoinValue();
        } else {
            this.setCoinValue(0.01);
            this.coinReturnAmount += this.coinValue;
        }
        return this.coinValue;

    }

    public void pressedButton(String item) {
        if (item.equals(Constants.CHIPS)) {
            this.setWasChipButtonPressed(true);
            this.dispense(CHIPS_ITEM_ID);

        } else if (item.equals(Constants.COLA)) {
            this.wasColaButtonPressed = true;
            this.dispense(COLA_ITEM_ID);
        } else if (item.equals(Constants.CANDY)) {
            this.wasCandyButtonPressed = true;
            this.dispense(CANDY_ITEM_ID);
        } else {
            this.coinReturnAmount += this.totalAmountDeposited;
            this.wasReturnButtonPressed = true;
            this.setStateMessage(Constants.INSERT_COIN);
        }
    }

    private void dispense(int itemID) {
        switch (itemID) {
            case CHIPS_ITEM_ID:
                if (this.isTotalAmountDepositedLessThanItemPrice(this.chipsPrice)) {
                    this.setStateMessage(Constants.PRICE + this.convertDoubleToString(this.chipsPrice));
                } else if (this.doWeHaveItemInStock(Constants.CHIPS) && this.totalAmountDeposited >= this.chipsPrice) {
                    this.chipsInStock -= 1;
                    this.dispenseFlow(chipsPrice);
                }
                break;

            case COLA_ITEM_ID:
                if (this.isTotalAmountDepositedLessThanItemPrice(this.colaPrice)) {
                    this.setStateMessage(Constants.PRICE + this.convertDoubleToString(this.colaPrice));
                }
                if (this.doWeHaveItemInStock(Constants.COLA) && this.totalAmountDeposited >= this.colaPrice) {
                    this.colaInStock -= 1;
                    this.dispenseFlow(colaPrice);
                }
                break;

            case CANDY_ITEM_ID:
                if (this.isTotalAmountDepositedLessThanItemPrice(this.candyPrice)) {
                    this.setStateMessage(Constants.PRICE + this.convertDoubleToString(this.candyPrice));
                }
                if (this.doWeHaveItemInStock(Constants.CANDY) && this.totalAmountDeposited >= this.candyPrice) {
                    this.candyInStock -= 1;
                    this.dispenseFlow(this.candyPrice);
                }
                break;

            default:
                // I added this default case for good measure, if the program gets here, something went seriously wrong!!!
                this.setStateMessage(Constants.ERROR);
        }
    }

    private boolean isTotalAmountDepositedLessThanItemPrice(double itemPrice) {
        return this.totalAmountDeposited < itemPrice;
    }

    private String convertDoubleToString(double itemPrice) {
        return Double.toString(itemPrice);
    }

    private void dispenseFlow(double itemPrice) {
        this.makeChange(itemPrice);
        this.incrementCoinReturnAmount();
        this.setStateMessage(Constants.THANK_YOU);
    }

    private void incrementCoinReturnAmount() {
        this.coinReturnAmount += this.totalAmountDeposited;
    }

    private boolean doWeHaveItemInStock(String itemName) {
        if (itemName.equals(Constants.CHIPS)) {
            return this.evaluator(this.chipsInStock > 0);
        } else if (itemName.equals(Constants.COLA)) {
            return this.evaluator(this.colaInStock > 0);
        } else if (itemName.equals(Constants.CANDY)) {
            return this.evaluator(this.candyInStock > 0);
        }
        return false;
    }

    private boolean evaluator(boolean b) {
        if (b) {
            return true;
        } else if (this.soldOutButtonCounter == 0) {
            this.setStateMessage(Constants.SOLD_OUT);
            this.soldOutButtonCounter++;
        } else {
            this.setStateMessage(Double.toString(this.totalAmountDeposited));
            this.soldOutButtonCounter = 0;
        }
        return false;
    }

    private void incrementTotalAmountDepositedByCoinValue() {
        this.totalAmountDeposited += this.coinValue;
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
        if (this.wasChipButtonPressed) {
            return this.stateMessage;
        } else if (this.wasColaButtonPressed) {
            return this.stateMessage;
        } else if (this.wasCandyButtonPressed) {
            return this.stateMessage;
        } else if (this.wasReturnButtonPressed) {
            return this.stateMessage;
        } else if (this.enoughMoneyInMachineForChange()) {
            this.setStateMessage(Constants.EXACT_CHANGE);
            return this.stateMessage;
        } else if (this.totalAmountDeposited > 0) {
            return Double.toString(this.totalAmountDeposited);
        }
        return this.stateMessage;
    }

    private void makeChange(double itemPrice) {
        this.totalAmountDeposited = BigDecimal.valueOf(this.totalAmountDeposited).subtract(BigDecimal.valueOf(itemPrice)).doubleValue();
    }

    private void setWasChipButtonPressed(boolean wasChipButtonPressed) {
        this.wasChipButtonPressed = wasChipButtonPressed;
    }

    public int getChipsInStock() {
        return this.chipsInStock;
    }

    public void setChipsInStock(int numberInStock) {
        this.chipsInStock = numberInStock;
    }

    public int getColasInStock() {
        return this.colaInStock;
    }

    public void setColaInStock(int numberInStock) {
        this.colaInStock = numberInStock;
    }

    public int getCandyInStock() {
        return candyInStock;
    }

    public void setCandyInStock(int numberInStock) {
        this.candyInStock = numberInStock;
    }

    public void setMoneyInMachine(double moneyInMachine) {
        this.moneyInMachine = moneyInMachine;
    }

    private boolean enoughMoneyInMachineForChange() {
        return moneyInMachine < this.chipsPrice || moneyInMachine < this.colaPrice || moneyInMachine < this.candyPrice;
    }
}
