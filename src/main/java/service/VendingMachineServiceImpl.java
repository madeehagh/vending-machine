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
import util.Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class VendingMachineServiceImpl implements VendingMachineService {

    Inventory inventory;
    HotTeaInventoryOp hotTeaInventoryOp;
    HotCoffeeInventryOp hotCoffeeInventryOp;
    BlackTeaInventoryOp blackTeaInventoryOp;
    GreenTeaInventoryOp greenTeaInventoryOp;
    ExecutorService executorService;

    public VendingMachineServiceImpl(){
        inventory = new Inventory();
    }

    /**
     * This method initialises inventory (Key : item, value: quantity available)
     * @param inventories
     * @return
     */
    @Override
    public boolean createInventory(Map<String, String> inventories) {

        return inventory.createInventory(inventories);
    }

    /**
     * This method creates number of outlets for the vending machine
     * @param n
     * @return
     */
    @Override
    public boolean setNoOfUnit(int n) {
        inventory.setNoOfUnits(n);
        executorService = Executors.newFixedThreadPool(n);
        return true;
    }

    /**
     * This method takes Beverage Object as input, checks the availability of item and places order for that item.
     * @param beverages
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public VendingMachineResponseWrapper placeOrder(Beverages beverages) throws ExecutionException, InterruptedException {
        VendingMachineResponseWrapper vendingMachineResponseWrapper = new VendingMachineResponseWrapper();
        List<VendingMachineResponse> vendingMachineResponses = new ArrayList<>();

        Callable hotTeaCallableTask  = () -> {
            VendingMachineResponse vendingMachineResponse = processHotTea(beverages);
            VendingMachineResponse vendingMachineHotCoffeeResp = processHotCoffe(beverages);
            VendingMachineResponse vendingMachineBlackTeaResp = processBlackTea(beverages);
            VendingMachineResponse vendingMachineGreenTeaResp = processGreenTea(beverages);
            vendingMachineResponses.add(vendingMachineResponse);
            vendingMachineResponses.add(vendingMachineBlackTeaResp);
            vendingMachineResponses.add(vendingMachineGreenTeaResp);
            vendingMachineResponses.add(vendingMachineHotCoffeeResp);
            vendingMachineResponseWrapper.setVendingMachineResponseList(vendingMachineResponses);
            return vendingMachineResponseWrapper;
        };

        return (VendingMachineResponseWrapper) executorService.submit(hotTeaCallableTask).get();
    }

    /**
     * Process Green Tea
     * @param beverages
     * @return
     */
    private VendingMachineResponse processGreenTea(Beverages beverages) {
        VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
        if (null != beverages.getGreenTea()) {
            InventoryResponse inventoryResponse = prepareGreenTea(beverages);
            vendingMachineResponse.setItem(Items.GREEN_TEA.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
        }
        return vendingMachineResponse;
    }

    /**
     * Process Black Tea
     * @param beverages
     * @return
     */
    private VendingMachineResponse processBlackTea(Beverages beverages) {
        VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
        if (null != beverages.getBlack_tea()) {
            InventoryResponse inventoryResponse = prepareBlackTea(beverages);
            vendingMachineResponse.setItem(Items.BLACK_TEA.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
        }
        return vendingMachineResponse;
    }

    /**
     * Process Hot Coffee
     * @param beverages
     * @return
     */
    private VendingMachineResponse processHotCoffe(Beverages beverages) {
        VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
        if (null != beverages.getHot_coffee()) {
            InventoryResponse inventoryResponse = prepareHotCoffee(beverages);
            vendingMachineResponse.setItem(Items.HOT_COFFEE.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
        }
        return vendingMachineResponse;
    }

    /**
     * Process Hot Tea
     * @param beverages
     * @return
     */
    private VendingMachineResponse processHotTea(Beverages beverages) {
        VendingMachineResponse vendingMachineResponse = new VendingMachineResponse();
        if (null != beverages.getHot_tea()) {
            InventoryResponse inventoryResponse = prepareHotTea(beverages);
            vendingMachineResponse.setItem(Items.HOT_TEA.toString());
            vendingMachineResponse.setInventoryResponse(inventoryResponse);
        }
        return vendingMachineResponse;
    }

    /**
     * Prepare green tea
     * @param beverages
     * @return
     */
    private InventoryResponse prepareGreenTea(Beverages beverages) {

        greenTeaInventoryOp = new GreenTeaInventoryOp(beverages.getGreenTea());
        InventoryResponse inventoryResponse = greenTeaInventoryOp.updateItem();
        return inventoryResponse;
    }

    /**
     * Prepare black Tea
     * @param beverages
     * @return
     */
    private InventoryResponse prepareBlackTea(Beverages beverages) {
        blackTeaInventoryOp = new BlackTeaInventoryOp(beverages.getBlack_tea());
        InventoryResponse inventoryResponse = blackTeaInventoryOp.updateItem();
        return inventoryResponse;
    }

    /**
     * Prepare hot coffee
     * @param beverages
     * @return
     */
    private InventoryResponse prepareHotCoffee(Beverages beverages) {
        hotCoffeeInventryOp = new HotCoffeeInventryOp(beverages.getHot_coffee());
        InventoryResponse inventoryResponse = hotCoffeeInventryOp.updateItem();
        return inventoryResponse;
    }

    /**
     * Prepare hot tea
     * @param beverages
     * @return
     */
    private InventoryResponse prepareHotTea(Beverages beverages) {
        hotTeaInventoryOp = new HotTeaInventoryOp(beverages.getHot_tea());
        InventoryResponse inventoryResponse = hotTeaInventoryOp.updateItem();
       return inventoryResponse;
    }

    public boolean cancelOrder() {
        return false;
    }
}
