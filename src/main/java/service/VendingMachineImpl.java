package service;

import enums.Items;
import handler.BlackTeaInventoryOp;
import handler.GreenTeaInventoryOp;
import handler.HotCoffeeInventryOp;
import handler.HotTeaInventoryOp;
import model.Beverages;
import model.VendingMachineResponse;
import util.Inventory;

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
    public void createInventory(Map<String, String> inventories) {
        inventory.createInventory(inventories);
    }

    @Override
    public void setNoOfUnit(int n) {
        inventory.setNoOfUnits(n);
    }

    @Override
    public void placeOrder(Beverages beverages) {

        if (null != beverages.getHot_tea()) {
            prepareHotTea(beverages);
        }

        if (null != beverages.getHot_coffee()) {
            prepareHotCoffee(beverages);
        }

        if (null != beverages.getBlack_tea()) {
            prepareBlackTea(beverages);
        }

        if (null != beverages.getGreenTea()) {
            prepareGreenTea(beverages);
        }
    }

    private void prepareGreenTea(Beverages beverages) {
        boolean isPrepared;
        boolean isInsufficient;
        greenTeaInventoryOp = new GreenTeaInventoryOp(beverages.getGreenTea());
        VendingMachineResponse vendingMachineResponse = greenTeaInventoryOp.updateItem();
        isPrepared = vendingMachineResponse.isPrepared();
        isInsufficient = vendingMachineResponse.isInSufficient();

        if (isPrepared)
            System.out.println(Items.GREEN_TEA + " is prepared");
        else if (isInsufficient)
            System.out.println(Items.GREEN_TEA.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not sufficient");
        else
            System.out.println(Items.GREEN_TEA.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not sufficient");
    }

    private void prepareBlackTea(Beverages beverages) {
        boolean isPrepared;
        boolean isInsufficient;
        blackTeaInventoryOp = new BlackTeaInventoryOp(beverages.getBlack_tea());
        VendingMachineResponse vendingMachineResponse = blackTeaInventoryOp.updateItem();
        isPrepared = vendingMachineResponse.isPrepared();
        isInsufficient = vendingMachineResponse.isInSufficient();
        if (isPrepared)
            System.out.println(Items.BLACK_TEA.toString() + " is prepared");
        else if (isInsufficient)
            System.out.println(Items.BLACK_TEA.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not sufficient");
        else
            System.out.println(Items.BLACK_TEA.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not available");
    }

    private void prepareHotCoffee(Beverages beverages) {
        boolean isPrepared;
        boolean isInsufficient;
        hotCoffeeInventryOp = new HotCoffeeInventryOp(beverages.getHot_coffee());
        VendingMachineResponse vendingMachineResponse = hotCoffeeInventryOp.updateItem();
        isPrepared = vendingMachineResponse.isPrepared();
        isInsufficient = vendingMachineResponse.isInSufficient();

        if (isPrepared)
            System.out.println(Items.HOT_COFFEE.toString() + " is prepared");
        else if (isInsufficient)
            System.out.println(Items.HOT_COFFEE.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not sufficient");
        else
            System.out.println(Items.HOT_COFFEE.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not available");
    }

    private void prepareHotTea(Beverages beverages) {
        boolean isPrepared;
        boolean isInsufficient;
        hotTeaInventoryOp = new HotTeaInventoryOp(beverages.getHot_tea());
        VendingMachineResponse vendingMachineResponse = hotTeaInventoryOp.updateItem();
        isPrepared = vendingMachineResponse.isPrepared();
        isInsufficient = vendingMachineResponse.isInSufficient();

        if (isPrepared)
            System.out.println(Items.HOT_TEA.toString() + " is prepared");
        else if(isInsufficient)
            System.out.println(Items.HOT_TEA.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not sufficient");
        else
            System.out.println(Items.HOT_TEA.toString() + " can not be prepared because " +
                    vendingMachineResponse.getItemUnavailable() + " is not available");
    }

    public boolean cancelOrder() {
        return false;
    }


}
