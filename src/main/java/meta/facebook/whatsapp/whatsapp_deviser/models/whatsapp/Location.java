package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
    "latitude",
    "longitude",
    "name",
    "address"
})

public class Location {

            @JsonProperty("latitude")
            private String latitude;

            @JsonProperty("longitude")
            private String longitude;

            @JsonProperty("name")
            private String name;

            @JsonProperty("address")
            private String address;
}
