package global.treeleaf.blog.controller.comment;

import global.treeleaf.blog.entity.BlogPost;
import global.treeleaf.blog.usecase.comment.CommentUseCase;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

import javax.xml.stream.events.Comment;
import java.util.Optional;

@Controller("/comments")
public class CommentController {
    private final CommentUseCase commentUseCase;

    public CommentController(CommentUseCase commentUseCase) {
        this.commentUseCase = commentUseCase;
    }

    @Get("/{blogPostId}")
    public Optional<global.treeleaf.blog.entity.Comment> getAllCommentsByBlogPost(@PathVariable Long blogPostId) {
        BlogPost blogPost = new BlogPost();
        blogPost.setId(blogPostId);
        return commentUseCase.getAllCommentsByBlogPost(blogPost);
    }
    @Get("/{id}")
    public HttpResponse<Comment> getCommentById(@PathVariable Long id) {
        Comment comment = (Comment) commentUseCase.getCommentById(id);
        if (comment != null) {
            return HttpResponse.ok(comment);
        } else {
            return HttpResponse.notFound();
        }
    }
    @Post
    public HttpResponse<Comment> saveComment(@Body @Valid Comment comment) {
        Comment savedComment = commentUseCase.save(comment);
        return HttpResponse.created(savedComment);
    }

    @Put("/{id}")
    public HttpResponse<Comment> updateComment(@PathVariable Long id, @Body @Valid Comment comment) {
        Comment existingComment = (Comment) commentUseCase.getCommentById(id);
        if (existingComment != null) {
            Comment updatedComment = (Comment) commentUseCase.saveComment(comment);
            return HttpResponse.ok(updatedComment);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteCommentById(@PathVariable Long id) {
        Comment existingComment = (Comment) commentUseCase.getCommentById(id);
        if (existingComment != null) {
            commentUseCase.deleteCommentById(id);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }
}



