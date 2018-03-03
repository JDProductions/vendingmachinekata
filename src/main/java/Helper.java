public class Helper {
    private MoneyHandler moneyHandler;
    private DisplayHandler display;
    private ItemHandler itemHandler;
    private ButtonHandler btnHandler;

    public Helper(MoneyHandler moneyHandler, DisplayHandler display, ItemHandler itemHandler,ButtonHandler btnHandler) {
        this.moneyHandler = moneyHandler;
        this.display = display;
        this.itemHandler = itemHandler;
        this.btnHandler = btnHandler;
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
                this.completeTransactionAndUpdateDisplay(this.itemHandler.getChipsPrice(), Constants.CHIPS, ItemHandler.CHIPS_ITEM_ID);
                break;

            case ItemHandler.COLA_ITEM_ID:
                this.completeTransactionAndUpdateDisplay(this.itemHandler.getColaPrice(), Constants.COLA, ItemHandler.COLA_ITEM_ID);
                break;

            case ItemHandler.CANDY_ITEM_ID:
                this.completeTransactionAndUpdateDisplay(this.itemHandler.getCandyPrice(), Constants.CANDY, ItemHandler.CANDY_ITEM_ID);
                break;

            default:
                this.display.setStateMessage(Constants.ERROR);
        }
    }

    private void completeTransactionAndUpdateDisplay(double itemPrice, String item, int itemID) {
        if (this.moneyHandler.isTotalDepositedAtleastLessThan(itemPrice)) {
            display.displayItemPrice(itemPrice);
        } else if (this.itemHandler.doWeHaveItemInStock(item) && this.moneyHandler.isTotalDepositedAtleastGreaterThan(itemPrice)) {
            this.itemHandler.reduceItemInventory(itemID);
            this.moneyHandler.processTransaction(itemPrice);
            this.display.setStateMessage(Constants.THANK_YOU);
        }
    }

    public static String convertDoubleToString(double itemPrice) {
        return Double.toString(itemPrice);
    }
}
