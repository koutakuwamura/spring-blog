package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Blog;
import com.example.demo.entity.Category;
import com.example.demo.model.Account;
import com.example.demo.repository.BlogRepository;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.UserRepository;

@Controller
public class BlogController {
	@Autowired
	Account account;
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	UserRepository userRepository;

	// タスク一覧表示
	@GetMapping("/blogs")
	public String index(
	        @RequestParam(value = "categoryId", required = false) Integer categoryId,
	        @RequestParam(value = "id", required = false) Integer id,
	        Model model) {

	    // user（1件）を取得
	    if (id != null) {
	        userRepository.findById(id).ifPresent(user -> model.addAttribute("user", user));
	    }

	    // 全カテゴリー一覧を取得
	    List<Category> categoryList = categoryRepository.findAll();
	    model.addAttribute("categories", categoryList);

	    // タスク一覧情報の取得
	    List<Blog> blogList;
	    if (categoryId == null || categoryId == 0) {
	        blogList = blogRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
	    } else {
	        blogList = blogRepository.findByCategoryIdOrderByIdAsc(categoryId);
	    }
	    model.addAttribute("blogs", blogList);

	    return "blogs";
	}


	//新規作成画面の表示
	@GetMapping("/blogs/add")
	public String addTask() {
		return "addblogs";
	}

	//新規作成の実行
	@PostMapping("/blogs/crate")
	public String storeTask(
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "body", defaultValue = "") String body,
			Model model) {
		//Taskオブジェクトの生成
		Blog blog = new Blog(categoryId, title, body);
		//tasksテーブルへの反映
		blogRepository.save(blog);
		//[tasks]にGETでリクエストしなおす
		return "redirect:/blogs";
	}

	//タスク更新画面表示
	@GetMapping("/blogs/{id}/edit")
	public String editTask(@PathVariable("id") Integer id, Model model) {
		// tasksテーブルをID（主キー）で検索
		Blog blog = blogRepository.findById(id).get();
		model.addAttribute("blog", blog);
		return "editblogs";
	}

	//タスク更新の実行
	@PostMapping("/blogs/{id}/update")
	public String update(
			@PathVariable("id") Integer id,
			@RequestParam(name = "categoryId", defaultValue = "") Integer categoryId,
			@RequestParam(name = "title", defaultValue = "") String title,
			@RequestParam(name = "body", defaultValue = "") String body,
			Model model) {
		//Taskオブジェクトの生成
		Blog blog = new Blog(id,categoryId, title, body);
		//tasksテーブルへの反映
		blogRepository.save(blog);
		//[tasks]にGETでリクエストしなおす
		return "redirect:/blogs";

	}
	// 削除画面表示
	@GetMapping("/blogs/{id}/delete")
	public String confirmDelete(@PathVariable("id") Integer id, Model model) {
		Blog blog = blogRepository.findById(id).orElse(null);
	    model.addAttribute("blog", blog);
	    return "deleteblogs";  // → この名前のHTMLテンプレートを表示
	}
	// 削除処理
			@PostMapping("/blogs/{id}/delete")
			public String delete(@PathVariable("id") Integer id, Model model) {

				// itemsテーブルから削除（DELETE）
				blogRepository.deleteById(id);
				// 「/items」にGETでリクエストし直す（リダイレクト）
				return "redirect:/blogs";
			}

}
