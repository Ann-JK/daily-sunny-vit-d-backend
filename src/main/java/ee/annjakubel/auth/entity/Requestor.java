package ee.annjakubel.auth.entity;

import ee.annjakubel.auth.UserState;
import io.micronaut.data.annotation.GeneratedValue;
import io.micronaut.data.annotation.Id;
import io.micronaut.data.annotation.MappedEntity;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;

@MappedEntity
public record Requestor(@Nullable
                        @Id
                        @GeneratedValue
                        Long id,
                        @NotBlank
                        String username,
                        @NotBlank
                        String password,
                        boolean enabled,
                        boolean accountExpired,
                        boolean accountLocked,
                        boolean passwordExpired) implements UserState {

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public boolean isAccountExpired() {
        return accountExpired;
    }

    @Override
    public boolean isAccountLocked() {
        return accountLocked;
    }

    @Override
    public boolean isPasswordExpired() {
        return false;
    }
}
