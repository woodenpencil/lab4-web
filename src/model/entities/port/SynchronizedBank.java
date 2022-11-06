package model.entities.port;

public class SynchronizedBank implements Bank {

	public int cashierCount;
    public int maxBynAvailable;
    public int maxUsdAvailable;
    public int bynAvailable = 0;
    public int usdAvailable = 0;
    public volatile boolean fillUsd;
    public volatile boolean fillByn;
    public volatile boolean takeUsd;
    public volatile boolean takeByn;
    public volatile int treasureByn;
    public volatile int treasureUsd;
    
    
    
    private int clientsBeingServed = 0;
    

    public SynchronizedBank(int cashierCount, int maxByn, int maxUsd, int treasureByn, int treasureUsd) {
        this.cashierCount = cashierCount;
        this.maxBynAvailable = maxByn;
        this.maxUsdAvailable = maxUsd;
        this.treasureByn = treasureByn;
        this.treasureUsd = treasureUsd;
        
    }

    public int withdrawByn(int amount) {
        int retries = 100;

        while (retries-- > 0) {
            int money = tryWithdrawByn(amount);
            if (money > 0) {
                return money;
            }
        }
        return 0;
    }

    public int withdrawUsd(int amount) {
        int retries = 100;

        while (retries-- > 0) {
            int money = tryWithdrawUsd(amount);
            if (money > 0) {
                return money;
            }
        }
        return 0;
    }
    public boolean depositByn(int amount) {
        int retries = 100;
        while (retries-- > 0) {
            if (tryDepositByn(amount)) {
                return true;
            }
        }
        return false;
    }
    public boolean depositUsd(int amount) {
        int retries = 100;
        while (retries-- > 0) {
            if (tryDepositUsd(amount)) {
                return true;
            }
        }
        return false;
    }
    public boolean bank() {
        while (!this.tryBank()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return true;
    }

    public synchronized void leave() {
        clientsBeingServed--;
    }

    private synchronized boolean tryBank() {
        if (clientsBeingServed < cashierCount) {
            clientsBeingServed++;
            return true;
        }
        return false;
    }

    private synchronized int tryWithdrawByn(int max) {
        if (bynAvailable >= max) {
            bynAvailable -= max;
            return max;
        }
        if(maxBynAvailable<=max) {
        	fillByn = true;
        	return 0;
        }
        else {
            int byn = bynAvailable;
            bynAvailable = 0;
            return byn;
        }
    }
    private synchronized int tryWithdrawUsd(int max) {
        if (usdAvailable >= max) {
            usdAvailable -= max;
            return max;
        }
        if (max > usdAvailable) {
            int usd = usdAvailable;
            usdAvailable = 0;
            return usd;
        }
        return 0;
    }
    @Override
    public int getBynAvailable() {
        return bynAvailable;
    }
    @Override
    public int getUsdAvailable() {
        return usdAvailable;
    }
    @Override
    public void setBynAvailable(int val) {
    	bynAvailable+=val;
    }
    @Override
    public void setUsdAvailable(int val) {
    	usdAvailable+=val;
    }
    
    
    @Override
    public int getTreasureByn() {
        return treasureByn;
    }
    @Override
    public int getTreasureUsd() {
        return treasureUsd;
    }
    @Override
    public void setTreasureByn(int val) {
        treasureByn+=val;
    }
    @Override
    public void setTreasureUsd(int val) {
        treasureUsd+=val;
    }
    
    
    @Override
    public boolean getFillUsd() {
        return fillUsd;
    }
    @Override
    public boolean getFillByn() {
        return fillByn;
    }
	@Override
    public void setFillUsd(boolean val) {
        fillUsd=val;
    }
    @Override
    public void setFillByn(boolean val) {
        fillByn=val;
    }
    
    @Override
    public boolean getTakeUsd() {
        return takeUsd;
    }
    @Override
    public boolean getTakeByn() {
        return takeByn;
    }
	@Override
    public void setTakeUsd(boolean val) {
        takeUsd=val;
    }
    @Override
    public void setTakeByn(boolean val) {
        takeByn=val;
    }
    
    
    @Override
    public int getMaxUsd() {
        return maxUsdAvailable;
    }
    @Override
    public int getMaxByn() {
        return maxBynAvailable;
    }

    private synchronized boolean tryDepositByn(int amount) {
        if (bynAvailable + amount > maxBynAvailable) {
            return false;
        }
        bynAvailable += amount;
        return true;
    }
    private synchronized boolean tryDepositUsd(int amount) {
        if (usdAvailable + amount > maxUsdAvailable) {
            return false;
        }
        usdAvailable += amount;
        return true;
    }

    @Override
    public String toString() {
        String str = "\n=============Bank=============\nCount of cashiers: " + cashierCount + "\nMax byn available: " + maxBynAvailable+"\nMax usd available: " + maxUsdAvailable;
        return str;
    }
}
