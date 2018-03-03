//  Created by James DuBois on 2/21/18.
//  Copyright © 2018 VendingMachineKata. All rights reserved.


public class VendingMachine {
    private ButtonHandler btnHandler = new ButtonHandler();
    private MoneyHandler moneyHandler = new MoneyHandler();
    private DisplayHandler display = new DisplayHandler(this.btnHandler, this.moneyHandler);
    private ItemHandler itemHandler = new ItemHandler(this.btnHandler, this.display, this.moneyHandler);
    private Helper helper = new Helper(this.moneyHandler, this.display, this.itemHandler, this.btnHandler);


    public ItemHandler getItemHandler() {
        return itemHandler;
    }

    public MoneyHandler getMoneyHandler() {
        return moneyHandler;
    }

    public DisplayHandler getDisplay() {
        return display;
    }

    public Helper getHelper() {
        return helper;
    }
}
