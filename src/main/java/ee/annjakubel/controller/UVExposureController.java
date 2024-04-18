package ee.annjakubel.controller;

import ee.annjakubel.model.UVRequest;
import ee.annjakubel.model.UVResponse;
import ee.annjakubel.service.UVExposureService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.server.cors.CrossOrigin;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;


@Controller
@CrossOrigin(allowedOrigins = "https://localhost:4200, https://localhost:4200/d-vit, https://localhost:4200/d-vit/uv")
@Slf4j
public class UVExposureController {

    @Inject
    UVExposureService uvExposureService;

    @Post("/d-vit/uv")
    public MutableHttpResponse<UVResponse> getSunExposureData(@Body UVRequest uvRequest) throws IOException, InterruptedException {
        log.info(" ");
        UVResponse uvResponse = uvExposureService.calculateSunExposureData(uvRequest);
        log.info(uvResponse.toString());

        return HttpResponse.ok(uvResponse);
    }
}
