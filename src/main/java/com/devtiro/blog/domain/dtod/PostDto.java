package com.devtiro.blog.domain.dtod;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PostDto {
    private String title;
    private String content;
    private UUID categoryId;
    private List<UUID> tagIds;
}
