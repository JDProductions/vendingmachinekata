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
}
