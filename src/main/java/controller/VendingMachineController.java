package controller;

import com.fasterxml.jackson.databind.JsonNode;
import request.Beverages;
import response.InventoryResponse;
import response.VendingMachineResponse;
import response.VendingMachineResponseWrapper;
import service.VendingMachineService;
import service.VendingMachineServiceImpl;
import util.VendingUtil;

import java.util.Map;
import java.util.concurrent.ExecutionException;

public class VendingMachineController {

    VendingMachineService vendingMachineService = new VendingMachineServiceImpl();

    public VendingMachineController() {}

    /**
     * This method initialize number of outlets from which beverages can be served.
     * @param outlets
     */
    public boolean initializeSystem(JsonNode outlets) {
        if (!VendingUtil.isObjectNullOrEmpty(outlets)) {
            Map<String, String> result = VendingUtil.convertJsonNodetoMap(outlets);
            int count = 0;
            if (null != result)
                count = Integer.parseInt(result.get("count_n"));
                return vendingMachineService.setNoOfUnit(count);
        }
        return false;
    }

    /**
     * This method initialize inventory of items.
     * @param total_items_quantity
     */
    public boolean initializeInventory(JsonNode total_items_quantity) {
        if (!VendingUtil.isObjectNullOrEmpty(total_items_quantity)) {
            Map<String, String> result = VendingUtil.convertJsonNodetoMap(total_items_quantity);
            if (null != result)
                return vendingMachineService.createInventory(result);
        }
        return false;
    }

    /**
     * This method perform order placement operation.
     * @param beverages
     */
    public void placeOrder(Beverages beverages) throws ExecutionException, InterruptedException {
        if (null != beverages) {
            VendingMachineResponseWrapper vendingMachineResponseWrapper = vendingMachineService.placeOrder(beverages);
            processResponse(vendingMachineResponseWrapper);
        }
    }

    private void processResponse(VendingMachineResponseWrapper vendingMachineResponseWrapper) {

        if (null == vendingMachineResponseWrapper)
            System.out.println("Server error occurred");

        for (VendingMachineResponse vendingMachineResponse : vendingMachineResponseWrapper.getVendingMachineResponseList()) {
                if (null != vendingMachineResponse) {
                    InventoryResponse inventoryResponse = vendingMachineResponse.getInventoryResponse();
                    String item = vendingMachineResponse.getItem();
                    if (inventoryResponse.isPrepared()) {
                        System.out.println(item + " is prepared");
                    } else if (inventoryResponse.isInSufficient()){
                        System.out.println(item + " can not be prepared because " +
                                inventoryResponse.getItemUnavailable() + " is not sufficient");
                    } else {
                        System.out.println(item + " can not be prepared because " +
                                inventoryResponse.getItemUnavailable() + " is not available");
                    }
                }
        }
    }
}
