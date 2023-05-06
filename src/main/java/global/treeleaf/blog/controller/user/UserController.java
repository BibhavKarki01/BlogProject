package global.treeleaf.blog.controller.user;

import global.treeleaf.blog.entity.User;
import global.treeleaf.blog.usecase.user.UserUseCase;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MutableHttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

@Controller("/users")
public class UserController {

    private final UserUseCase userUseCase;

    public UserController(UserUseCase userUseCase) {
        this.userUseCase = userUseCase;
    }

    @Post("/register")
    public HttpResponse<User> registerUser(@Body @Valid User user) {
        User savedUser = userUseCase.registerUser(user);
        return HttpResponse.created(savedUser);
    }

    @Post("/login")
    public <LoggedInUser, LoginRequest> MutableHttpResponse<Object> loginUser(@Body @Valid LoginRequest loginRequest) {
        LoggedInUser loggedInUser = userUseCase.loginUser(loginRequest);
        if (loggedInUser != null) {
            return HttpResponse.ok(loggedInUser);
        } else {
            return HttpResponse.unauthorized();
        }
    }

    @Get("/{id}")
    public HttpResponse<User> getUserById(@PathVariable Long id) {
        User user = userUseCase.getUserBYId(id);
        if (user != null) {
            return HttpResponse.ok(user);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Put("/{id}")
    public HttpResponse<User> updateUser(@PathVariable Long id, @Body @Valid User user) {
        User existingUser = userUseCase.getUserById(id);
        if (existingUser != null) {
            User updatedUser = userUseCase.saveUser(user);
            return HttpResponse.ok(updatedUser);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete ("/{id}")
    public HttpResponse<Void> deleteUserById(@PathVariable Long id) {
        User existingUser = userUseCase.getUserById(id);
        if (existingUser != null) {
            userUseCase.deleteUserById(id);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }
}

