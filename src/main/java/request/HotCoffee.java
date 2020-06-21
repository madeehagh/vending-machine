package request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class HotCoffee {
    @JsonProperty("hot_water")
    public String hot_water;
    @JsonProperty("ginger_syrup")
    public String ginger_syrup;
    @JsonProperty("hot_milk")
    public String hot_milk;
    @JsonProperty("sugar_syrup")
    public String sugar_syrup;
    @JsonProperty("tea_leaves_syrup")
    public String tea_leaves_syrup;
}
