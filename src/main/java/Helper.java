public class Helper {
    private MoneyHandler moneyHandler;
    private DisplayHandler display;
    private ItemHandler itemHandler;
    private ButtonHandler btnHandler;

    public Helper(MoneyHandler moneyHandler, DisplayHandler display, ItemHandler itemHandler) {
        this.moneyHandler = moneyHandler;
        this.display = display;
        this.itemHandler = itemHandler;
    }


    public static String convertDoubleToString(double itemPrice) {
        return Double.toString(itemPrice);
    }

    public void completeTransactionAndUpdateDisplay(double itemPrice, String item, int itemID) {
        if (this.moneyHandler.isTotalDepositedAtleastLessThan(itemPrice)) {
            display.displayItemPrice(itemPrice);
        } else if (this.itemHandler.doWeHaveItemInStock(item) && this.moneyHandler.isTotalDepositedAtleastGreaterThan(itemPrice)) {
            this.itemHandler.reduceItemInventory(itemID);
            this.moneyHandler.processTransaction(itemPrice);
            this.display.setStateMessage(Constants.THANK_YOU);
        }
    }
}
