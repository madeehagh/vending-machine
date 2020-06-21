package handler;

import model.GreenTea;
import model.VendingMachineResponse;
import util.Inventory;

import java.util.HashMap;
import java.util.Map;

public class GreenTeaInventoryOp implements InventryOperation {
    GreenTea greenTea;
    Inventory inventory;
    public GreenTeaInventoryOp(GreenTea greenTea) {
        this.greenTea = greenTea;
        inventory = new Inventory();
    }

    @Override
    public VendingMachineResponse updateItem() {
        Map<String, Integer> item = new HashMap<>();
        item.put("hot_water" , Integer.parseInt(greenTea.hot_water));
        item.put("ginger_syrup", Integer.parseInt(greenTea.ginger_syrup));
        item.put("sugar_syrup", Integer.parseInt(greenTea.sugar_syrup));
        item.put("green_mixture", Integer.parseInt(greenTea.green_mixture));
        return inventory.updateInventory(item);
    }
}
