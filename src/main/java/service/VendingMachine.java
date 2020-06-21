package service;

import request.Beverages;
import response.VendingMachineResponseWrapper;

import java.util.Map;

public interface VendingMachine {
    public boolean setNoOfUnit(int n);
    public VendingMachineResponseWrapper placeOrder(Beverages beverages);
    public boolean createInventory(Map<String, String> inventories);
}
