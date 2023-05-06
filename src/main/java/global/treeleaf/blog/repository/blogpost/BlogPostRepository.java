package global.treeleaf.blog.repository.blogpost;

import global.treeleaf.blog.entity.BlogPost;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

@Repository
public interface BlogPostRepository extends CrudRepository<BlogPost,Long> {
}
