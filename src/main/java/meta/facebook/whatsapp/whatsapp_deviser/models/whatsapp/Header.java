package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.awt.*;

@Data
@JsonPropertyOrder({
    "type",
    "text",
    "image",
})
public class Header {
    @JsonProperty("type")
    private String type;

    //@JsonIgnore
    @JsonProperty("text")
    private String text;

    //@JsonIgnore
    @JsonProperty("image")
    private Image image;


}
