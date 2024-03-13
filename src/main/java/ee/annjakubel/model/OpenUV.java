package ee.annjakubel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
public class OpenUV {
     private Double uvValue;
     private OffsetDateTime currentDateTime;
     private double latitude;
     private double longitude;
}
