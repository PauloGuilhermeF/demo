package br.com.devedojo.demo.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.data.domain.Sort;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CustomSortDeserializer extends JsonDeserializer<Sort> {
    @Override
    public Sort deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        List<Sort.Order> orders = new ArrayList<>();

        // Se o nó for um array, processa cada item como uma ordem de sort
        if (node.isArray()) {
            for (JsonNode json : node) {
                String direction = json.get("direction").asText();
                String property = json.get("property").asText();
                orders.add(new Sort.Order(Sort.Direction.valueOf(direction), property));
            }
        } else if (node.isObject()) {
            // Tenta extrair um array do campo "orders"
            JsonNode ordersNode = node.get("orders");
            if (ordersNode != null && ordersNode.isArray()) {
                for (JsonNode json : ordersNode) {
                    String direction = json.get("direction").asText();
                    String property = json.get("property").asText();
                    orders.add(new Sort.Order(Sort.Direction.valueOf(direction), property));
                }
            } else {
                // Se não houver campo "orders", verifica se o próprio objeto contém os campos necessários
                if (node.has("direction") && node.has("property")) {
                    String direction = node.get("direction").asText();
                    String property = node.get("property").asText();
                    orders.add(new Sort.Order(Sort.Direction.valueOf(direction), property));
                }
            }
        }

        return orders.isEmpty() ? Sort.unsorted() : Sort.by(orders);
    }
}
