package global.treeleaf.blog.configuration;

import global.treeleaf.blog.entity.User;
import global.treeleaf.blog.repository.user.UserRepository;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.security.annotation.Secured;
import io.micronaut.security.authentication.AuthenticationProvider;

import io.micronaut.security.rules.SecurityRule;
import io.micronaut.security.token.jwt.config.JwtConfiguration;

import io.micronaut.security.token.jwt.render.AccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerAccessRefreshToken;
import io.micronaut.security.token.jwt.render.BearerTokenRenderer;
import io.micronaut.security.token.jwt.validator.JwtTokenValidator;
import jakarta.inject.Singleton;


import java.util.Optional;
import java.util.function.Function;

@Factory
public class SecurityConfiguration {

    @Singleton
    @Bean
    @Secured(SecurityRule.IS_ANONYMOUS)
    public Function<String, UserDetails> userDetailsFetcher(UserRepository userRepository) {
        return (username) -> userRepository.findByUsername(username)
                .map(user -> new UserDetails(
                        user.getUsername(),
                        user.getRoles()
                ))
                .orElse(null);
    }

    @Singleton
    public JwtConfiguration jwtConfiguration() {
        return new JwtConfiguration() {
            @Override
            public boolean isEnabled() {
                return false;
            }

            public String getSecret() {
                return "supersecretkey";
            }


            public int getExpiration() {
                return 3600;
            }
        };
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserRepository userRepository) {
        return (authenticationRequest) -> {
            String username = authenticationRequest.getIdentiy().toString();
            Optional<User> optionalUser = userRepository.findByUsername(username);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                String password = authenticationRequest.getSecret().toString();
                if (password.equals(user.getPassword())) {
                    return Optional.of(new UserDetails(
                            user.getUsername(),
                            user.getRoles()
                    ));
                }
            }
            return Optional.empty();
        };
    }

    @Bean
    public JwtTokenValidator jwtTokenValidator() {
        return new JwtTokenValidator() {
            @Override
            public boolean validateToken(String token) {
                return true;
            }
        };
    }

    @Singleton
    public BearerTokenRenderer bearerTokenRenderer() {
        return new BearerTokenRenderer() {

            public AccessRefreshToken render(BearerAccessRefreshToken bearerAccessRefreshToken) {
                return bearerAccessRefreshToken;
            }
        };
    }
}