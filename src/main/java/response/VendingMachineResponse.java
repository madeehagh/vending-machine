package response;

import lombok.Data;

@Data
public class VendingMachineResponse {
    String item;
    InventoryResponse inventoryResponse;
}
