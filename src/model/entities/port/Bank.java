package model.entities.port;

public interface Bank {

    /**
     * Bank load cargo to ship
     * @param max - capacity
     * @return - loaded capacity
     */
    public int withdrawByn(int max);
    public int withdrawUsd(int max);
    /**
     * Client place some cargo to port
     * @param capacity
     * @return - capacity
     */
    public boolean depositByn(int capacity);
    public boolean depositUsd(int capacity);
    /**
     * Client enter to one berth in port
     * @return - true if ship enter, false if not
     */
    public boolean bank();

    /**
     * Client leave port
     */
    public void leave();

    /**
     * Get-method for current capacity of port
     * @return
     */
    public int getBynAvailable();

    public int getUsdAvailable();
}
