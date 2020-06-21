package handler;

import model.BlackTea;
import model.VendingMachineResponse;
import util.Inventory;

import java.util.HashMap;
import java.util.Map;

public class BlackTeaInventoryOp implements InventryOperation {

    BlackTea blackTea;
    Inventory inventory;

    public BlackTeaInventoryOp(BlackTea blackTea) {
        this.blackTea = blackTea;
        inventory = new Inventory();
    }

    @Override
    public VendingMachineResponse updateItem() {
        Map<String, Integer> item = new HashMap<>();
        item.put("hot_water" , Integer.parseInt(blackTea.hot_water));
        item.put("ginger_syrup", Integer.parseInt(blackTea.hot_water));
        item.put("sugar_syrup", Integer.parseInt(blackTea.hot_water));
        item.put("tea_leaves_syrup", Integer.parseInt(blackTea.hot_water));
        return inventory.updateInventory(item);
    }
}
