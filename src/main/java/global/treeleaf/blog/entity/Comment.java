package global.treeleaf.blog.entity;

import io.micronaut.http.annotation.Post;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    private BlogPost blogPost;

    private LocalDateTime createdAt;


    public Comment() {
        this.createdAt = LocalDateTime.now();
    }

    public Comment(Long id, String content, BlogPost blogPost, LocalDateTime createdAt) {
        this.id = id;
        this.content = content;
        this.blogPost = blogPost;
        this.createdAt = createdAt;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}


