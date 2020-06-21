package handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import controller.VendingMachineController;
import model.Beverages;
import util.VendingUtil;

import java.util.Map;

public class VendingMachineHandler {
    VendingMachineController vendingMachineController;

    public VendingMachineHandler() {
        this.vendingMachineController = new VendingMachineController();
    }

    /**
     * This method initialize number of outlets from which beverages can be served.
     * @param outlets
     */
    public  void initializeSystem(JsonNode outlets) {
        if (null != outlets) {
            Map<String, String> result = VendingUtil.convertJsonNodetoMap(outlets);
            if (null != result)
                vendingMachineController.createOutlets(result.get("count_n"));
        }
    }

    /**
     * This method initialize inventory of items.
     * @param total_items_quantity
     */
    public void initializeInventory(JsonNode total_items_quantity) {
        if (null != total_items_quantity) {
            Map<String, String> result = VendingUtil.convertJsonNodetoMap(total_items_quantity);
            if (null != result)
                vendingMachineController.createInventory(result);
        }
    }

    /**
     * This method perform order placement operation.
     * @param beverages
     */
    public void placeOrder(Beverages beverages) {
        if (null != beverages) {
            vendingMachineController.placeOrder(beverages);
        }
    }
}
