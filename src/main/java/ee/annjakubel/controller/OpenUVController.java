package ee.annjakubel.controller;

import ee.annjakubel.model.OpenUV;
import ee.annjakubel.service.OpenUVService;
import io.micronaut.context.annotation.Property;
import io.micronaut.http.annotation.*;
import io.micronaut.http.server.cors.CrossOrigin;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Inject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Controller
@CrossOrigin(allowedOrigins = "https://localhost:4200/d-vit, https://localhost:4200/d-vit/uv")
public class OpenUVController {
    @Inject
    OpenUVService service;

    @Property(name="openuv.header.token.name")
    String accessHeaderName;
    @Property(name="openuv.header.token.value")
    String accessHeaderValue;

    HttpClient httpClient = HttpClient.newHttpClient();

    @Get("/open-uv")
    public OpenUV getUVData(@QueryValue String lat, @QueryValue String lng) throws IOException, InterruptedException {
        URI uri = UriBuilder.of("https://api.openuv.io/api/v1/uv?lat=" + lat + "&lng=" + lng).build();
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(accessHeaderName, accessHeaderValue)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return service.transformResponse(response);
    }
}
