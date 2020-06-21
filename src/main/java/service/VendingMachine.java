package service;

import model.Beverages;

import java.util.Map;

public interface VendingMachine {
    public void setNoOfUnit(int n);
    public void placeOrder(Beverages beverages);
    public void createInventory(Map<String, String> inventories);
}
