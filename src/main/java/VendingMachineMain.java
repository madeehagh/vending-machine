import com.fasterxml.jackson.databind.ObjectMapper;
import controller.VendingMachineController;
import request.VendingRequest;

import java.io.File;
import java.io.FileNotFoundException;

public class VendingMachineMain {

    public static void main(String[] args) throws Exception {

        String fileLoc = "src/main/resources/input.json";
        ObjectMapper objectMapper = new ObjectMapper();
        VendingMachineController vendingMachineController = new VendingMachineController();

        try  {
            VendingRequest vendingRequest = objectMapper.readValue(new File(fileLoc), VendingRequest.class);

            if (null != vendingRequest && vendingRequest.getMachine() != null) {
                vendingMachineController.initializeSystem(vendingRequest.getMachine().getOutlets());
                vendingMachineController.initializeInventory(vendingRequest.getMachine().getTotal_items_quantity());
                vendingMachineController.placeOrder(vendingRequest.getMachine().getBeverages());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}
