package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
    "id",
    "title",

})
public class Reply {
    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
}
