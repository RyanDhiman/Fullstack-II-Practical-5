package com.example.postapi.controller;

import com.example.postapi.dto.ApiResponse;
import com.example.postapi.dto.PostRequest;
import com.example.postapi.model.Post;
import com.example.postapi.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Post>> createPost(@Valid @RequestBody PostRequest request) {
        Post post = postService.createPost(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>("success", "Post created", post));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<Post>>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return ResponseEntity.ok(new ApiResponse<>("success", "Posts fetched", posts));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> getPostById(@PathVariable String id) {
        Post post = postService.getPostById(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Post fetched", post));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Post>> updatePost(@PathVariable String id,
                                                          @Valid @RequestBody PostRequest request) {
        Post updated = postService.updatePost(id, request);
        return ResponseEntity.ok(new ApiResponse<>("success", "Post updated", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePost(@PathVariable String id) {
        postService.deletePost(id);
        return ResponseEntity.ok(new ApiResponse<>("success", "Post deleted", null));
    }

    // Assignment 1 extension: GET /api/posts/search?keyword=...
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<Post>>> searchPosts(@RequestParam String keyword) {
        List<Post> posts = postService.searchPosts(keyword);
        return ResponseEntity.ok(new ApiResponse<>("success", "Posts matching '" + keyword + "'", posts));
    }
}
