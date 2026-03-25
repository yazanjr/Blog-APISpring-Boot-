package com.devtiro.blog.repositroies;

import com.devtiro.blog.domain.enities.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findAllByCategoryIdAndAuthorEmail(UUID categoryid, String email);
    List<Post> findAllByTagsIdAndAuthorEmail(UUID tagid, String email);
    List<Post> findByAuthorEmail(String email);
    Post findByIdAndAuthorEmail(UUID id, String email);
}



