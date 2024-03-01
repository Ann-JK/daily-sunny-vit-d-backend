package ee.annjakubel.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.micronaut.serde.annotation.Serdeable;
import lombok.Data;

@Data
@Serdeable
public class OpenUV {

    @JsonProperty("uv")
    private double uvValue;

}
