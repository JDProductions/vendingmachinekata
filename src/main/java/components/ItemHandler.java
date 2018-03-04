package components;

import helpers.Helper;
import helpers.Constants;

public class ItemHandler {
    public static final int CHIPS_ITEM_ID = 1;
    public static final int COLA_ITEM_ID = 2;
    public static final int CANDY_ITEM_ID = 3;
    private final double colaPrice = 1.00;
    private final double candyPrice = 0.65;
    private ButtonHandler btnHandler;
    private DisplayHandler display;
    private MoneyHandler moneyHandler;
    private double chipsPrice = 0.50;
    private int chipsInStock = 5;
    private int colaInStock = 5;
    private int candyInStock = 5;

    public ItemHandler(ButtonHandler btnHandler, DisplayHandler display, MoneyHandler moneyHandler) {
        this.btnHandler = btnHandler;
        this.display = display;
        this.moneyHandler = moneyHandler;
    }

    public ItemHandler() {

    }

    public double getCandyPrice() {
        return this.candyPrice;
    }

    public int getCandyInStock() {
        return this.candyInStock;
    }

    public void setCandyInStock(int candyInStock) {
        this.candyInStock = candyInStock;
    }

    public double getColaPrice() {
        return this.colaPrice;
    }


    public int getColaInStock() {
        return this.colaInStock;
    }

    public void setColaInStock(int colaInStock) {
        this.colaInStock = colaInStock;
    }


    public double getChipsPrice() {
        return this.chipsPrice;
    }


    public void reduceItemInventory(int itemID) {
        switch (itemID) {
            case 1:
                this.chipsInStock -= 1;
                break;
            case 2:
                this.colaInStock -= 1;
                break;
            case 3:
                this.candyInStock -= 1;
        }
    }

    public int getChipsInStock() {
        return this.chipsInStock;
    }

    public void setChipsInStock(int chipsInStock) {
        this.chipsInStock = chipsInStock;
    }

    public boolean doWeHaveItemInStock(String itemName) {
        if (itemName.equals(Constants.CHIPS)) {
            return this.evaluateIfItemIsSoldOut(this.getChipsInStock() > 0);
        } else if (itemName.equals(Constants.COLA)) {
            return this.evaluateIfItemIsSoldOut(this.getColaInStock() > 0);
        } else if (itemName.equals(Constants.CANDY)) {
            return this.evaluateIfItemIsSoldOut(this.getCandyInStock() > 0);
        }
        return false;
    }

    public boolean evaluateIfItemIsSoldOut(boolean b) {
        if (b) {
            return true;
        } else if (this.btnHandler.getSoldOutButtonCounter() == 0) {
            this.display.setStateMessage(Constants.SOLD_OUT);
            this.btnHandler.incrementButtonCounter();
        } else {
            this.display.setStateMessage(Helper.convertDoubleToString(this.moneyHandler.getTotalAmountDeposited()));
            this.btnHandler.resetButtonCounter();
        }
        return false;
    }

}
