package util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class VendingUtil {
    static ObjectMapper objectMapper = new ObjectMapper();

    public static Map<String, String> convertJsonNodetoMap(JsonNode jsonNode) {
        Map<String, String> result = objectMapper.convertValue(jsonNode,
                new TypeReference<Map<String, String>>(){});
        return result;
    }

   public static boolean isObjectNullOrEmpty(JsonNode jsonNode) {
        if (null == jsonNode)
            return true;
            return jsonNode.isEmpty();
   }
}
