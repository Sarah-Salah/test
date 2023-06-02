package meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.ArrayList;

@Data
public class OrchestratorResponse {
    @JsonProperty("imageUrl")
    private String imageUrl;

    @JsonProperty("videoUrl")
    private String videoUrl;

    @JsonProperty("audioUrl")
    private String audioUrl;

    // @JsonIgnore

    @JsonProperty("components")
    private List<String> components = new ArrayList<>();

    //@JsonIgnore
    //JsonProperty("listDesc")
    //private List<String> listDesc = new ArrayList<>();

    //@JsonIgnore
    //JsonProperty("listHeader")
    //private List<String> listHeader = new ArrayList<>();

    private List<Interactive> interactiveList = new ArrayList<>();

    @JsonProperty("message")
    private String message;
}
