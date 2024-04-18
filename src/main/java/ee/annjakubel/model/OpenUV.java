package ee.annjakubel.model;

import io.micronaut.serde.annotation.Serdeable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Serdeable
public class OpenUV {
     private Double uvIndex;
     private OffsetDateTime currentDateTime;
     private double latitude;
     private double longitude;
     private double altitude;
     private double ozoneLayerThickness;
}
