package response;

import lombok.Data;

import java.util.List;

@Data
public class VendingMachineResponseWrapper {
    List<VendingMachineResponse> vendingMachineResponseList;
}
