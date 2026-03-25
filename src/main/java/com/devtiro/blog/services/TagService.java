package com.devtiro.blog.services;

import com.devtiro.blog.domain.dtod.CreateTagRequest;
import com.devtiro.blog.domain.dtod.TagResponse;
import com.devtiro.blog.domain.enities.Tag;

import java.util.List;

public interface TagService {
    List<TagResponse> getTags();
    TagResponse SaveTag(CreateTagRequest createTagRequest);
}

