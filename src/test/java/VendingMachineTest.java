import org.junit.Test;

import static junit.framework.Assert.assertEquals;

public class VendingMachineTest {
    @Test
    public void whenVendingMachineIsIdleDisplayMessageShouldReadInsertCoin() {
        VendingMachine vendingMachine = new VendingMachine();
        assertEquals("INSERT COIN", vendingMachine.getStateMessage());

    }
}
