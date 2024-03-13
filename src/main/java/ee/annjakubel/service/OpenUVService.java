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

    public OpenUV transformResponse(HttpResponse<String> response, String latitude, String longitude) throws JsonProcessingException {
        JsonNode jsonNode = new ObjectMapper().readTree(response.body());

        Double uvValue = jsonNode.get("result").get("uv").asDouble();
        String currentDateTime = jsonNode.get("result").get("uv_time").asText();

        OffsetDateTime parsedDateTime = OffsetDateTime.parse(currentDateTime, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        Double lat = Double.parseDouble(latitude);
        Double lng = Double.parseDouble(longitude);

        return new OpenUV(uvValue, parsedDateTime, lat, lng);
    }
}
