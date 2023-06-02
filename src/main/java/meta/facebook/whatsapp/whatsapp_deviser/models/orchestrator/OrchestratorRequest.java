package meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Setter
@Getter
@ToString
public class OrchestratorRequest {
    @JsonProperty("content")
    private Content content;
}
