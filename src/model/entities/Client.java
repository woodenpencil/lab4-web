package model.entities;

public class Client {

    /**
     * max capacity of ship
     */
    private int bynToWithdraw;
    private int bynToDeposit;
    
    private int usdToWithdraw;
    private int usdToDeposit;
    
    private int bynToChange;
    private int usdToChange;
    
    
    /**
     * private field of current capacity of ship
     */
    private int currentAmountOfByn;
    private int currentAmountOfUsd;

    /**
     * private field of number of ship
     */
    private int number;

    /**
     * Constructor with parameters
     * @param maxCapacity - max capacity
     * @param currentAmountOfByn - current capacity
     * @param number - number of ship
     */
    public Client(int bynToWithdraw, int bynToDeposit, int currentAmountOfByn, int currentAmountOfUsd, int usdToWithdraw, int usdToDeposit, int bynToChange, int usdToChange, int number) {
    	this.bynToWithdraw = bynToWithdraw;
    	this.bynToDeposit = bynToDeposit;
    	this.usdToWithdraw = usdToWithdraw;
    	this.usdToDeposit = usdToDeposit;
        this.currentAmountOfByn = currentAmountOfByn;
        this.currentAmountOfUsd = currentAmountOfUsd;
        this.bynToChange = bynToChange;
        this.usdToChange = usdToChange;

        if (number < 0)
            throw new IllegalArgumentException("Invalid value for number of client!");
        this.number = number;
    }

    /**
     * Get-method for number of ship
     * @return number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Get-Method for current capacity
     * @return - current capacity of ship
     */
    public int getCurrentAmountOfByn() {
        return currentAmountOfByn;
    }
    public int getCurrentAmountOfUsd() {
        return currentAmountOfUsd;
    }
    
    public int getBynToDeposit() {
        return bynToDeposit;
    }
    public int getUsdToDeposit() {
        return usdToDeposit;
    }
    public int getBynToWithdraw() {
        return bynToWithdraw;
    }
    public int getUsdToWithdraw() {
        return usdToWithdraw;
    }
    
    /**
     * Set-method for current capacity
     * @param currentAmountOfByn - current capacity
     */
    public void setCurrentAmountOfByn(int currentAmountOfByn) {
        if (currentAmountOfByn < 0)
            throw new IllegalArgumentException("Invalid value for current amount of byn for client!");

        this.currentAmountOfByn = currentAmountOfByn;
    }
    public void setCurrentAmountOfUsd(int currentAmountOfUsd) {
        if (currentAmountOfUsd < 0)
            throw new IllegalArgumentException("Invalid value for current amount of usd for client!");

        this.currentAmountOfUsd = currentAmountOfUsd;
    }
    /**
     * Method that check сargo in ship
     * @return
     */
    public boolean hasByn() {
        return currentAmountOfByn > 0;
    }
    public boolean hasUsd() {
        return currentAmountOfUsd > 0;
    }
    /**
     * Add cargo to ship
     * @param capacity - try to add this capacity
     */
    public void withdrawByn(int money) {
        currentAmountOfByn += money;
    }
    public void withdrawUsd(int money) {
        currentAmountOfUsd += money;
    }
    
    @Override
    public String toString() {
        String str = "\n********\nClient " + number + ":\nCurrent amount of usd: " + currentAmountOfUsd+":\nCurrent amount of byn: " + currentAmountOfByn+":\nWants to deposit byn: "+bynToDeposit+":\nWants to deposit usd: "+usdToDeposit+":\nWants to withdraw byn: "+bynToWithdraw+":\nWants to withdraw usd: "+usdToWithdraw;
        return str;
    }
}
