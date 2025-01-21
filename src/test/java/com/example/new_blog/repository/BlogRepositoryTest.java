package com.example.new_blog.repository;

import com.example.new_blog.model.Blog;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class BlogRepositoryTest {

    @Autowired
    private BlogRepository blogRepository;

    @Test
    public void testSaveBlog() {
        Blog blog = new Blog();
        blog.setTitle("Test Blog");
        blog.setContent("This is a test blog.");
        blog.setSummary("Test summary");
        blog.setMemberOnly(false);

        Blog savedBlog = blogRepository.save(blog);

        assertThat(savedBlog).isNotNull();
        assertThat(savedBlog.getId()).isNotNull();
        assertThat(savedBlog.getTitle()).isEqualTo("Test Blog");
    }
}

