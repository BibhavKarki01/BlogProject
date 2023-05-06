package global.treeleaf.blog.repository.user;

import global.treeleaf.blog.entity.User;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;

@Repository
public interface UserRepository  extends CrudRepository<User, Long>{
   Optional<User> findByUsername(String username);
}
