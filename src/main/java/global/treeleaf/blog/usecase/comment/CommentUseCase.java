package global.treeleaf.blog.usecase.comment;

import global.treeleaf.blog.entity.BlogPost;
import global.treeleaf.blog.entity.Comment;
import global.treeleaf.blog.repository.comment.CommentRepository;
import io.micronaut.validation.Validated;
import jakarta.inject.Singleton;
import jakarta.validation.Valid;

import java.util.Optional;


@Singleton
@Validated
public class CommentUseCase {

    private final CommentRepository commentRepository;

    public CommentUseCase(CommentRepository commentRepository) {

        this.commentRepository = commentRepository;
    }
    public Optional<Comment> getAllCommentsByBlogPost(BlogPost blogPost) {
        return commentRepository.findAllByBlogPost(blogPost);
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    public Comment saveComment(@Valid Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteCommentById(Long id) {
        commentRepository.deleteById(id);
    }


}



