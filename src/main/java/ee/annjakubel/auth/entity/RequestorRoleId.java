package ee.annjakubel.auth.entity;

import io.micronaut.data.annotation.Embeddable;
import io.micronaut.data.annotation.Relation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Objects;

@Embeddable
@AllArgsConstructor
@Getter
public class RequestorRoleId {

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private final Requestor requestor;

    @Relation(value = Relation.Kind.MANY_TO_ONE)
    private final Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RequestorRoleId requestorRoleId = (RequestorRoleId) o;

        return role.id().equals(requestorRoleId.getRole().id()) &&
                requestor.id().equals(requestorRoleId.getRequestor().id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(role.id(), requestor.id());
    }
}
