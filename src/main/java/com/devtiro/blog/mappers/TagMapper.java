package com.devtiro.blog.mappers;

import com.devtiro.blog.domain.PostStatus;
import com.devtiro.blog.domain.dtod.CreateTagRequest;
import com.devtiro.blog.domain.dtod.TagResponse;
import com.devtiro.blog.domain.enities.Post;
import com.devtiro.blog.domain.enities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TagMapper {

    Tag toEnity(CreateTagRequest createTagRequest);

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    TagResponse toResponse(Tag tag);

    @Named("calculatePostCount")
    default Integer calculatePostCount(Set<Post> posts){
        if(posts==null || posts.isEmpty()){
            return 0;
        }
        return (int) posts.stream().filter(post-> PostStatus.PUPLISHED.equals(post.getStatus())).count();
    }
}
