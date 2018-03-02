//  Created by James DuBois on 2/21/18.
//  Copyright © 2018 VendingMachineKata. All rights reserved.


public class VendingMachine {
    private ButtonHandler btnHandler = new ButtonHandler();
    private MoneyHandler moneyHandler = new MoneyHandler();
    private DisplayHandler display = new DisplayHandler(this.btnHandler,moneyHandler);
    private ItemHandler itemHandler = new ItemHandler(this.btnHandler,this.display,this.moneyHandler);

    private Helper helper = new Helper();


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
                    displayItemPrice(this.itemHandler.getChipsPrice());
                } else if (this.itemHandler.doWeHaveItemInStock(Constants.CHIPS) && this.moneyHandler.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getChipsPrice())) {
                    completeTransaction(ItemHandler.CHIPS_ITEM_ID, this.itemHandler.getChipsPrice());
                }
                break;

            case ItemHandler.COLA_ITEM_ID:
                if (this.moneyHandler.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getColaPrice())) {
                    displayItemPrice(this.itemHandler.getColaPrice());
                }
                else if (this.itemHandler.doWeHaveItemInStock(Constants.COLA) && this.moneyHandler.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getColaPrice())) {
                    completeTransaction(ItemHandler.COLA_ITEM_ID, this.itemHandler.getColaPrice());
                }
                break;

            case ItemHandler.CANDY_ITEM_ID:
                if (this.moneyHandler.isTotalAmountDepositedLessThanItemPrice(this.itemHandler.getCandyPrice())) {
                    displayItemPrice(this.itemHandler.getCandyPrice());
                }
                else if (this.itemHandler.doWeHaveItemInStock(Constants.CANDY) && this.moneyHandler.isTotalDepositedGreaterThanOrEqualToItemPrice(this.itemHandler.getCandyPrice())) {
                    completeTransaction(ItemHandler.CANDY_ITEM_ID, this.itemHandler.getCandyPrice());
                }
                break;

            default:
                this.display.setStateMessage(Constants.ERROR);
        }
    }

    private void displayItemPrice(double itemPrice) {
        this.display.setStateMessage(Constants.PRICE + this.helper.convertDoubleToString(itemPrice));
    }

    private void completeTransaction(int itemID, double itemPrice) {
        this.itemHandler.reduceItemInventory(itemID);
        this.moneyHandler.processTransaction(itemPrice);
        this.display.setStateMessage(Constants.THANK_YOU);
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
