package global.treeleaf.blog.repository.comment;

import global.treeleaf.blog.entity.BlogPost;
import global.treeleaf.blog.entity.Comment;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;

import java.util.Optional;


@Repository
public interface CommentRepository  extends CrudRepository<Comment, Long> {
    Optional<Comment> findAllByBlogPost(BlogPost blogPost);
}
