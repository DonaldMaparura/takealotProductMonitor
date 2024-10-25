package com.dziky.takealotproductmonitor.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WebhookController {
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody JsonNode payload) {
        // Check for the presence of the values_changed key
        if (payload.has("values_changed")) {
            JsonNode valuesChangedNode = payload.get("values_changed");

            // Check if it is an object
            if (valuesChangedNode.isObject()) {
                // Process as a JSON object
                System.out.println("Values Changed as Object: " + valuesChangedNode);
                // You can convert this to a specific class if you know its structure
            }
            // Check if it is a text (string)
            else if (valuesChangedNode.isTextual()) {
                String jsonString = valuesChangedNode.asText();
                try {
                    // Convert the JSON string to a JsonNode object
                    ObjectMapper mapper = new ObjectMapper();
                    JsonNode nestedJsonNode = mapper.readTree(jsonString);
                    System.out.println("Values Changed from String: " + nestedJsonNode);

                    // Extract the old and new values from the nested JSON
                    if (nestedJsonNode.has("selling_price")) {
                        JsonNode sellingPriceNode = nestedJsonNode.get("selling_price");

                        // Extract old_value and new_value

                        System.out.println("Seller ID: " + payload.get("seller_id"));
                        System.out.println("Offer ID: " + payload.get("offer_id"));
                        System.out.println("Old Value: " + sellingPriceNode.get("old_value").asInt());
                        System.out.println("New Value: " + sellingPriceNode.get("new_value").asInt());

                        // Further processing logic...
                    } else {
                        System.out.println("selling_price key not found in nested JSON.");
                    }
                } catch (JsonProcessingException e) {
                    // Handle JSON parsing exception
                    System.err.println("Error parsing JSON: " + e.getMessage());
                    return ResponseEntity.badRequest().body("Invalid JSON format in values_changed");
                }
            } else {
                System.out.println("Unexpected type for values_changed: " + valuesChangedNode.getNodeType());
            }
        } else {
            System.out.println("values_changed key not found in payload.");
        }
        return ResponseEntity.ok("Webhook received");
    }

}

