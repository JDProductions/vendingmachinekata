//  Created by James DuBois on 2/21/18.
//  Copyright © 2018 VendingMachineKata. All rights reserved.


public class VendingMachine {
    private ButtonHandler btnHandler = new ButtonHandler();
    private MoneyHandler moneyHandler = new MoneyHandler();
    private DisplayHandler display = new DisplayHandler(this.btnHandler,moneyHandler);
    private ItemHandler itemHandler = new ItemHandler(this.btnHandler,this.display,this.moneyHandler);

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
                completeTransactionAndUpdateDisplay(this.itemHandler.getChipsPrice(), Constants.CHIPS, ItemHandler.CHIPS_ITEM_ID);
                break;

            case ItemHandler.COLA_ITEM_ID:
                completeTransactionAndUpdateDisplay(this.itemHandler.getColaPrice(), Constants.COLA, ItemHandler.COLA_ITEM_ID);
                break;

            case ItemHandler.CANDY_ITEM_ID:
                completeTransactionAndUpdateDisplay(this.itemHandler.getCandyPrice(), Constants.CANDY, ItemHandler.CANDY_ITEM_ID);
                break;

            default:
                this.display.setStateMessage(Constants.ERROR);
        }
    }

    private void completeTransactionAndUpdateDisplay(double itemPrice, String item, int itemID) {
        if (this.moneyHandler.isTotalAmountDepositedLessThanItemPrice(itemPrice)) {
            display.displayItemPrice(itemPrice);
        } else if (this.itemHandler.doWeHaveItemInStock(item) && this.moneyHandler.isTotalDepositedGreaterThanOrEqualToItemPrice(itemPrice)) {
            this.itemHandler.reduceItemInventory(itemID);
            this.moneyHandler.processTransaction(itemPrice);
            this.display.setStateMessage(Constants.THANK_YOU);
        }
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
