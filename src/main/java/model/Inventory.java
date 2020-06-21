package model;

import lombok.Data;
import response.InventoryResponse;

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

    public boolean createInventory(Map<String, String> items) {

        Map<String, Integer> inventoryMap = new HashMap<>();

        for(Map.Entry<String, String> entry : items.entrySet()) {
            String item = entry.getKey();
            int qty = Integer.parseInt(entry.getValue());
            inventoryMap.put(item, qty);
        }
        inventory.putAll(inventoryMap);
        return true;
    }

    public InventoryResponse updateInventory(Map<String, Integer> items) {
        InventoryResponse inventoryResponse = new InventoryResponse();
        for (Map.Entry<String, Integer> entry : items.entrySet()) {
            String item = entry.getKey();
            int quantity = entry.getValue();
            if (!inventory.containsKey(item)) {
                inventoryResponse.setPrepared(false);
                inventoryResponse.setItemUnavailable(item);
                return inventoryResponse;
            }
            else{

                int updateValue = inventory.get(item) - quantity;
                if (updateValue < 0) {
                    unavailableItems.add(item);
                    inventoryResponse.setPrepared(false);
                    inventoryResponse.setInSufficient(true);
                    inventoryResponse.setItemUnavailable(item);
                    return inventoryResponse;
                }
                inventory.put(item, updateValue);
                inventoryResponse.setPrepared(true);
            }
        }
        return inventoryResponse;
    }
}
