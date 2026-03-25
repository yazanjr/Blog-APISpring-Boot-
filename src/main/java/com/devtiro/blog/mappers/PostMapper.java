package com.devtiro.blog.mappers;

import com.devtiro.blog.domain.dtod.CreatePostRequest;
import com.devtiro.blog.domain.dtod.PostDto;
import com.devtiro.blog.domain.enities.Category;
import com.devtiro.blog.domain.enities.Post;
import com.devtiro.blog.domain.enities.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PostMapper {
    Post toPost(PostDto postDto);

    @Mapping(target = "tagIds" , source = "tags" ,qualifiedByName = "mapTag" )
    @Mapping(target = "categoryId" , source = "category" ,qualifiedByName = "mapCategory" )
    PostDto toPostDto(Post post);

    @Named("mapTag")
    default List<UUID> mapTag(Set<Tag> tags){
        if (tags == null) {
            return null;
        }

        return tags.stream()
                .map(Tag::getId)
                .toList();
    }

    @Named("mapCategory")
    default UUID mapCategory(Category category){
        return category.getId();
    }

}
