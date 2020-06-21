package handler;

import request.HotCoffee;
import response.InventoryResponse;
import util.Inventory;

import java.util.HashMap;
import java.util.Map;

public class HotCoffeeInventryOp implements InventryOperation{

    HotCoffee hotCoffee;
    Inventory inventory;

    public HotCoffeeInventryOp(HotCoffee hotCoffee) {
        this.hotCoffee = hotCoffee;
        inventory = new Inventory();
    }

    @Override
    public InventoryResponse updateItem() {
        Map<String, Integer> item = new HashMap<>();
        item.put("hot_water" , Integer.parseInt(hotCoffee.hot_water));
        item.put("hot_milk", Integer.parseInt(hotCoffee.hot_milk));
        item.put("ginger_syrup", Integer.parseInt(hotCoffee.ginger_syrup));
        item.put("sugar_syrup", Integer.parseInt(hotCoffee.sugar_syrup));
        item.put("tea_leaves_syrup", Integer.parseInt(hotCoffee.tea_leaves_syrup));
        return inventory.updateInventory(item);
    }
}
