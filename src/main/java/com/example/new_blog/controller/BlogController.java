package com.example.new_blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.new_blog.model.Blog;
import com.example.new_blog.repository.BlogRepository;
import com.example.new_blog.service.BlogService;

@Controller
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private BlogService blogService;

    @GetMapping("/new")
    public String newBlog(Model model) {
        model.addAttribute("blog", new Blog());
        return "blogs/form";
    }

    @PostMapping
    public String createBlog(@ModelAttribute Blog blog) {
        blogRepository.save(blog);
        return "redirect:/home";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("@blogService.isAuthor(#id)")
    public String editBlog(@PathVariable Long id, Model model) {
        model.addAttribute("blog", blogRepository.findById(id).orElse(null));
        return "blogs/form";
    }

    @PostMapping("/{id}")
    @PreAuthorize("@blogService.isAuthor(#id)")
    public String updateBlog(@PathVariable Long id, @ModelAttribute Blog blog) {
        blogRepository.save(blog);
        return "redirect:/home";
    }

    @GetMapping("/{id}/delete")
    @PreAuthorize("@blogService.isAuthor(#id)")
    public String deleteBlog(@PathVariable Long id) {
        blogRepository.deleteById(id);
        return "redirect:/home";
    }
}

