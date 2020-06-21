package response;

import lombok.Data;

@Data
public class InventoryResponse {
    private boolean isPrepared;
    private String itemUnavailable;
    private boolean isInSufficient;
}
