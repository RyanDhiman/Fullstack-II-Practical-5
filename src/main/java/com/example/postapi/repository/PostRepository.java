package com.example.postapi.repository;

import com.example.postapi.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PostRepository extends MongoRepository<Post, String> {

    // save(), findById(), findAll(), deleteById() are provided automatically by MongoRepository.

    // Custom query method used by Assignment 1 (search endpoint).
    List<Post> findByContentContainingIgnoreCase(String keyword);
}
