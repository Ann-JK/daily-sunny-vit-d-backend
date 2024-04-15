package ee.annjakubel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UVRequest {
    public int skinType;
    public double longitude;
    public double latitude;
}
