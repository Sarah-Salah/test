package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "body",
})
public class Text {
    @JsonProperty("body")
    private String body;
}
