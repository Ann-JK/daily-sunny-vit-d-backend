package ee.annjakubel.service;

import ee.annjakubel.Description;
import ee.annjakubel.controller.WeatherDataController;
import ee.annjakubel.model.OpenUV;
import ee.annjakubel.model.UVRequest;
import ee.annjakubel.model.UVResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.math.BigDecimal;

@Singleton
@Slf4j
public class UVExposureService {

    @Inject
    WeatherDataController openUVController;

    public UVResponse calculateSunExposureData(UVRequest uvRequest) throws IOException, InterruptedException {
       OpenUV uvData = openUVController.getUVData(String.valueOf(uvRequest.getLatitude()),
               String.valueOf(uvRequest.getLongitude()));

       double elevation = openUVController.getElevationData(String.valueOf(uvRequest.getLatitude()),
               String.valueOf(uvRequest.getLongitude()));

       double formattedUVIndex = roundDecimals(uvData.getUvIndex(), 2);

       if (uvData.getUvIndex() < 3.0) {
           return new UVResponse(formattedUVIndex, 0, Description.NO_EXPOSURE);
       }

        double recommendedTime = calculateFormula(uvRequest.getSkinType(), uvData, elevation);
        String formattedDescription = String.format(Description.EXPOSURE, (int) formattedUVIndex, (int) recommendedTime);
        log.info(formattedDescription);

       return new UVResponse(formattedUVIndex, recommendedTime, formattedDescription);
    }

    public double calculateFormula(int skinType, OpenUV uvData, double elevation) {

        double altitudeAngleInDegrees = Math.toDegrees(uvData.getAltitudeAngle());
        double solarZenithAngle = Math.toRadians(90 - altitudeAngleInDegrees);
        log.info("Solar Zenith Angle: {}", solarZenithAngle);
        log.info("Solar Altitude angle: {}", altitudeAngleInDegrees);

        double altitudeFactor = 1 / (Math.pow(1 + 0.0001 * elevation, 2));
        double oZoneLayerThicknessFactor = 1 / (1 + 0.0001 * uvData.getOzoneLayerThickness());
        double skinTypeFactor = 1.0 / skinType;


        double minutesResult = Math.round(1000.0 / (uvData.getUvIndex() * 60 * skinTypeFactor *
                 solarZenithAngle * altitudeFactor * oZoneLayerThicknessFactor));

        return roundDecimals(minutesResult, 0);
    }

    public double roundDecimals(double value, int decimal) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(decimal, BigDecimal.ROUND_HALF_UP);
        return bigDecimal.doubleValue();
    }
}
