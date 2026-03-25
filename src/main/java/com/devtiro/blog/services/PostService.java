package com.devtiro.blog.services;


import com.devtiro.blog.domain.dtod.CreatePostRequest;
import com.devtiro.blog.domain.dtod.PostDto;
import com.devtiro.blog.domain.enities.Post;


import java.util.List;
import java.util.UUID;

public interface PostService {
    List<PostDto> getAllPosts();
    PostDto getPostById(UUID postId);
    PostDto createPost(CreatePostRequest createPostRequest);
    List<PostDto> getPostsByCategory(UUID categoryId);
    List<PostDto> getPostsByTagId(UUID tagId);
}
