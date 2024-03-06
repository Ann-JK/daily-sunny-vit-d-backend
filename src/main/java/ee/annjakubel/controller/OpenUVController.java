package ee.annjakubel.controller;

import ee.annjakubel.model.OpenUV;
import ee.annjakubel.service.OpenUVService;
import ee.annjakubel.tokens.Token;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.QueryValue;
import io.micronaut.http.uri.UriBuilder;
import jakarta.inject.Inject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


@Controller
public class OpenUVController {

    @Inject
    OpenUVService service;
    HttpClient httpClient = HttpClient.newHttpClient();

    @Get("/uv")
    public OpenUV getUVData(@QueryValue String lat, @QueryValue String lng) throws IOException, InterruptedException {
        URI uri = UriBuilder.of("https://api.openuv.io/api/v1/uv?lat=" + lat + "&lng=" + lng).build();
        HttpRequest request = HttpRequest.newBuilder(uri)
                .header(Token.OPEN_UV_HEADER_NAME, Token.OPEN_UV_HEADER_TOKEN)
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return service.transformResponse(response);
    }
}