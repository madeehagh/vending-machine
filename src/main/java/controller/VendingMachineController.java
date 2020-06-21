package controller;

import model.Beverages;
import service.VendingMachine;
import service.VendingMachineImpl;

import java.util.Map;

public class VendingMachineController {
    VendingMachine vendingMachine = new VendingMachineImpl();


    public void createOutlets(String n) {
        vendingMachine.setNoOfUnit(Integer.parseInt(n));
    }

    public void createInventory(Map<String, String> inventories) {
       vendingMachine.createInventory(inventories);
    }

    public void placeOrder(Beverages beverages) {
        vendingMachine.placeOrder(beverages);
    }
}
