package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GreenTea {
    @JsonProperty("hot_water")
    public String hot_water;
    @JsonProperty("ginger_syrup")
    public String ginger_syrup;
    @JsonProperty("sugar_syrup")
    public String sugar_syrup;
    @JsonProperty("green_mixture")
    public  String green_mixture;
}
