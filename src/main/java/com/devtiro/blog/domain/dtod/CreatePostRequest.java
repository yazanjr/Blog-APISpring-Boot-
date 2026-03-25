package com.devtiro.blog.domain.dtod;

import com.devtiro.blog.domain.PostStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePostRequest {
    private String title;
    private String content;
    private UUID categoryId;
    private Set<UUID> tagIds;
    private PostStatus status;

}
