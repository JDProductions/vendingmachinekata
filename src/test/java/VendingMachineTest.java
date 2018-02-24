import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VendingMachineTest {

    private final VendingMachine vendingMachine = new VendingMachine();

    @Test
    public void whenVendingMachineIsIdleDisplayMessageShouldReadInsertCoin() {
        assertEquals("INSERT COIN", vendingMachine.getStateMessage());

    }

    @Test
    public void whenIInsertAValidCoinTheDisplayMessageShouldReadTheTotalDepositedAmount() {
        double quarter = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1, 1);
        double dime = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(2, 2);


        assertEquals("0.35",vendingMachine.getStateMessage());
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfAQuarter() {
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1, 1);

        assertEquals(0.25, coinAmount,0);
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfADime() {
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(2, 2);

        assertEquals(0.10, coinAmount,0);
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfANickel() {
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(3, 3);

        assertEquals(0.05, coinAmount,0);
    }

    @Test
    public void whenIInsertAPennyTheCoinReturnShouldReturnTheValueOfAPennyWhichWasInserted() {
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(4,4);
        vendingMachine.getCoinReturnAmount();

        assertEquals(coinAmount, vendingMachine.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDepositedQuarters() {
        // I know these variable aren't being used but I added them for readability for the Artisan.
        double quarter = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double quarter2 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);


        assertEquals(0.50,vendingMachine.getTotalAmountDeposited());

    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDeposited() {
        // I know these variable aren't being used but I added them for readability for the Artisan.
        double quarter = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double quarter2 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double dime = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(2,2);
        double nickel = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(3,3);
        // Notice the penny is not added to the total deposited.
        double penny = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(4,4);


        assertEquals(0.65,vendingMachine.getTotalAmountDeposited());

    }

    @Test
    public void whenIInsertEnoughMoneyForChipsIShouldDispensedChipsAndTheDisplayMessageShouldReadTHANKYOU(){
        double quarter1 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double quarter2 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);

        vendingMachine.pressChipButton();


        assertEquals("THANK YOU",vendingMachine.getStateMessage());

    }

    @Test
    public void whenIInsertEnoughMoneyForChipsAndPressChipButtonTheChipsShouldBeDispensed() {
        double quarter1 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double quarter2 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);

        vendingMachine.pressChipButton();

        // There are 5 bags of chips in stock, I am now expecting 4 bags to be in stock meaning one of the bags has been dispensed.
        assertEquals(4, vendingMachine.getChipsInStock());
    }

    @Test
    public void whenIInsertTheExactAmountNeededForChipsAndPressChipButtonTheTotalAmountDepositedShouldBeZero() {
        double quarter1 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double quarter2 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);

        vendingMachine.pressChipButton();

        // There are 5 bags of chips in stock, I am now expecting 4 bags to be in stock meaning one of the bags has been dispensed.
        assertEquals(0.0, vendingMachine.getTotalAmountDeposited());
    }

}
