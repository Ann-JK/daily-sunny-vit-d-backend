package ee.annjakubel.service;

import ee.annjakubel.Description;
import ee.annjakubel.controller.WeatherDataController;
import ee.annjakubel.entity.UVData;
import ee.annjakubel.model.OpenUV;
import ee.annjakubel.model.UVRequest;
import ee.annjakubel.model.UVResponse;
import ee.annjakubel.repository.UVDataJdbcRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

@Singleton
@Slf4j
public class UVExposureService {

    @Inject
    WeatherDataController openUVController;

    @Inject
    UVDataJdbcRepository uvDataJdbcRepository;

    public UVResponse calculateSunExposureData(UVRequest uvRequest) throws IOException, InterruptedException {
        String formattedLongitude = String.valueOf(uvRequest.getLongitude());
        String formattedLatitude = String.valueOf(uvRequest.getLatitude());

        OpenUV uvData = openUVController.getUVData(formattedLatitude,
               formattedLongitude);

       double elevation = openUVController.getElevationData(formattedLatitude, formattedLongitude);
       double formattedUVIndex = roundDecimals(uvData.getUvIndex(), 2);
       int skinType = uvRequest.getSkinType();

       if (formattedUVIndex < 3.0) {
           saveHistory(uvRequest, uvData.getUvIndex(), 0);
           return new UVResponse(formattedUVIndex, 0, Description.NO_EXPOSURE);
       }

        double recommendedTime = calculateFormula(skinType, uvData, elevation);
        String formattedDescription = String.format(Description.EXPOSURE, (int) formattedUVIndex, (int) recommendedTime);
        log.info(formattedDescription);

        saveHistory(uvRequest, uvData.getUvIndex(), recommendedTime);
        return new UVResponse(formattedUVIndex, recommendedTime, formattedDescription);
    }

    public double calculateFormula(int skinType, OpenUV uvData, double elevation) {

        double altitudeAngleInDegrees = Math.toDegrees(uvData.getAltitudeAngle());
        double solarZenithAngle = Math.toRadians(90 - altitudeAngleInDegrees);
        log.info("Skin type: {}; UV index : {}; Solar Zenith Angle: {} radians; Elevation from sea level: {} meters; " +
                "Ozone layer thickness: {} dobsons", skinType, uvData.getUvIndex(), solarZenithAngle, elevation,
                uvData.getOzoneLayerThickness());

        double altitudeFactor = 1 / (Math.pow(1 + 0.0001 * elevation, 2));
        double oZoneLayerThicknessFactor = 1 / (1 + 0.0001 * uvData.getOzoneLayerThickness());
        double skinTypeFactor = 1.0 / skinType;

        double minutesResult = roundDecimals(1000.0 / (uvData.getUvIndex() * 60 * skinTypeFactor *
                 solarZenithAngle * altitudeFactor * oZoneLayerThicknessFactor), 0);

        return minutesResult;
    }

    public double roundDecimals(double value, int decimal) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(decimal, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }

    public void saveHistory( UVRequest request, double uvIndex, double minutes) {
        uvDataJdbcRepository.save(new UVData(null,
                uvIndex,
                request.getLongitude(),
                request.getLatitude(),
                request.getSkinType(),
                minutes,
                LocalDateTime.now()));

    }
}
