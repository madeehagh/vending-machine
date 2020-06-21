package model;

import lombok.Data;

@Data
public class VendingMachineResponse {
    private boolean isPrepared;
    private String itemUnavailable;
    private boolean isInSufficient;
}
