package com.dziky.takealotproductmonitor.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Map;
@Data

public class OfferUpdated {
    @JsonProperty("seller_id")
    private int sellerId;

    @JsonProperty("offer_id")
    private int offerId;

    @JsonProperty("values_changed")
    private Map<String, Object> valuesChanged;

    @JsonProperty("batch_id")
    private int batchId;

}
