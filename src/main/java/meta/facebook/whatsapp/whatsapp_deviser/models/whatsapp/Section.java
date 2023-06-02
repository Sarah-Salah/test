package meta.facebook.whatsapp.whatsapp_deviser.models.whatsapp;


import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonPropertyOrder({
        "title",
        "rows",
})
public class Section {

        @JsonProperty("title")
        private String title;
        @JsonProperty("rows")
        private List<Row> rows = null;
}
