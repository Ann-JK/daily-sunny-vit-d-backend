package ee.annjakubel.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import ee.annjakubel.model.OpenUV;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;


import java.net.http.HttpResponse;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Singleton
@Slf4j
public class OpenUVService {

    public OpenUV parseOpenUVResponse(HttpResponse<String> response, String latitude, String longitude) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(response.body());

        double uvIndex = jsonNode.get("result").get("uv").asDouble();
        String currentDateTime = jsonNode.get("result").get("uv_time").asText();
        //TODO: THIS IS THE SOLAR ALTITUDE ANGLE!!
        double altitudeAngle = jsonNode.get("result").get("sun_info").get("sun_position").get("altitude").asDouble();
        double oZoneLayer = jsonNode.get("result").get("ozone").asDouble();

        OffsetDateTime parsedDateTime = OffsetDateTime.parse(currentDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        double lat = Double.parseDouble(latitude);
        double lng = Double.parseDouble(longitude);

        return new OpenUV(uvIndex, parsedDateTime, lat, lng, altitudeAngle, oZoneLayer);
    }

    public double parseGoogleElevationResponse(HttpResponse<String> response) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(response.body());
        log.info(jsonNode.toString());

        double elevation = jsonNode.get("results").get(0).get("elevation").asDouble();
        return elevation;
    }
}
