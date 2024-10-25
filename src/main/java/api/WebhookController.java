package api;

import model.OfferUpdated;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class WebhookController {
    @PostMapping("/webhook")
    public ResponseEntity<String> handleWebhook(@RequestBody OfferUpdated payload) {
        System.out.println("Received Webhook:");
        System.out.println("Seller ID: " + payload.getSellerId());
        System.out.println("Offer ID: " + payload.getOfferId());
        System.out.println("Batch ID: " + payload.getBatchId());
        System.out.println("Values Changed: " + payload.getValuesChanged());

        // Respond with a 200 OK status
        return new ResponseEntity<>("Webhook received successfully", HttpStatus.OK);
    }
}

