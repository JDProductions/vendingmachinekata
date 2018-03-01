public class ItemHandler {

    public static final int CHIPS_ITEM_ID = 1;
    public static final int COLA_ITEM_ID = 2;
    public static final int CANDY_ITEM_ID = 3;

    private double chipsPrice = 0.50;
    private int chipsInStock = 5;


    private final double colaPrice = 1.00;
    private int colaInStock = 5;

    public double getCandyPrice() {
        return candyPrice;
    }

    private final double candyPrice = 0.65;

    public int getCandyInStock() {
        return candyInStock;
    }

    public void setCandyInStock(int candyInStock) {
        this.candyInStock = candyInStock;
    }

    private int candyInStock = 5;


    public double getColaPrice() {
        return colaPrice;
    }


    public int getColaInStock() {
        return colaInStock;
    }

    public void setColaInStock(int colaInStock) {
        this.colaInStock = colaInStock;
    }


    public double getChipsPrice() {
        return chipsPrice;
    }


    public  void reduceItemInventory(int itemID){
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
        return chipsInStock;
    }

    public void setChipsInStock(int chipsInStock) {
        this.chipsInStock = chipsInStock;
    }



}
