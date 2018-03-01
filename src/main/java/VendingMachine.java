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

    private String stateMessage = Constants.INSERT_COIN;

    private double totalAmountDeposited = 0;
    private double coinReturnAmount;
    private double moneyInMachine = 5.00;

    private ButtonHandler btnHandler = new ButtonHandler();
    private ItemHandler itemHandler = new ItemHandler();
    private MoneyHandler moneyHandler = new MoneyHandler();


    public double determineCoinValueBasedOnWeightAndSizeByDiameter(int weight, int diameter) {
        if (weight == ONE_GRAM && diameter == ONE_MILLIMETER) {
            this.moneyHandler.setCoinValue(0.25);
            this.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == TWO_GRAMS && diameter == TWO_MILLIMETERS) {
            this.moneyHandler.setCoinValue(0.10);
            this.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == THREE_GRAMS && diameter == THREE_MILLIMETERS) {
            this.moneyHandler.setCoinValue(0.05);
            this.incrementTotalAmountDepositedByCoinValue();
        } else {
            this.moneyHandler.setCoinValue(0.01);
            this.coinReturnAmount += this.moneyHandler.getCoinValue();
        }
        return this.moneyHandler.getCoinValue();

    }

    public void pressedButton(String item) {
        if (item.equals(Constants.CHIPS)) {
            this.btnHandler.setWasChipButtonPressed(true);
            this.dispense(ItemHandler.CHIPS_ITEM_ID);
        } else if (item.equals(Constants.COLA)) {
            this.btnHandler.setWasColaButtonPressed(true);
            this.dispense(ItemHandler.COLA_ITEM_ID);
        } else if (item.equals(Constants.CANDY)) {
            this.btnHandler.setWasCandyButtonPressed(true);
            this.dispense(ItemHandler.CANDY_ITEM_ID);
        } else {
            this.coinReturnAmount += this.totalAmountDeposited;
            this.btnHandler.setWasReturnButtonPressed(true);
            this.setStateMessage(Constants.INSERT_COIN);
        }
    }

    private void dispense(int itemID) {
        switch (itemID) {
            case ItemHandler.CHIPS_ITEM_ID:
                if (this.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getChipsPrice())) {
                    this.setStateMessage(Constants.PRICE + this.convertDoubleToString(this.itemHandler.getChipsPrice()));
                } else if (this.doWeHaveItemInStock(Constants.CHIPS) && this.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getChipsPrice())) {
                    this.itemHandler.reduceItemInventory(ItemHandler.CHIPS_ITEM_ID);
                    this.dispenseFlow(this.itemHandler.getChipsPrice());
                }
                break;

            case ItemHandler.COLA_ITEM_ID:
                if (this.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getColaPrice())) {
                    this.setStateMessage(Constants.PRICE + this.convertDoubleToString(this.itemHandler.getColaPrice()));
                }
                if (this.doWeHaveItemInStock(Constants.COLA) && this.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getColaPrice())) {
                    this.itemHandler.reduceItemInventory(ItemHandler.COLA_ITEM_ID);
                    this.dispenseFlow(this.itemHandler.getColaPrice());
                }
                break;

            case ItemHandler.CANDY_ITEM_ID:
                if (this.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getCandyPrice())) {
                    this.setStateMessage(Constants.PRICE + this.convertDoubleToString(this.itemHandler.getCandyPrice()));
                }
                if (this.doWeHaveItemInStock(Constants.CANDY) && this.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getCandyPrice())) {
                    this.itemHandler.reduceItemInventory(ItemHandler.CANDY_ITEM_ID);
                    this.dispenseFlow(this.itemHandler.getCandyPrice());
                }
                break;

            default:
                // I added this default case for good measure, if the program gets here, something went seriously wrong!!!
                this.setStateMessage(Constants.ERROR);
        }
    }

    private boolean isTotalDepositedGreaterThanOrEqualToItemPrice(double itemPrice) {
        return this.totalAmountDeposited >= itemPrice;
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
            return this.evaluator(this.itemHandler.getChipsInStock() > 0);
        } else if (itemName.equals(Constants.COLA)) {
            return this.evaluator(this.itemHandler.getColaInStock() > 0);
        } else if (itemName.equals(Constants.CANDY)) {
            return this.evaluator(this.itemHandler.getCandyInStock() > 0);
        }
        return false;
    }

    private boolean evaluator(boolean b) {
        if (b) {
            return true;
        } else if (this.btnHandler.getSoldOutButtonCounter() == 0) {
            this.setStateMessage(Constants.SOLD_OUT);
            this.btnHandler.incrementButtonCounter();
        } else {
            this.setStateMessage(this.convertDoubleToString(this.totalAmountDeposited));
            this.btnHandler.resetButtonCounter();
        }
        return false;
    }

    private void incrementTotalAmountDepositedByCoinValue() {
        this.totalAmountDeposited += this.moneyHandler.getCoinValue();
    }

    public double getCoinReturnAmount() {
        return this.coinReturnAmount;
    }

    public double getTotalAmountDeposited() {
        return this.totalAmountDeposited;
    }

    private void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    public String getStateMessage() {
        if (this.btnHandler.wasChipButtonPressed()) {
            return this.stateMessage;
        } else if (this.btnHandler.wasColaButtonPressed()) {
            return this.stateMessage;
        } else if (this.btnHandler.wasCandyButtonPressed()) {
            return this.stateMessage;
        } else if (this.btnHandler.wasReturnButtonPressed()) {
            return this.stateMessage;
        } else if (this.enoughMoneyInMachineForChange()) {
            this.setStateMessage(Constants.EXACT_CHANGE);
            return this.stateMessage;
        } else if (this.totalAmountDeposited > 0) {
            return this.convertDoubleToString(this.totalAmountDeposited);
        }
        return this.stateMessage;
    }

    private void makeChange(double itemPrice) {
        this.totalAmountDeposited = BigDecimal.valueOf(this.totalAmountDeposited).subtract(BigDecimal.valueOf(itemPrice)).doubleValue();
    }

    public void setMoneyInMachine(double moneyInMachine) {
        this.moneyInMachine = moneyInMachine;
    }

    private boolean enoughMoneyInMachineForChange() {
        return moneyInMachine < this.itemHandler.getChipsPrice() || moneyInMachine < this.itemHandler.getColaPrice() || moneyInMachine < this.itemHandler.getCandyPrice();
    }

    public ItemHandler getItemHandler() {
        return itemHandler;
    }
}
