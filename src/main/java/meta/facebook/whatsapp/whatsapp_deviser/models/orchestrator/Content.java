package meta.facebook.whatsapp.whatsapp_deviser.models.orchestrator;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;


@Setter
@Getter
@ToString
public class Content {
    @JsonProperty("userInput")
    private String userInput;
    @JsonProperty("userDial")
    private String userDial;
    @JsonProperty("sessionId")
    private String sessionId;
    @JsonProperty("language")
    private String language;
    @JsonProperty("channel")
    private String channel;
    @JsonProperty("customerType")
    private String customerType;
    @JsonProperty("channelMediaType")
    private String channelMediaType;
    @JsonProperty("sourceChannel")
    private String sourceChannel;
    @JsonProperty("clientName")
    private String clientName;
}
