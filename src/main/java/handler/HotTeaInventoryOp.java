package handler;

import request.HotTea;
import response.InventoryResponse;
import util.Inventory;

import java.util.HashMap;
import java.util.Map;

public class HotTeaInventoryOp implements InventryOperation{
    HotTea hotTea;
    Inventory inventory;

    public HotTeaInventoryOp(HotTea hotTea) {
        this.hotTea = hotTea;
        inventory = new Inventory();
    }

    @Override
    public InventoryResponse updateItem() {
        Map<String, Integer> item = new HashMap<>();
        item.put("hot_water" , Integer.parseInt(hotTea.hot_water));
        item.put("hot_milk", Integer.parseInt(hotTea.hot_milk));
        item.put("ginger_syrup", Integer.parseInt(hotTea.ginger_syrup));
        item.put("sugar_syrup", Integer.parseInt(hotTea.sugar_syrup));
        item.put("tea_leaves_syrup", Integer.parseInt(hotTea.tea_leaves_syrup));
        return inventory.updateInventory(item);
    }
}
