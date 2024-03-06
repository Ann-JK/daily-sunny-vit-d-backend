package ee.annjakubel

import ee.annjakubel.controller.OpenUVController
import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class OpenUVControllerTest extends Specification {

    @Inject
    OpenUVController controller

    def "apiCallResponseIsDeserialized"() {

        given:
        def lat = "55.5"
        def lng = "66.6"

        when:
        def response = controller.getUVData(lat, lng)

        then:
        response.uvValue != null
        response.currentDateTime != null
    }


}
