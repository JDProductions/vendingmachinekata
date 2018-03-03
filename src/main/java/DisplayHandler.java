public class DisplayHandler {

    private String stateMessage = Constants.INSERT_COIN;

    private Helper helper = new Helper();
    private ButtonHandler btnHandler;
    private MoneyHandler moneyHandler;

    public DisplayHandler(ButtonHandler btnHandler, MoneyHandler moneyHandler) {
        this.btnHandler = btnHandler;
        this.moneyHandler = moneyHandler;
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
        } else if (this.moneyHandler.enoughMoneyInMachineForChange()) {
            this.setStateMessage(Constants.EXACT_CHANGE);
            return this.stateMessage;
        } else if (this.moneyHandler.getTotalAmountDeposited() > 0) {
            return this.helper.convertDoubleToString(this.moneyHandler.getTotalAmountDeposited());
        }
        return this.stateMessage;
    }

    public void setStateMessage(String stateMessage) {
        this.stateMessage = stateMessage;
    }

    public void displayItemPrice(double itemPrice) {
        this.setStateMessage(Constants.PRICE + this.helper.convertDoubleToString(itemPrice));
    }

}
