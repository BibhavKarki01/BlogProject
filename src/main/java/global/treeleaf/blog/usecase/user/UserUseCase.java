package global.treeleaf.blog.usecase.user;

import global.treeleaf.blog.entity.User;
import global.treeleaf.blog.repository.user.UserRepository;
import io.micronaut.security.authentication.AuthenticationException;
import io.micronaut.security.authentication.UsernamePasswordCredentials;
import io.micronaut.security.utils.SecurityService;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import javax.transaction.Transactional;
import java.util.Optional;

@Singleton
public class UserUseCase<UserDetails> {

    private final UserRepository userRepository;
    private final SecurityService securityService;

    public UserUseCase(UserRepository userRepository, SecurityService securityService) {
        this.userRepository = userRepository;
        this.securityService = securityService;
    }

    @Transactional
    public UserDetails login(@NotBlank String username, @NotBlank String password) throws AuthenticationException {
        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(username, password);
        UserDetails userDetails = securityService.authenticate(credentials)
                .orElseThrow(() -> new AuthenticationException("Invalid credentials"));

        return userDetails;
    }

    public Optional<User> findByUsername(@NotNull String username) {
        return userRepository.findByUsername(username);
    }

    public Optional<User> findById(@NotNull Long id) {
        return userRepository.findById(id);
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public boolean deleteById(@NotNull Long id) {
        userRepository.deleteById(id);
        return true;
    }

    public UserDetails getUserDetails() {
        return (UserDetails) securityService.getAuthentication().get();
    }


    public void deleteUserById(Long id) {
    }
    
}





