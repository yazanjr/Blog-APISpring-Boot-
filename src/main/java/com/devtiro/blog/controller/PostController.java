package com.devtiro.blog.controller;


import com.devtiro.blog.domain.PostStatus;
import com.devtiro.blog.domain.dtod.CreatePostRequest;
import com.devtiro.blog.domain.dtod.PostDto;
import com.devtiro.blog.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping("/all")
    public ResponseEntity<List<PostDto>> getAllPosts() {
        return ResponseEntity.ok(postService.getAllPosts());
    }

    @GetMapping("/category")
    public ResponseEntity<List<PostDto>> getAllPostsByCategory(@RequestParam UUID category) {
        return ResponseEntity.ok(postService.getPostsByCategory(category));
    }

    @GetMapping("/tag")
    public ResponseEntity<List<PostDto>> getAllPostsByTags(@RequestParam UUID tag) {
        return ResponseEntity.ok(postService.getPostsByTagId(tag));
    }

    @GetMapping
    public ResponseEntity<PostDto> getPostById(@RequestParam UUID  postId) {
        return ResponseEntity.ok(postService.getPostById(postId));
    }

    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody CreatePostRequest createPostRequest) {
        PostDto postDto = postService.createPost(createPostRequest);

        return new ResponseEntity<>(postDto,HttpStatus.CREATED);
    }
}
