package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
    "type",
    "reply",

})
public class Button {
    @JsonProperty("type")
    private String type;

    @JsonProperty("reply")
    private Reply reply;
}
