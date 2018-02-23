import org.junit.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VendingMachineTest {
    @Test
    public void whenVendingMachineIsIdleDisplayMessageShouldReadInsertCoin() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("INSERT COIN", vendingMachine.getStateMessage());

    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfAQuarter() {
        VendingMachine vendingMachine = new VendingMachine();
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1, 1);

        Assert.assertEquals(0.25, coinAmount,0);
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfADime() {
        VendingMachine vendingMachine = new VendingMachine();
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(2, 2);

        Assert.assertEquals(.10, coinAmount,0);
    }

    @Test
    public void whenInsertACoinDetermineTheValueBasedOffOfWeightAndDiameterAndReturnValueOfANickel() {
        VendingMachine vendingMachine = new VendingMachine();
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(3, 3);

        Assert.assertEquals(.05, coinAmount,0);
    }

    @Test
    public void whenIInsertAPennyTheCoinReturnShouldReturnTheValueOfAPennyWhichWasInserted() {
        VendingMachine vendingMachine = new VendingMachine();
        double coinAmount = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(4,4);
        vendingMachine.getCoinReturnAmount();

        assertEquals(coinAmount, vendingMachine.getCoinReturnAmount());
    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDepositedQuarters() {
        VendingMachine vendingMachine = new VendingMachine();
        // I know these variable aren't being used but I added them for readability for the Artisan.
        double quarter = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double quarter2 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);


        assertEquals(.50,vendingMachine.getTotalAmountDeposited());

    }

    @Test
    public void whenIInsertCoinsTheTotalDepositedShouldBeValueOfAllCoinsDeposited() {
        VendingMachine vendingMachine = new VendingMachine();
        // I know these variable aren't being used but I added them for readability for the Artisan.
        double quarter = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double quarter2 = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(1,1);
        double dime = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(2,2);
        double nickel = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(3,3);
        double penny = vendingMachine.determineCoinValueBasedOnWeightAndSizeByDiameter(4,4);


        assertEquals(.65,vendingMachine.getTotalAmountDeposited());

    }
}
