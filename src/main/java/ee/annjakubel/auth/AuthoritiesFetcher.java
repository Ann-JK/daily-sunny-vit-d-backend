package ee.annjakubel.auth;

import java.util.List;

public interface AuthoritiesFetcher {
    List<String> findAuthoritiesByUsername(String username);
}
