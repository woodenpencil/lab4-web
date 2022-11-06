package model.entities.port;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockBank implements Bank {

    private int berthCount;
    private int maxCapacity;
    private int currentCapacity = 0;
    private int shipsInBerths = 0;
    private Lock lock = new ReentrantLock();

    public LockBank(int berthCount, int maxCapacity) {
        if (berthCount < 0)
            throw new IllegalArgumentException("Invalid value of count of berths.");
        this.berthCount = berthCount;

        if (maxCapacity < 0)
            throw new IllegalArgumentException("Invalid value of max capacity of port.");
        this.maxCapacity = maxCapacity;
    }

    @Override
    public int load(int max) {
        int retries = 100;
        while (retries-- > 0) {
            int cargo = tryLoad(max);
            if (cargo > 0) {
                return cargo;
            }
        }
        return 0;
    }

    @Override
    public boolean depositByn(int capacity) {
        int retries = 100;
        while (retries-- > 0) {
            if (tryPlace(capacity)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean bank() {
        while (!this.tryPort()) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void leave() {
        lock.lock();
        try {
            shipsInBerths--;
        } finally {
            lock.unlock();
        }
    }

    private boolean tryPort() {
        lock.lock();
        try {
            if (shipsInBerths < berthCount) {
                shipsInBerths++;
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public int getCurrentCapacity() {
        return currentCapacity;
    }

    private int tryLoad(int max) {
        lock.lock();
        try {
            if (currentCapacity >= max) {
                currentCapacity -= max;
                return max;
            }
            if (max > currentCapacity) {
                int weight = currentCapacity;
                currentCapacity = 0;
                return weight;
            }
            return 0;
        } finally {
            lock.unlock();
        }
    }

    private boolean tryPlace(int weight) {
        lock.lock();
        try {
            if (currentCapacity + weight > maxCapacity) {
                return false;
            }
            currentCapacity += weight;
            return true;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public String toString() {
        String str = "\n=============Bank=============\nCount of berths: " + berthCount + "\nMax capacity: " + maxCapacity;
        return str;
    }
}
