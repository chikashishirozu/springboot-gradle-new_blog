package com.example.new_blog.service;

import com.example.new_blog.model.Blog;
import com.example.new_blog.repository.BlogRepository;
import org.testng.annotations.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;

public class BlogServiceTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private BlogService blogService;

    @Test
    public void testIsAuthor() {
        Long blogId = 1L;
        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setAuthor("testUser");
        given(blogRepository.findById(blogId)).willReturn(java.util.Optional.of(blog));

        boolean result = blogService.isAuthor(blogId);

        assertThat(result).isTrue();
    }
}

