package model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Data;

@Data
@JsonIgnoreProperties
public class Beverages {
    @JsonProperty("hot_tea")
    public HotTea hot_tea;
    @JsonProperty("hot_coffee")
    public HotCoffee hot_coffee;
    @JsonProperty("black_tea")
    public BlackTea black_tea;
    @JsonProperty("green_tea")
    public  GreenTea greenTea;
}
