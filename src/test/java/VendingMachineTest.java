import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VendingMachineTest {

    private final VendingMachine vendingMachine = new VendingMachine();

    @Test
    public void whenVendingMachineIsIdleDisplayMessageShouldReadInsertCoin() {
        assertEquals("INSERT COIN", vendingMachine.getStateMessage());

    }

    private double DEPOSIT_QUARTER() {
        return vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
    }

    private double DEPOSIT_NICKEL() {
        return vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(3,3);
    }

    private double DEPOSIT_PENNY() {
        return vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(4,4);
    }

    private double DEPOSIT_DIME() {
        return vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(2,2);
    }

    @Test
    public void whenIInsertAValidCoinTheDisplayMessageShouldReadTheTotalDepositedAmount() {
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();


        assertEquals("0.35",vendingMachine.getStateMessage());
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfAQuarter() {
        double coinAmount = DEPOSIT_QUARTER();

        assertEquals(0.25, coinAmount,0);
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfADime() {
        double coinAmount = DEPOSIT_DIME();

        assertEquals(0.10, coinAmount,0);
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfANickel() {
        double coinAmount = DEPOSIT_NICKEL();

        assertEquals(0.05, coinAmount,0);
    }

    @Test
    public void whenIInsertAPennyTheCoinReturnShouldReturnTheValueOfAPennyWhichWasInserted() {
        DEPOSIT_PENNY();
        vendingMachine.getCoinReturnAmount();

        assertEquals(0.01, vendingMachine.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDepositedQuarters() {
        // I know these variable aren't being used but I added them for readability for the Artisan.
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();


        assertEquals(0.50,vendingMachine.getTotalAmountDeposited());

    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDeposited() {
        // I know these variable aren't being used but I added them for readability for the Artisan.
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();
        // Notice the penny is not added to the total deposited.
        DEPOSIT_PENNY();


        assertEquals(0.65,vendingMachine.getTotalAmountDeposited());

    }

    @Test
    public void whenIInsertEnoughMoneyForChipsIShouldDispensedChipsAndTheDisplayMessageShouldReadTHANKYOU(){
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Chips");


        assertEquals("THANK YOU",vendingMachine.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForChipsAndPressChipButtonTheChipsShouldBeDispensed() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Chips");

        // There are 5 bags of chips in stock, I am now expecting 4 bags to be in stock meaning one of the bags has been dispensed.
        assertEquals(4, vendingMachine.getChipsInStock());
    }

    @Test
    public void whenIInsertTheExactAmountNeededForChipsAndPressChipButtonTheTotalAmountDepositedShouldBeZero() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Chips");

        // There are 5 bags of chips in stock, I am now expecting 4 bags to be in stock meaning one of the bags has been dispensed.
        assertEquals(0.0, vendingMachine.getTotalAmountDeposited());
    }

    @Test
    public void afterASuccessfulTransactionBuyingChipsWhenIPressTheChipButtonAgainIShouldSeeTheDisplayMessageAsINSERTCOIN() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Chips");
        vendingMachine.pressedButton("Chips");

        assertEquals("INSERT COIN", vendingMachine.getStateMessage());

    }

    @Test
    public void whenIPressTheChipButtonAndIDontHaveEnoughMoneyDepositedTheDisplayMessageWillReadPRICEAndTheCostOfTheChips() {
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Chips");

        assertEquals("PRICE " + "0.5", vendingMachine.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForColaIShouldDispensedColaAndTheDisplayMessageShouldReadTHANKYOU(){
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Cola");


        assertEquals("THANK YOU",vendingMachine.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForColaAndPressColaButtonTheColaShouldBeDispensed() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Cola");

        // There are 5 cola's in stock, I am now expecting 4 cola's to be in stock meaning one of the cola's has been dispensed.
        assertEquals(4, vendingMachine.getColasInStock());
    }

    @Test
    public void whenIPressTheColaButtonAndIDontHaveEnoughMoneyDepositedTheDisplayMessageWillReadPRICEAndTheCostOfTheCola() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Cola");

        assertEquals("PRICE " + "1.0", vendingMachine.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForCandyIShouldDispensedCandyAndTheDisplayMessageShouldReadTHANKYOU(){
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        vendingMachine.pressedButton("Candy");


        assertEquals("THANK YOU",vendingMachine.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForCandyAndPressCandyButtonTheCandyShouldBeDispensed() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        vendingMachine.pressedButton("Candy");

        // There are 5 pieces of candy in stock, I am now expecting 4 pieces of candy to be in stock meaning one of the pieces of candy has been dispensed.
        assertEquals(4, vendingMachine.getCandyInStock());
    }

    @Test
    public void whenIPressTheCandyButtonAndIDontHaveEnoughMoneyDepositedTheDisplayMessageWillReadPRICEAndTheCostOfTheCandy() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Candy");

        assertEquals("PRICE " + "0.65", vendingMachine.getStateMessage());
    }


    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForChipsIShouldBeReturnedChange() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Chips");

        assertEquals(.5, vendingMachine.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForColaIShouldBeReturnedChange() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Cola");

        assertEquals(.25, vendingMachine.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoreMoneyThenWhatsNeededForCandyIShouldBeReturnedChange() {
         DEPOSIT_QUARTER();
         DEPOSIT_QUARTER();
         DEPOSIT_DIME();
         DEPOSIT_DIME();

        vendingMachine.pressedButton("Candy");

        assertEquals(.05, vendingMachine.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoneyAndPushReturnCoinButtonIShouldGetAllOfMyMoneyBack() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();

        vendingMachine.pressedButton("Return Coin");

        assertEquals(.6, vendingMachine.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertMoneyAndPushReturnCoinButtonTheDisplayMessageShouldSayINSERTCOIN() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.pressedButton("Return Coin");

        assertEquals("INSERT COIN", vendingMachine.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheChipsButtonButChipsAreSoldOutTheDisplayMessageShouldReadSOLDOUT() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.setChipsInStock(0);
        vendingMachine.pressedButton("Chips");

        assertEquals("SOLD OUT", vendingMachine.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheChipsButtonButChipsAreSoldOut2xOnTheSecondPressIShouldSeeTheAmountIHaveDeposited() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.setChipsInStock(0);
        vendingMachine.pressedButton("Chips");
        vendingMachine.pressedButton("Chips");

        assertEquals("0.5", vendingMachine.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheColaButtonButColaAreSoldOutTheDisplayMessageShouldReadSOLDOUT() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.setColaInStock(0);
        vendingMachine.pressedButton("Cola");

        assertEquals("SOLD OUT", vendingMachine.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheColaButtonButColasAreSoldOut2xOnTheSecondPressIShouldSeeTheAmountIHaveDeposited() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();

        vendingMachine.setColaInStock(0);
        vendingMachine.pressedButton("Cola");
        vendingMachine.pressedButton("Cola");

        assertEquals("1.0", vendingMachine.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheCandyButtonButCandyIsSoldOutTheDisplayMessageShouldReadSOLDOUT() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        vendingMachine.setCandyInStock(0);
        vendingMachine.pressedButton("Candy");

        assertEquals("SOLD OUT", vendingMachine.getStateMessage());
    }

    @Test
    public void whenIInsertMoneyAndPushTheCandyButtonButCandyIsSoldOut2xOnTheSecondPressIShouldSeeTheAmountIHaveDeposited() {
        DEPOSIT_QUARTER();
        DEPOSIT_QUARTER();
        DEPOSIT_DIME();
        DEPOSIT_NICKEL();

        vendingMachine.setCandyInStock(0);
        vendingMachine.pressedButton("Candy");
        vendingMachine.pressedButton("Candy");

        assertEquals("0.65", vendingMachine.getStateMessage());
    }

    @Test
    public void whenMachineCanOnlyAcceptExactChange(){
        vendingMachine.setMoneyInMachine(0.0);

        assertEquals("EXACT CHANGE ONLY", vendingMachine.getStateMessage());
    }

}
