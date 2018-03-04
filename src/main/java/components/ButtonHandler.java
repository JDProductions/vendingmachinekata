package components;

public class ButtonHandler {
    private boolean wasChipButtonPressed = false;
    private boolean wasColaButtonPressed = false;
    private boolean wasCandyButtonPressed = false;
    private boolean wasReturnButtonPressed = false;

    private int soldOutButtonCounter = 0;

    public boolean wasChipButtonPressed() {
        return wasChipButtonPressed;
    }

    public void setWasChipButtonPressed(boolean wasChipButtonPressed) {
        this.wasChipButtonPressed = wasChipButtonPressed;
    }

    public boolean wasColaButtonPressed() {
        return wasColaButtonPressed;
    }

    public void setWasColaButtonPressed(boolean wasColaButtonPressed) {
        this.wasColaButtonPressed = wasColaButtonPressed;
    }

    public boolean wasCandyButtonPressed() {
        return wasCandyButtonPressed;
    }

    public void setWasCandyButtonPressed(boolean wasCandyButtonPressed) {
        this.wasCandyButtonPressed = wasCandyButtonPressed;
    }

    public boolean wasReturnButtonPressed() {
        return wasReturnButtonPressed;
    }

    public void setWasReturnButtonPressed(boolean wasReturnButtonPressed) {
        this.wasReturnButtonPressed = wasReturnButtonPressed;
    }

    public int getSoldOutButtonCounter() {
        return this.soldOutButtonCounter;
    }

    public void setSoldOutButtonCounter(int soldOutButtonCounter) {
        this.soldOutButtonCounter = soldOutButtonCounter;
    }

    public void incrementButtonCounter() {
        this.soldOutButtonCounter++;
    }

    public void resetButtonCounter() {
        this.setSoldOutButtonCounter(0);
    }
}
