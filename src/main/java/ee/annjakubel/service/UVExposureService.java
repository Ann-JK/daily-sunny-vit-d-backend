package ee.annjakubel.service;

import ee.annjakubel.Description;
import ee.annjakubel.controller.OpenUVController;
import ee.annjakubel.model.OpenUV;
import ee.annjakubel.model.UVRequest;
import ee.annjakubel.model.UVResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Singleton
@Slf4j
public class UVExposureService {

    @Inject
    OpenUVController openUVController;

    public UVResponse calculateSunExposureData(UVRequest uvRequest) throws IOException, InterruptedException {
       OpenUV uvData = openUVController.getUVData(String.valueOf(uvRequest.getLatitude()),
               String.valueOf(uvRequest.getLongitude()));

       log.info("Current uv data: {}", uvData.getUvIndex());

       if (uvData.getUvIndex() < 3.0) {
           return new UVResponse(uvData.getUvIndex(), 0, Description.NO_EXPOSURE);
       }

       return calculateFormula(uvRequest.getSkinType(), uvData);

    }

    public UVResponse calculateFormula(int skinType, OpenUV uvData) {
        UVResponse response = new UVResponse();

        return response;
    }
}
