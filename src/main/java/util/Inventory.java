package util;

import lombok.Data;
import model.VendingMachineResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Data
public class Inventory {
    private static Map<String, Integer> inventory = new HashMap<>();
    private static List<String> unavailableItems = new ArrayList<>();
    private int noOfUnits;

    public Inventory() {}

    public void createInventory(Map<String, String> items) {

        Map<String, Integer> inventoryMap = new HashMap<>();

        for(Map.Entry<String, String> entry : items.entrySet()) {
            String item = entry.getKey();
            int qty = Integer.parseInt(entry.getValue());
            inventoryMap.put(item, qty);
        }
        inventory.putAll(inventoryMap);
    }

    public VendingMachineResponse updateInventory(Map<String, Integer> items) {
        VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            if (!inventory.containsKey(item)) {
                vendingMachineResponse.setPrepared(false);
                vendingMachineResponse.setItemUnavailable(item);
                return vendingMachineResponse;
            }
            else{

                int updateValue = inventory.get(item) - quantity;
                if (updateValue < 0) {
                    unavailableItems.add(item);
                    vendingMachineResponse.setPrepared(false);
                    vendingMachineResponse.setInSufficient(true);
                    vendingMachineResponse.setItemUnavailable(item);
                    return vendingMachineResponse;
                }
                inventory.put(item, updateValue);
            }
        }
        vendingMachineResponse.setPrepared(true);
        return vendingMachineResponse;
    }
}
