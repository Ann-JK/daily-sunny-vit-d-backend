package ee.annjakubel.service;

import ee.annjakubel.auth.AuthoritiesFetcher;
import ee.annjakubel.repository.RequestorRoleJdbcRepository;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;

import java.util.List;

@Singleton
class AuthoritiesFetcherService implements AuthoritiesFetcher {

    @Inject
    RequestorRoleJdbcRepository jdbcRepository;

    @Override
    public List<String> findAuthoritiesByUsername(String username) {
        return jdbcRepository.findAllAuthoritiesByUsername(username);
    }
}
