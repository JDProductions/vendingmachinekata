//  Created by James DuBois on 2/21/18.
//  Copyright © 2018 VendingMachineKata. All rights reserved.

import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VendingMachineTest {

    private final VendingMachine vendingMachine = new VendingMachine();
    private final ItemHandler itemHandler = this.vendingMachine.getItemHandler();
    private final MoneyHandler moneyHandler = this.vendingMachine.getMoneyHandler();
    private final DisplayHandler display = this.vendingMachine.getDisplay();
    private final Helper helper = vendingMachine.getHelper();


    private double DEPOSIT_QUARTER() {
        return this.moneyHandler.determineCoinValueBasedOnWeightAndSizeByDiameter(1, 1);
    }

    private double DEPOSIT_NICKEL() {
        return this.moneyHandler.determineCoinValueBasedOnWeightAndSizeByDiameter(3, 3);
    }

    private double DEPOSIT_PENNY() {
        return this.moneyHandler.determineCoinValueBasedOnWeightAndSizeByDiameter(4, 4);
    }

    private double DEPOSIT_DIME() {
        return this.moneyHandler.determineCoinValueBasedOnWeightAndSizeByDiameter(2, 2);
    }

    @Test
    public void whenVendingMachineIsIdleDisplayMessageShouldReadInsertCoin() {
        assertEquals("INSERT COIN", this.display.getStateMessage());

    }

    @Test
    public void whenIInsertAValidCoinTheDisplayMessageShouldReadTheTotalDepositedAmount() {
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();


        assertEquals("0.35", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfAQuarter() {
        double coinAmount = DEPOSIT_QUARTER();

        assertEquals(0.25, coinAmount, 0);
    }

    @Test
    public void whenIInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfADime() {
        double coinAmount = DEPOSIT_DIME();

        assertEquals(0.10, coinAmount, 0);
    }

    @Test
    public void whenIInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfANickel() {
        double coinAmount = DEPOSIT_NICKEL();

        assertEquals(0.05, coinAmount, 0);
    }

    @Test
    public void whenIInsertAPennyTheCoinReturnShouldReturnTheValueOfAPennyWhichWasInserted() {
        DEPOSIT_PENNY();
        this.moneyHandler.getCoinReturnAmount();

        assertEquals(0.01, this.moneyHandler.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDepositedQuarters() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();


        assertEquals(0.50, this.moneyHandler.getTotalAmountDeposited());

    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDeposited() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();
        // Notice the penny is not added to the total deposited.
        DEPOSIT_PENNY();


        assertEquals(0.65, this.moneyHandler.getTotalAmountDeposited());

    }

    @Test
    public void whenIInsertEnoughMoneyForChipsIShouldDispensedChipsAndTheDisplayMessageShouldReadTHANKYOU() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Chips");


        assertEquals("THANK YOU", this.display.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForChipsAndPressChipButtonTheChipsShouldBeDispensed() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Chips");

        // There are 5 bags of chips in stock, I am now expecting 4 bags to be in stock meaning one of the bags has been dispensed.
        assertEquals(4, this.itemHandler.getChipsInStock());
    }

    @Test
    public void whenIInsertTheExactAmountNeededForChipsAndPressChipButtonTheTotalAmountDepositedShouldBeZero() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Chips");

        assertEquals(0.0, this.moneyHandler.getTotalAmountDeposited());
    }

    @Test
    public void whenIPressTheChipButtonAndIDontHaveEnoughMoneyDepositedTheDisplayMessageWillReadPRICEAndTheCostOfTheChips() {
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Chips");

        assertEquals("PRICE " + "0.5", this.display.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForColaIShouldDispensedColaAndTheDisplayMessageShouldReadTHANKYOU() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Cola");


        assertEquals("THANK YOU", this.display.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForColaAndPressColaButtonTheColaShouldBeDispensed() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Cola");

        // There are 5 cola's in stock, I am now expecting 4 cola's to be in stock meaning one of the cola's has been dispensed.
        assertEquals(4, this.itemHandler.getColaInStock());
    }

    @Test
    public void whenIPressTheColaButtonAndIDontHaveEnoughMoneyDepositedTheDisplayMessageWillReadPRICEAndTheCostOfTheCola() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Cola");

        assertEquals("PRICE " + "1.0", this.display.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForCandyIShouldDispensedCandyAndTheDisplayMessageShouldReadTHANKYOU() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        this.helper.pressedButton("Candy");


        assertEquals("THANK YOU", this.display.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForCandyAndPressCandyButtonTheCandyShouldBeDispensed() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        this.helper.pressedButton("Candy");

        // There are 5 pieces of candy in stock, I am now expecting 4 pieces of candy to be in stock meaning one of the pieces of candy has been dispensed.
        assertEquals(4, this.itemHandler.getCandyInStock());
    }

    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForCandyIShouldStillSeeTHANKYOUDisplayedSinceItsASuccessfulTransaction() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Candy");

        assertEquals("THANK YOU", this.display.getStateMessage());
    }

    @Test
    public void whenIPressTheCandyButtonAndIDontHaveEnoughMoneyDepositedTheDisplayMessageWillReadPRICEAndTheCostOfTheCandy() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Candy");

        assertEquals("PRICE " + "0.65", this.display.getStateMessage());
    }


    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForChipsIShouldBeReturnedChange() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Chips");

        assertEquals(.5, this.moneyHandler.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForChipsIShouldStillSeeTHANKYOUDisplayedSinceItsASuccessfulTransaction() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Chips");

        assertEquals("THANK YOU", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForColaIShouldBeReturnedChange() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Cola");

        assertEquals(.25, this.moneyHandler.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForColaIShouldStillSeeTHANKYOUDisplayedSinceItsASuccessfulTransaction() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Cola");

        assertEquals("THANK YOU", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForCandyIShouldBeReturnedChange() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_DIME();

        this.helper.pressedButton("Candy");

        assertEquals(.05, this.moneyHandler.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoneyAndPushReturnCoinButtonIShouldGetAllOfMyMoneyBack() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();

        this.helper.pressedButton("Return Coin");

        assertEquals(.6, this.moneyHandler.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoneyAndPushReturnCoinButtonTheDisplayMessageShouldSayINSERTCOIN() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.helper.pressedButton("Return Coin");

        assertEquals("INSERT COIN", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheChipsButtonButChipsAreSoldOutTheDisplayMessageShouldReadSOLDOUT() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.itemHandler.setChipsInStock(0);
        this.helper.pressedButton("Chips");

        assertEquals("SOLD OUT", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheChipsButtonButChipsAreSoldOut2xOnTheSecondPressIShouldSeeTheAmountIHaveDeposited() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.itemHandler.setChipsInStock(0);
        this.helper.pressedButton("Chips");
        this.helper.pressedButton("Chips");

        assertEquals("0.5", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheColaButtonButColaAreSoldOutTheDisplayMessageShouldReadSOLDOUT() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.itemHandler.setColaInStock(0);
        this.helper.pressedButton("Cola");

        assertEquals("SOLD OUT", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheColaButtonButColasAreSoldOut2xOnTheSecondPressIShouldSeeTheAmountIHaveDeposited() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        this.itemHandler.setColaInStock(0);
        this.helper.pressedButton("Cola");
        this.helper.pressedButton("Cola");

        assertEquals("1.0", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheCandyButtonButCandyIsSoldOutTheDisplayMessageShouldReadSOLDOUT() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        this.itemHandler.setCandyInStock(0);
        this.helper.pressedButton("Candy");

        assertEquals("SOLD OUT", this.display.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheCandyButtonButCandyIsSoldOut2xOnTheSecondPressIShouldSeeTheAmountIHaveDeposited() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        this.itemHandler.setCandyInStock(0);
        this.helper.pressedButton("Candy");
        this.helper.pressedButton("Candy");

        assertEquals("0.65", this.display.getStateMessage());
    }

    @Test
    public void whenMachineCanOnlyAcceptExactChange() {
        this.moneyHandler.setMoneyInMachine(0.0);

        assertEquals("EXACT CHANGE ONLY", this.display.getStateMessage());
    }

}
