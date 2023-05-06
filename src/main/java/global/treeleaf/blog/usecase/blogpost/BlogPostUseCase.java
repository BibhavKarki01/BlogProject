package global.treeleaf.blog.usecase.blogpost;



import global.treeleaf.blog.entity.BlogPost;
import global.treeleaf.blog.repository.blogpost.BlogPostRepository;
import io.micronaut.validation.Validated;
import jakarta.inject.Singleton;


import javax.validation.constraints.NotNull;
import java.util.Optional;

@Singleton
@Validated
public class BlogPostUseCase {

   private final BlogPostRepository blogPostRepository;

    public BlogPostUseCase(BlogPostRepository blogPostRepository) {
        this.blogPostRepository = blogPostRepository;
    }

    public Iterable<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    public BlogPost getBlogPostById(Long id) {
        return blogPostRepository.findById(id).orElse(null);
    }

    public BlogPost saveBlogPost(BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    public void deleteBlogPostById(Long id) {
        blogPostRepository.deleteById(id);
    }
}
