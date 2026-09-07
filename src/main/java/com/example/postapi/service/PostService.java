package com.example.postapi.service;

import com.example.postapi.dto.PostRequest;
import com.example.postapi.exception.ResourceNotFoundException;
import com.example.postapi.model.Post;
import com.example.postapi.repository.PostRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private static final Logger log = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createPost(PostRequest request) {
        Post post = new Post(request.getContent());
        Post saved = postRepository.save(post);
        log.info("Created post with id: {}", saved.getId());
        return saved;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(String id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found with id: " + id));
    }

    public Post updatePost(String id, PostRequest request) {
        Post post = getPostById(id);
        post.setContent(request.getContent());
        return postRepository.save(post);
    }

    public void deletePost(String id) {
        Post post = getPostById(id);
        postRepository.deleteById(post.getId());
        log.info("Deleted post with id: {}", id);
    }

    public List<Post> searchPosts(String keyword) {
        return postRepository.findByContentContainingIgnoreCase(keyword);
    }
}
