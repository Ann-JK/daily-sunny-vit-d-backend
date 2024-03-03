package ee.annjakubel

import io.micronaut.http.HttpRequest
import io.micronaut.http.HttpStatus
import io.micronaut.http.client.HttpClient
import io.micronaut.http.client.annotation.Client
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import jakarta.inject.Inject
import spock.lang.Specification

@MicronautTest
class OpenUVControllerTest extends Specification {

    @Client("/")
    @Inject
    HttpClient client

    def "apiCallResponseIsDeserialized"() {

        given:
        def lat = "55.5"
        def lng = "66.6"
        def request = HttpRequest.GET("/uv?lat=$lat&lng=$lng")

        when:
        def response = client.toBlocking().exchange(request, String)

        then:
        response.status == HttpStatus.OK
    }


}
