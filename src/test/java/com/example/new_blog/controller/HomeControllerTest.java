package com.example.new_blog.controller;

import com.example.new_blog.model.Blog;
import com.example.new_blog.repository.BlogRepository;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;

import java.util.Collections;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;

public class HomeControllerTest {

    @Mock
    private BlogRepository blogRepository;

    @InjectMocks
    private HomeController homeController;

    private MockMvc mockMvc;

    @BeforeMethod
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(homeController)
                  .apply(SecurityMockMvcConfigurers.springSecurity())
                  .build();
    }

    @WithMockUser(username = "user", roles = {"USER"})
    @Test
    public void testHomePage() throws Exception {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Blog> blogs = new PageImpl<>(Collections.emptyList(), pageable, 0);
        given(blogRepository.findAll(pageable)).willReturn(blogs);

        mockMvc.perform(get("/home.html"))
               .andExpect(status().isOk())
               .andExpect(view().name("home.html"));

        verify(blogRepository).findAll(pageable);
    }

    @Test
    public void testBlogDetail() throws Exception {
        Long blogId = 1L;
        Blog blog = new Blog();
        blog.setId(blogId);
        blog.setTitle("Test Blog");
        given(blogRepository.findById(blogId)).willReturn(java.util.Optional.of(blog));

        mockMvc.perform(get("/blogs/{id}", blogId))
               .andExpect(status().isOk())
               .andExpect(view().name("blogDetail.html"));

        verify(blogRepository).findById(blogId);
    }

    @Test
    public void testBlogDetailNotFound() throws Exception {
        Long blogId = 1L;
        given(blogRepository.findById(blogId)).willReturn(java.util.Optional.empty());

        mockMvc.perform(get("/blogs/{id}", blogId))
               .andExpect(status().isNotFound());
    }
    
    @Test
    public void testHomePageUnauthenticated() throws Exception {
        mockMvc.perform(get("/home.html"))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("http://localhost/login.html"));
    }

    @Test
    public void testBlogDetailUnauthenticated() throws Exception {
        Long blogId = 1L;
        mockMvc.perform(get("/blogs/{id}", blogId))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("http://localhost/login.html"));
    }
        
}



