package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Blog;

public interface BlogRepository extends JpaRepository<Blog, Integer>   {
	// SELECT * FROM items WHERE category_id = ?
	List<Blog> findByCategoryIdOrderByIdAsc(Integer categoryId);
	
}
