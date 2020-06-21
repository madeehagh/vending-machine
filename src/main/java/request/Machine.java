package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
public class Machine {
    @JsonProperty("total_items_quantity")
    private JsonNode total_items_quantity;
    @JsonProperty("beverages")
    private Beverages beverages;
    @JsonProperty("outlets")
    private JsonNode outlets;
}
