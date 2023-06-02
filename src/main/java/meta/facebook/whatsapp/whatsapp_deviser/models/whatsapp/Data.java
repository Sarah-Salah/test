package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;


@lombok.Data
@JsonPropertyOrder({
    "messaging_product",
    "to",
    "status",
    "message_id",
    "type",
    "text",
    "location",
    "recipient_type",
    "interactive"
})
public class Data {
    @JsonProperty("messaging_product")
    private String messagingProduct;

    @JsonProperty("to")
    private String to;

    @JsonProperty("status")
    private String status;

    @JsonProperty("message_id")
    private String messageId;

    @JsonProperty("type")
    private String type;

    @JsonProperty("text")
    private Text text;

    @JsonProperty("location")
    private Location location;

    @JsonProperty("recipient_type")
    private String recipientType;

    @JsonProperty("interactive")
    private Interactive interactive;

}
