package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;

import java.util.List;
import java.util.ArrayList;
import javax.annotation.Generated;


import com.fasterxml.jackson.annotation.*;
import lombok.Data;


@Data
@JsonPropertyOrder({
    "button",
    "sections",
    "buttons",
})

public class Action {
    @JsonProperty("button")
    private String button;

    //@JsonIgnore
    @JsonProperty("sections")
    private List<Section> sections = new ArrayList<>();

    //@JsonIgnore

    @JsonProperty("buttons")
    private List<Button> buttons = new ArrayList<>();
}
