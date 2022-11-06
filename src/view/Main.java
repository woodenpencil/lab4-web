package view;

import model.entities.Client;
//import model.entities.port.LockBank;
import model.entities.port.SynchronizedBank;
import model.entities.port.Bank;
import model.thread.ServeClientThread;
import model.thread.WatchDog;

import java.util.Random;

public class Main {

    private static void Work(SynchronizedBank bank) {
        Thread[] threads = new Thread[20];
        Random random = new Random();

        System.out.println(bank);
        for (int i = 0; i < 7; i++) {
            int currentAmountOfByn = random.nextInt(50);
            int bynToWithdraw = random.nextInt(50);
            int bynToDeposit = random.nextInt(50);
            int currentAmountOfUsd = random.nextInt(50);
            int usdToWithdraw = random.nextInt(50);
            int usdToDeposit = random.nextInt(50);
            int bynToChange = 0;
            int usdToChange = 0;
            if(i==0) {
            	bynToChange = random.nextInt(50);
            }else if(i==1) {
                usdToChange = random.nextInt(50);
            }
            else if(i%2==0) {
            	bynToWithdraw = 0;
            	usdToWithdraw = 0;
            }else {
            	bynToDeposit = 0;
            	usdToDeposit = 0;
            }
            Client client = new Client(bynToWithdraw, bynToDeposit, currentAmountOfByn, currentAmountOfUsd, usdToWithdraw, usdToDeposit, bynToChange, usdToChange, i + 1);
            System.out.println(client);
            threads[i] = new Thread(new ServeClientThread(bank, client));
        }

        threads[7] = new Thread(new WatchDog(bank));
        // run
        for (int i = 0; i < 8; i++) {
            threads[i].start();
        }

        // wait
        //for (int i = 0; i < 5; i++) {
        //    while (threads[i].isAlive()) {
        //    }
        //}
    }

    public static void main(String[] args) {
        System.out.println("*************\nSynchronized bank");
        Work(new SynchronizedBank(2, 1000, 1000));
        //System.out.println("\n*************\nLock variant");
        //Work(new LockBank(2, 1000));
    }

}
