package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;


import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
    "type",
    "header",
    "body",
    "footer",
    "action",
})
public class Interactive {

        @JsonProperty("type")
        private String type;

        @JsonProperty("header")
        private Header header;

        @JsonProperty("body")
        private Body body;

        @JsonProperty("footer")
        private Footer footer;

        @JsonProperty("action")
        private Action action;


}
