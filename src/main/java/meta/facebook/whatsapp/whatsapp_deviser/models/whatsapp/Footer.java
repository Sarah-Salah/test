package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
    "text",

})

@Generated("jsonschema2pojo")
public class Footer {

        @JsonProperty("text")
        private String text;
}
