package meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class Interactive {
    @JsonProperty("header")
    private String header;
    @JsonProperty("description")
    private String description;

}
