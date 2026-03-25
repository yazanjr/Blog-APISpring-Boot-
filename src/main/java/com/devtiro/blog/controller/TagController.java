package com.devtiro.blog.controller;

import com.devtiro.blog.domain.dtod.CreateTagRequest;
import com.devtiro.blog.domain.dtod.TagResponse;
import com.devtiro.blog.services.TagService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tags")
@RequiredArgsConstructor
public class TagController {
    public final TagService tagService;


    @GetMapping
    public ResponseEntity<List<TagResponse>> getTags() {
        return ResponseEntity.ok(tagService.getTags());
    }

    @PostMapping
    public ResponseEntity<TagResponse> createTag(@RequestBody @Valid CreateTagRequest createTagRequest) {
        TagResponse tagResponse = tagService.SaveTag(createTagRequest);
        return ResponseEntity.ok(tagResponse);
    }
}
