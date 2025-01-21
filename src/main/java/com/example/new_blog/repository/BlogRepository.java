package com.example.new_blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.new_blog.model.Blog;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {
}

