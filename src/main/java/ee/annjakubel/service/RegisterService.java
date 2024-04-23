package ee.annjakubel.service;

import ee.annjakubel.auth.PasswordEncoder;
import ee.annjakubel.auth.entity.Requestor;
import ee.annjakubel.auth.entity.RequestorRole;
import ee.annjakubel.auth.entity.RequestorRoleId;
import ee.annjakubel.auth.entity.Role;
import ee.annjakubel.exception.UserAlreadyExistsException;
import ee.annjakubel.repository.RequestorJdbcRepository;
import ee.annjakubel.repository.RequestorRoleJdbcRepository;
import ee.annjakubel.repository.RoleJdbcRepository;
import jakarta.annotation.Nullable;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Singleton
@AllArgsConstructor
public class RegisterService {
    private static final boolean DEFAULT_ENABLED = true;
    private static final boolean DEFAULT_ACCOUNT_EXPIRED = false;
    private static final boolean DEFAULT_ACCOUNT_LOCKED = false;
    private static final boolean DEFAULT_PASSWORD_EXPIRED = false;

    @Inject
    RoleJdbcRepository roleService;
    @Inject
    RequestorJdbcRepository requestorJdbcRepository;
    @Inject
    RequestorRoleJdbcRepository requestorRoleJdbcRepository;
    @Inject
    PasswordEncoder passwordEncoder;

    public void register(@NotBlank String username, @NotBlank String rawPassword) {
        register(username, rawPassword, Collections.emptyList());
    }

    @Transactional
    public void register(@NotBlank String username, @NotBlank String rawPassword, @Nullable List<String> authorities) {
        Optional<Requestor> requestorOptional = requestorJdbcRepository.findByUsername(username);

        if (requestorOptional.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        Requestor requestor = requestorJdbcRepository.save(createUser(username, rawPassword));

        if (requestor != null && authorities != null) {
            for (String authority : authorities) {
                Role role = roleService.findByAuthority(authority)
                        .orElseGet(() -> roleService.save(authority));
                RequestorRoleId requestorRoleId = new RequestorRoleId(requestor, role);

                if (requestorRoleJdbcRepository.findById(requestorRoleId).isEmpty()) {
                    requestorRoleJdbcRepository.save(new RequestorRole(requestorRoleId));
                }
            }
        }
    }

    private Requestor createUser(String username, String rawPassword) {
        final String encodedPassword = passwordEncoder.encode(rawPassword);

        return new Requestor(null, username, encodedPassword, DEFAULT_ENABLED, DEFAULT_ACCOUNT_EXPIRED,
                DEFAULT_ACCOUNT_LOCKED, DEFAULT_PASSWORD_EXPIRED);
    }
}
