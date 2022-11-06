package model.thread;

import model.entities.Client;
import model.entities.port.Bank;



/**
 * Class-Thread for Client
 */
public class ServeClientThread implements Runnable{

    /**
     * private field client
     */
    private Client client;

    /**
     * private field bank
     */
    private Bank bank;

    /**
     * Constructor with parameters
     * @param bank - bank
     * @param client - client in this bank
     */
    public ServeClientThread(Bank bank, Client client) {
        this.client = client;
        this.bank = bank;
    }

    /**
     * Method run for thread
     */
    @Override
    public void run() {
        // bank
        if (!bank.bank()) {
            System.out.println("There are no vacant cashiers to accept " + client);
            return;
        }

        // try deposit byn to bank
        if (client.hasByn()) {
            System.out.println("Client " + client.getNumber() + " try deposit " + client.getBynToDeposit() + " byn to bank");
            int deposit = Math.min(client.getCurrentAmountOfByn(), client.getBynToDeposit());
            if (bank.depositByn(deposit)) {
                client.setCurrentAmountOfByn(client.getCurrentAmountOfByn()-deposit);
            }
            System.out.println("********Current amount of byn in bank: " + bank.getBynAvailable());
            System.out.println("********Current treasure of byn in bank: " + bank.getTreasureByn());
            
            showClientsByn();
            
        }
        // try deposit usd to bank
        if (client.hasUsd()) {
            System.out.println("Client " + client.getNumber() + " try deposit " + client.getUsdToDeposit() + " usd to bank");
            int deposit = Math.min(client.getCurrentAmountOfUsd(), client.getUsdToDeposit());
            if (bank.depositUsd(deposit)) {
                client.setCurrentAmountOfUsd(client.getCurrentAmountOfUsd()-deposit);
            }
            System.out.println("********Current amount of usd in bank: " + bank.getUsdAvailable());
            System.out.println("********Current treasure of usd in bank: " + bank.getTreasureUsd());
            
            showClientsUsd();
        }
        
        // client try to withdraw byn from bank
        System.out.println(client.getNumber() + " client tries to withdraw byn");
        int money = bank.withdrawByn(client.getBynToWithdraw());
        client.withdrawByn(money);
        System.out.println("Client " + client.getNumber() + " withdrawed " + money + " byn");
        System.out.println("********Current available byn in bank: " + bank.getBynAvailable());
        showClientsByn();

        
     // client try to withdraw money from bank
        System.out.println(client.getNumber() + " client tries to withdraw usd");
        money = bank.withdrawUsd(client.getUsdToWithdraw());
        client.withdrawUsd(money);
        System.out.println("Client " + client.getNumber() + " withdrawed " + money + " usd");
        System.out.println("********Current available usd in bank: " + bank.getUsdAvailable());
        showClientsUsd();
        
        
        bank.leave();
        System.out.println("Client " + client.getNumber() + " leave bank");
        System.out.println("********Current byn in bank: " + bank.getBynAvailable());
        System.out.println("********Current usd in bank: " + bank.getUsdAvailable());

    }

    /**
     * Show-method for current capacity of client
     */
    public void showClientsByn() {
        System.out.println("________Current byn of client " + client.getNumber() + ": " + client.getCurrentAmountOfByn());
    }
    public void showClientsUsd() {
        System.out.println("________Current usd of client " + client.getNumber() + ": " + client.getCurrentAmountOfUsd());
    }

}
