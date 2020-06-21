package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Beverages;

import java.util.Map;

public class VendingUtil {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> convertJsonNodetoMap(JsonNode jsonNode) {
        Map<String, String> result = objectMapper.convertValue(jsonNode,
                new TypeReference<Map<String, String>>(){});
        return result;
    }

    public static Map<String, String> convertJsonNodetoBeverageMap(JsonNode beverages) {
        Map<String, String> result = objectMapper.convertValue(beverages,
                new TypeReference<Map<String, String>>(){});
        return result;
    }
}
