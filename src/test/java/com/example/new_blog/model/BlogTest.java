package com.example.new_blog.model;

import org.testng.annotations.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BlogTest {

    @Test
    public void testBlogEntity() {
        Blog blog = new Blog();
        blog.setTitle("Test Blog");
        blog.setContent("This is a test blog.");
        blog.setSummary("Test summary");
        blog.setMemberOnly(false);
        blog.setAuthor("testUser");

        assertThat(blog.getTitle()).isEqualTo("Test Blog");
        assertThat(blog.getContent()).isEqualTo("This is a test blog.");
        assertThat(blog.getSummary()).isEqualTo("Test summary");
        assertThat(blog.isMemberOnly()).isFalse();
        assertThat(blog.getAuthor()).isEqualTo("testUser");
    }
}

