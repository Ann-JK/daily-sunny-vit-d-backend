package ee.annjakubel.controller;

import ee.annjakubel.model.OpenUV;
import ee.annjakubel.tokens.Token;
import ee.annjakubel.tokens.TokenExample;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Header;
import io.micronaut.http.annotation.PathVariable;

@Controller
//@Header(name = Token.OPEN_UV_HEADER_NAME, value = Token.OPEN_UV_HEADER_TOKEN)
@Header(name = TokenExample.OPEN_UV_HEADER_NAME, value = TokenExample.OPEN_UV_HEADER_TOKEN)
public class OpenUVController {

    @Get("https://api.openuv.io/api/v1/uv?lat={lat}&lng={lng}")
    public OpenUV getUVData(@PathVariable("lat") String latitude, @PathVariable("lng") String longitude) {
        //TODO: Geolocation
        return null;
    }

}
