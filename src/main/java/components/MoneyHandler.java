//  Created by James DuBois on 2/21/18.
//  Copyright © 2018 VendingMachineKata. All rights reserved.

package components;

import helpers.Constants;

import java.math.BigDecimal;

public class MoneyHandler {

    private double coinValue = 0;
    private double totalAmountDeposited = 0;
    private double coinReturnAmount;
    private double moneyInMachine = 5.00;

    private ItemHandler itemHandler = new ItemHandler();

    public double getCoinValue() {
        return this.coinValue;
    }

    void setCoinValue(double coinValue) {
        this.coinValue = coinValue;
    }

    public double getCoinReturnAmount() {
        return this.coinReturnAmount;
    }

    public double getTotalAmountDeposited() {
        return this.totalAmountDeposited;
    }

    public void incrementTotalAmountDepositedByCoinValue() {
        this.totalAmountDeposited += this.getCoinValue();
    }

    public void makeChange(double itemPrice) {
        this.totalAmountDeposited = BigDecimal.valueOf(this.getTotalAmountDeposited()).subtract(BigDecimal.valueOf(itemPrice)).doubleValue();
    }

    public void incrementCoinReturnAmountByCoinValue() {
        this.coinReturnAmount += this.getCoinValue();
    }

    public void incrementCoinReturnAmountByTotalDeposited() {
        this.coinReturnAmount += this.getTotalAmountDeposited();
    }

    public void setMoneyInMachine(double moneyInMachine) {
        this.moneyInMachine = moneyInMachine;
    }

    public boolean enoughMoneyInMachineForChange() {
        return this.moneyInMachine < this.itemHandler.getChipsPrice() || this.moneyInMachine < this.itemHandler.getColaPrice() || this.moneyInMachine < this.itemHandler.getCandyPrice();
    }

    public boolean isTotalDepositedAtleastGreaterThan(double itemPrice) {
        return this.getTotalAmountDeposited() >= itemPrice;
    }

    public boolean isTotalDepositedAtleastLessThan(double itemPrice) {
        return this.getTotalAmountDeposited() < itemPrice;
    }

    public void processTransaction(double itemPrice) {
        this.makeChange(itemPrice);
        this.incrementCoinReturnAmountByTotalDeposited();
    }

    public double determineCoinValueBasedOnWeightAndSizeByDiameter(int weight, int diameter) {
        if (weight == Constants.ONE_GRAM && diameter == Constants.ONE_MILLIMETER) {
            this.setCoinValue(0.25);
            this.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == Constants.TWO_GRAMS && diameter == Constants.TWO_MILLIMETERS) {
            this.setCoinValue(0.10);
            this.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == Constants.THREE_GRAMS && diameter == Constants.THREE_MILLIMETERS) {
            this.setCoinValue(0.05);
            this.incrementTotalAmountDepositedByCoinValue();
        } else {
            this.setCoinValue(0.01);
            this.incrementCoinReturnAmountByCoinValue();
        }
        return this.getCoinValue();

    }
}
