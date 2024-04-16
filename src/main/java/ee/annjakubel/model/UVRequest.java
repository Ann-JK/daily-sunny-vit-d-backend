package ee.annjakubel.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Serdeable
public class UVRequest {
    public int skinType;
    public double longitude;
    public double latitude;
}
