package ee.annjakubel.service;

import ee.annjakubel.auth.UserFetcher;
import ee.annjakubel.auth.UserState;
import ee.annjakubel.repository.RequestorJdbcRepository;
import io.micronaut.core.annotation.NonNull;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.validation.constraints.NotBlank;

import java.util.Optional;

@Singleton
class UserFetcherService implements UserFetcher {

    @Inject
    RequestorJdbcRepository jdbcRepository;

    @Override
    public Optional<UserState> findByUsername(@NotBlank @NonNull String username) {
        return jdbcRepository.findByUsername(username).map(UserState.class::cast);
    }
}
