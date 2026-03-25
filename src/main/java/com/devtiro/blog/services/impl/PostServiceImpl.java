package com.devtiro.blog.services.impl;

import com.devtiro.blog.domain.PostStatus;
import com.devtiro.blog.domain.dtod.CreatePostRequest;
import com.devtiro.blog.domain.dtod.PostDto;
import com.devtiro.blog.domain.enities.Category;
import com.devtiro.blog.domain.enities.Post;
import com.devtiro.blog.domain.enities.Tag;
import com.devtiro.blog.domain.enities.User;
import com.devtiro.blog.mappers.PostMapper;
import com.devtiro.blog.repositroies.CategoryRepository;
import com.devtiro.blog.repositroies.PostRepository;
import com.devtiro.blog.repositroies.TagRepository;
import com.devtiro.blog.repositroies.UserRepository;
import com.devtiro.blog.services.PostService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final PostMapper postMapper;
    private final CategoryRepository categoryRepository;
    private final TagRepository tagRepository;
    private final UserRepository userRepository;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findByAuthorEmail(this.getAuthtecatedEmail())
                .stream().map(postMapper::toPostDto).toList();

    }

    @Override
    public PostDto getPostById(UUID postId) {
        return postMapper.toPostDto(postRepository.findByIdAndAuthorEmail(postId, this.getAuthtecatedEmail()));
    }


    @Override
    @Transactional
    public PostDto createPost(CreatePostRequest createPostRequest) {
        Category category = categoryRepository
                .findById(createPostRequest.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        Set<Tag> tags = Set.of();

        if (createPostRequest.getTagIds() != null && !createPostRequest.getTagIds().isEmpty()) {
            tags = createPostRequest.getTagIds()
                    .stream()
                    .map(id -> tagRepository.findById(id)
                            .orElseThrow(() -> new RuntimeException("Tag not found: " + id)))
                    .collect(Collectors.toSet());
        }
        User user = userRepository.findByEmail(this.getAuthtecatedEmail())
                .orElseThrow(() -> new RuntimeException("User not found"));

        Post post =  Post.builder().title(createPostRequest.getTitle())
                .content(createPostRequest.getContent())
                .author(user)
                .category(category)
                .tags(tags)
                .status(PostStatus.DRAFT)
                .readingTime(1000)
                .build();

        return postMapper.toPostDto(postRepository.save(post));


    }

    @Override
    public List<PostDto> getPostsByCategory(UUID categoryId) {
       return postRepository.findAllByCategoryIdAndAuthorEmail(categoryId, this.getAuthtecatedEmail()).stream().map(postMapper::toPostDto).toList();
    }

    @Override
    public List<PostDto> getPostsByTagId(UUID tagId) {
        return postRepository.findAllByTagsIdAndAuthorEmail(tagId, this.getAuthtecatedEmail()).stream().map(postMapper::toPostDto).toList();

    }

    private String getAuthtecatedEmail() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
