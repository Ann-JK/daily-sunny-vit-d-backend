package ee.annjakubel.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Serdeable
public class UVResponse {
    public double UV;
    public double recommendedExposure;
    public String description;
}
