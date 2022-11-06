package model.thread;

import model.entities.Client;
import model.entities.port.Bank;
import model.entities.port.SynchronizedBank;

public class WatchDog implements Runnable{
	private SynchronizedBank bank;
	public WatchDog(SynchronizedBank bank) {
		this.bank = bank;
	}
	@Override
    public void run() {
		while(true) {
			if(fillByn)
		}
	}
}
