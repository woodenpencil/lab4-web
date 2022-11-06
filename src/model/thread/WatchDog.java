package model.thread;

import model.entities.Client;
import model.entities.port.Bank;
//import model.entities.port.SynchronizedBank;

public class WatchDog implements Runnable{
	public Bank bank;
	public WatchDog(Bank bank) {
		this.bank = bank;
	}
	@Override
    public void run() {
		while(true) {
			if(bank.getFillByn()) {
				int fill = Math.min(bank.getMaxByn() - bank.getBynAvailable(), bank.getTreasureByn());
				bank.setBynAvailable(bank.getBynAvailable()+fill);
				bank.setFillByn(false);
				bank.setTreasureByn(-fill);
			}
			if(bank.getFillUsd()) {
				int fill = Math.min(bank.getMaxUsd() - bank.getUsdAvailable(),bank.getTreasureUsd());
				bank.setUsdAvailable(fill);
				bank.setFillUsd(false);
				bank.setTreasureUsd(-fill);
			}
			if(bank.getTakeByn()) {
				int take = bank.getBynAvailable();
				bank.setBynAvailable(-take);
				bank.setTreasureByn(take);
				bank.setTakeByn(false);
			}
			if(bank.getTakeUsd()) {
				int take = bank.getUsdAvailable();
				bank.setUsdAvailable(-take);
				bank.setTreasureUsd(take);
				bank.setTakeUsd(false);
			}
		}
	}
}
