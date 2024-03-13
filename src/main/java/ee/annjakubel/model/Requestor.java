package ee.annjakubel.model;

import ee.annjakubel.enums.SkinAttribute;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Requestor {
    private String email;
    private SkinAttribute attributeNumber;
    private Double latitude;
    private Double longitude;

}
