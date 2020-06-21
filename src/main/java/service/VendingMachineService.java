package service;

import request.Beverages;
import response.VendingMachineResponseWrapper;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public interface VendingMachineService {

    public boolean setNoOfUnit(int n);
    public VendingMachineResponseWrapper placeOrder(Beverages beverages) throws ExecutionException, InterruptedException;
    public boolean createInventory(Map<String, String> inventories);

}
