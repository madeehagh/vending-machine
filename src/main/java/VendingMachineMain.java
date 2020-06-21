import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import controller.VendingMachineController;
import handler.VendingMachineHandler;
import model.Beverages;
import model.VendingRequest;
import util.VendingUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Map;

public class VendingMachineMain {

    public static void main(String[] args) throws Exception {

        String fileLoc = "src/main/resources/input.json";
        ObjectMapper objectMapper = new ObjectMapper();
        VendingMachineHandler vendingMachineHandler = new VendingMachineHandler();

        try  {
            VendingRequest vendingRequest = objectMapper.readValue(new File(fileLoc), VendingRequest.class);

            if (null != vendingRequest && vendingRequest.getMachine() != null) {
                vendingMachineHandler.initializeSystem(vendingRequest.getMachine().getOutlets());
                vendingMachineHandler.initializeInventory(vendingRequest.getMachine().getTotal_items_quantity());
                vendingMachineHandler.placeOrder(vendingRequest.getMachine().getBeverages());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }




}
