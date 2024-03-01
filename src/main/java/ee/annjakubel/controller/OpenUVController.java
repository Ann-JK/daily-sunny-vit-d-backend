package ee.annjakubel.controller;

import ee.annjakubel.tokens.Token;

import io.micronaut.http.annotation.*;
import io.micronaut.http.client.HttpClient;
import jakarta.inject.Inject;
import org.reactivestreams.Publisher;

@Controller
public class OpenUVController {

    @Inject
    HttpClient httpClient;

    @Get("/uv")
    @Header(name = Token.OPEN_UV_HEADER_NAME, value = Token.OPEN_UV_HEADER_TOKEN)
    public Publisher<String> getUVData(@QueryValue String lat, @QueryValue String lng) {
        return httpClient.retrieve("https://api.openuv.io/api/v1/uv?lat=" + lat + "&lng=" + lng);
    }

}
