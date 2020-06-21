package service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.mockito.Mock;
import request.*;
import model.Inventory;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

class VendingMachineImplTest {
    private static VendingMachine vendingMachine;

    @Mock
    Inventory inventory;

    @BeforeClass
    public void setUp() {
        vendingMachine = new VendingMachineImpl();
    }

    @Test
    public void createInventory_test() {
        Map<String, String> requestInventoryItems = createInventoryItems();
        assertTrue(vendingMachine.createInventory(requestInventoryItems) == true);
    }

    @Test
    public void updateInventory_test() {
        Beverages beverages = placeOrderRequest();
        assertTrue((vendingMachine.placeOrder(beverages).getVendingMachineResponseList().size()) > 0);
    }

    @Test
    public void setNoOfUnits_test() {

        assertTrue(vendingMachine.setNoOfUnit(3) == true);
    }

    private Map<String, String> createInventoryItems() {
        Map<String, String> inventory = new HashMap<>();
        inventory.put("hot_water", "500");
        inventory.put("hot_milk", "500");
        inventory.put("ginger_syrup", "100");
        inventory.put("sugar_syrup", "100");
        inventory.put("tea_leaves_syrup", "100");
        return inventory;
    }

    private Beverages placeOrderRequest() {

        Beverages beverages = new Beverages();

        HotTea hotTea = new HotTea();
        hotTea.setGinger_syrup("10");
        hotTea.setHot_milk("100");
        hotTea.setHot_water("200");
        hotTea.setSugar_syrup("10");
        hotTea.setTea_leaves_syrup("30");
        beverages.setHot_tea(hotTea);

        HotCoffee hotCoffee = new HotCoffee();
        hotCoffee.setGinger_syrup("30");
        hotCoffee.setHot_milk("400");
        hotCoffee.setHot_water("100");
        hotCoffee.setSugar_syrup("50");
        hotCoffee.setTea_leaves_syrup("30");
        beverages.setHot_coffee(hotCoffee);

        BlackTea blackTea = new BlackTea();
        blackTea.setGinger_syrup("30");
        blackTea.setHot_water("300");
        blackTea.setSugar_syrup("50");
        blackTea.setTea_leaves_syrup("30");
        beverages.setBlack_tea(blackTea);

        GreenTea greenTea = new GreenTea();
        greenTea.setGinger_syrup("30");
        greenTea.setHot_water("100");
        greenTea.setSugar_syrup("50");
        greenTea.setGreen_mixture("30");
        beverages.setGreenTea(greenTea);
        return beverages;
    }

}