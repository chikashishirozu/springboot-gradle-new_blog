package com.example.new_blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.new_blog.model.Blog;
import com.example.new_blog.repository.BlogRepository;

@Controller
public class HomeController {

    @Autowired
    private BlogRepository blogRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home.html")
    public String home(Model model, @RequestParam(defaultValue = "0") int page) {
        Pageable pageable = PageRequest.of(page, 10);
        Page<Blog> blogs = blogRepository.findAll(pageable);
        model.addAttribute("blogs", blogs);
        return "home.html";
    }

    @GetMapping("/blogs/{id}")
    public String getBlogDetail(@PathVariable Long id, Model model) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("blog", blog);
        return "blogDetail.html";
    }
}

