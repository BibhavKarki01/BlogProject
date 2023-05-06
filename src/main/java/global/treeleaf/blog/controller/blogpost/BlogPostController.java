package global.treeleaf.blog.controller.blogpost;

import global.treeleaf.blog.entity.BlogPost;
import global.treeleaf.blog.usecase.comment.CommentUseCase;
import global.treeleaf.blog.usecase.blogpost.BlogPostUseCase;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.validation.Valid;

@Controller("/posts")
public class BlogPostController {
        private final BlogPostUseCase blogPostUseCase;

        public BlogPostController(BlogPostUseCase blogPostUseCase) {
            this.blogPostUseCase = blogPostUseCase;
        }

    @Get("/blogPost")
    public Iterable<BlogPost> getAllBlogPosts() {
        return blogPostUseCase.getAllBlogPosts();
    }

    @Get("/{id}")
    public BlogPost getBlogPostById(@PathVariable Long id) {
        return blogPostUseCase.getBlogPostById(id);
    }

    @Post("/saveBlogPost")
    public HttpResponse<BlogPost> saveBlogPost(@Body @Valid BlogPost blogPost) {
        BlogPost savedBlogPost = blogPostUseCase.saveBlogPost(blogPost);
        return HttpResponse.created(savedBlogPost);
    }

    @Put("/{id}")
    public HttpResponse<BlogPost> updateBlogPost(@PathVariable Long id, @Body @Valid BlogPost blogPost) {
        BlogPost existingBlogPost = blogPostUseCase.getBlogPostById(id);
        if (existingBlogPost != null) {
            BlogPost updatedBlogPost = blogPostUseCase.saveBlogPost(blogPost);
            return HttpResponse.ok(updatedBlogPost);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{id}")
    public HttpResponse<Void> deleteBlogPostById(@PathVariable Long id) {
        BlogPost existingBlogPost = blogPostUseCase.getBlogPostById(id);
        if (existingBlogPost != null) {
            blogPostUseCase.deleteBlogPostById(id);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }
}


