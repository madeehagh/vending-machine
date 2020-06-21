package service;

import enums.Items;
import handler.BlackTeaInventoryOp;
import handler.GreenTeaInventoryOp;
import handler.HotCoffeeInventryOp;
import handler.HotTeaInventoryOp;
import request.Beverages;
import response.InventoryResponse;
import response.VendingMachineResponse;
import response.VendingMachineResponseWrapper;
import model.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class VendingMachineImpl implements  VendingMachine{

    Inventory inventory;
    HotTeaInventoryOp hotTeaInventoryOp;
    HotCoffeeInventryOp hotCoffeeInventryOp;
    BlackTeaInventoryOp blackTeaInventoryOp;
    GreenTeaInventoryOp greenTeaInventoryOp;

    public VendingMachineImpl(){
        inventory = new Inventory();
    }

    @Override
    public boolean createInventory(Map<String, String> inventories) {
        return inventory.createInventory(inventories);
    }

    @Override
    public boolean setNoOfUnit(int n) {
        inventory.setNoOfUnits(n);
        return true;
    }

    @Override
    public VendingMachineResponseWrapper placeOrder(Beverages beverages) {
        VendingMachineResponseWrapper vendingMachineResponseWrapper = new VendingMachineResponseWrapper();
        List<VendingMachineResponse> vendingMachineResponses = new ArrayList<>();

        if (null != beverages.getHot_tea()) {
            InventoryResponse inventoryResponse = prepareHotTea(beverages);
            VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
            vendingMachineResponse.setItem(Items.HOT_TEA.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
            vendingMachineResponses.add(vendingMachineResponse);
        }

        if (null != beverages.getHot_coffee()) {
            InventoryResponse inventoryResponse = prepareHotCoffee(beverages);
            VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
            vendingMachineResponse.setItem(Items.HOT_COFFEE.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
            vendingMachineResponses.add(vendingMachineResponse);
        }

        if (null != beverages.getBlack_tea()) {
            InventoryResponse inventoryResponse = prepareBlackTea(beverages);
            VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
            vendingMachineResponse.setItem(Items.BLACK_TEA.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
            vendingMachineResponses.add(vendingMachineResponse);
        }

        if (null != beverages.getGreenTea()) {
            InventoryResponse inventoryResponse = prepareGreenTea(beverages);
            VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
            vendingMachineResponse.setItem(Items.GREEN_TEA.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
            vendingMachineResponses.add(vendingMachineResponse);
        }
        vendingMachineResponseWrapper.setVendingMachineResponseList(vendingMachineResponses);
        return vendingMachineResponseWrapper;
    }

    private InventoryResponse prepareGreenTea(Beverages beverages) {

        greenTeaInventoryOp = new GreenTeaInventoryOp(beverages.getGreenTea());
        InventoryResponse inventoryResponse = greenTeaInventoryOp.updateItem();
        return inventoryResponse;
    }

    private InventoryResponse prepareBlackTea(Beverages beverages) {
        blackTeaInventoryOp = new BlackTeaInventoryOp(beverages.getBlack_tea());
        InventoryResponse inventoryResponse = blackTeaInventoryOp.updateItem();
        return inventoryResponse;
    }

    private InventoryResponse prepareHotCoffee(Beverages beverages) {
        hotCoffeeInventryOp = new HotCoffeeInventryOp(beverages.getHot_coffee());
        InventoryResponse inventoryResponse = hotCoffeeInventryOp.updateItem();
        return inventoryResponse;
    }

    private InventoryResponse prepareHotTea(Beverages beverages) {
        hotTeaInventoryOp = new HotTeaInventoryOp(beverages.getHot_tea());
        InventoryResponse inventoryResponse = hotTeaInventoryOp.updateItem();
       return inventoryResponse;
    }

    public boolean cancelOrder() {
        return false;
    }
}
