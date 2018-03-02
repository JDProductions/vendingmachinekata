//  Created by James DuBois on 2/21/18.
//  Copyright © 2018 VendingMachineKata. All rights reserved.


public class VendingMachine {
    /* I decided to not make the item prices constants because when the owner of this
       "machine" wants to update the price it will be possible  */

    private final int ONE_GRAM = 1;
    private final int ONE_MILLIMETER = 1;
    private final int TWO_GRAMS = 2;
    private final int TWO_MILLIMETERS = 2;
    private final int THREE_GRAMS = 3;
    private final int THREE_MILLIMETERS = 3;


    private ButtonHandler btnHandler = new ButtonHandler();
    private ItemHandler itemHandler = new ItemHandler();
    private MoneyHandler moneyHandler = new MoneyHandler();

    private DisplayHandler display = new DisplayHandler(this.btnHandler,moneyHandler);
    private Helper helper = new Helper();


    public double determineCoinValueBasedOnWeightAndSizeByDiameter(int weight, int diameter) {
        if (weight == ONE_GRAM && diameter == ONE_MILLIMETER) {
            this.moneyHandler.setCoinValue(0.25);
            this.moneyHandler.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == TWO_GRAMS && diameter == TWO_MILLIMETERS) {
            this.moneyHandler.setCoinValue(0.10);
            this.moneyHandler.incrementTotalAmountDepositedByCoinValue();
        } else if (weight == THREE_GRAMS && diameter == THREE_MILLIMETERS) {
            this.moneyHandler.setCoinValue(0.05);
            this.moneyHandler.incrementTotalAmountDepositedByCoinValue();
        } else {
            this.moneyHandler.setCoinValue(0.01);
            this.moneyHandler.incrementCoinReturnAmountByCoinValue();
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
            this.moneyHandler.incrementCoinReturnAmountByTotalDeposited();
            this.btnHandler.setWasReturnButtonPressed(true);
            this.display.setStateMessage(Constants.INSERT_COIN);
        }
    }

    private void dispense(int itemID) {
        switch (itemID) {
            case ItemHandler.CHIPS_ITEM_ID:
                if (this.moneyHandler.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getChipsPrice())) {
                    this.display.setStateMessage(Constants.PRICE + this.helper.convertDoubleToString(this.itemHandler.getChipsPrice()));
                } else if (this.doWeHaveItemInStock(Constants.CHIPS) && this.moneyHandler.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getChipsPrice())) {
                    this.itemHandler.reduceItemInventory(ItemHandler.CHIPS_ITEM_ID);
                    this.dispenseFlow(this.itemHandler.getChipsPrice());
                }
                break;

            case ItemHandler.COLA_ITEM_ID:
                if (this.moneyHandler.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getColaPrice())) {
                    this.display.setStateMessage(Constants.PRICE + this.helper.convertDoubleToString(this.itemHandler.getColaPrice()));
                }
                if (this.doWeHaveItemInStock(Constants.COLA) && this.moneyHandler.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getColaPrice())) {
                    this.itemHandler.reduceItemInventory(ItemHandler.COLA_ITEM_ID);
                    this.dispenseFlow(this.itemHandler.getColaPrice());
                }
                break;

            case ItemHandler.CANDY_ITEM_ID:
                if (this.moneyHandler.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getCandyPrice())) {
                    this.display.setStateMessage(Constants.PRICE + this.helper.convertDoubleToString(this.itemHandler.getCandyPrice()));
                }
                if (this.doWeHaveItemInStock(Constants.CANDY) && this.moneyHandler.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getCandyPrice())) {
                    this.itemHandler.reduceItemInventory(ItemHandler.CANDY_ITEM_ID);
                    this.dispenseFlow(this.itemHandler.getCandyPrice());
                }
                break;

            default:
                // I added this default case for good measure, if the program gets here, something went seriously wrong!!!
                this.display.setStateMessage(Constants.ERROR);
        }
    }

    private void dispenseFlow(double itemPrice) {
        this.moneyHandler.makeChange(itemPrice);
        this.moneyHandler.incrementCoinReturnAmountByTotalDeposited();
        this.display.setStateMessage(Constants.THANK_YOU);
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
            this.display.setStateMessage(Constants.SOLD_OUT);
            this.btnHandler.incrementButtonCounter();
        } else {
            this.display.setStateMessage(this.helper.convertDoubleToString(this.moneyHandler.getTotalAmountDeposited()));
            this.btnHandler.resetButtonCounter();
        }
        return false;
    }


    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    public MoneyHandler getMoneyHandler() {
        return moneyHandler;
    }

    public DisplayHandler getDisplay() {
        return display;
    }
}
