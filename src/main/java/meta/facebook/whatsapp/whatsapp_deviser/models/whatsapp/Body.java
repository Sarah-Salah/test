package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
    "text",

})
public class Body {
    @JsonProperty("text")
    private String text;
}
