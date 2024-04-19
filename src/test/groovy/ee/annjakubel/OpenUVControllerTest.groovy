package ee.annjakubel

import ee.annjakubel.controller.WeatherDataController
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class OpenUVControllerTest extends Specification {

    @Inject
    WeatherDataController controller

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
