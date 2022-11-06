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
    public void setBynAvailable(int val);
    public void setUsdAvailable(int val);
    
    public int getTreasureByn();
    public int getTreasureUsd();
    public void setTreasureByn(int val);
    public void setTreasureUsd(int val);
    
    public boolean getFillUsd();
    public boolean getFillByn();
    public void setFillUsd(boolean val);
    public void setFillByn(boolean val);
    
    public boolean getTakeUsd();
    public boolean getTakeByn();
    public void setTakeUsd(boolean val);
    public void setTakeByn(boolean val);
    
    public int getMaxUsd();
    public int getMaxByn();
	
}
