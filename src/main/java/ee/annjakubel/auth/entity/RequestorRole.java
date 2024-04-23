package ee.annjakubel.auth.entity;

import io.micronaut.data.annotation.EmbeddedId;
import io.micronaut.data.annotation.MappedEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;

@MappedEntity
@AllArgsConstructor
@Getter
public class RequestorRole {

    @EmbeddedId
    private final RequestorRoleId id;


}
