package ee.annjakubel.auth;

import io.micronaut.core.annotation.NonNull;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

public interface UserFetcher {
    Optional<UserState> findByUsername(@NotBlank @NonNull String username);
}
