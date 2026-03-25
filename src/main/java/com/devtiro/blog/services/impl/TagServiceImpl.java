package com.devtiro.blog.services.impl;

import com.devtiro.blog.domain.dtod.CreateTagRequest;
import com.devtiro.blog.domain.dtod.TagResponse;
import com.devtiro.blog.domain.enities.Tag;
import com.devtiro.blog.mappers.TagMapper;
import com.devtiro.blog.repositroies.TagRepository;
import com.devtiro.blog.services.TagService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;
    private final TagMapper tagMapper;

    @Override
    public List<TagResponse> getTags() {
        return tagRepository.findAllWithPostCount().stream().map(tagMapper::toResponse).toList();
    }

    @Override
    @Transactional
    public TagResponse SaveTag(CreateTagRequest createTagRequest) {
        Tag tag = tagMapper.toEnity(createTagRequest);
        if(!tagRepository.existsByName(tag.getName())) {
            tagRepository.save(tag);
        }
        return tagMapper.toResponse(tag);
    }
}
