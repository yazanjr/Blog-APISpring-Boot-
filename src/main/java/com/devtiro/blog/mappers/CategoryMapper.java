package com.devtiro.blog.mappers;

import com.devtiro.blog.domain.PostStatus;
import com.devtiro.blog.domain.dtod.CategoryDto;
import com.devtiro.blog.domain.dtod.CreateCategoryRequest;
import com.devtiro.blog.domain.enities.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;
import com.devtiro.blog.domain.enities.Post;

import java.util.List;

@Mapper(componentModel = "spring" ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {

    @Mapping(target = "postCount", source = "posts", qualifiedByName = "calculatePostCount")
    CategoryDto toDto (Category category);

    Category toEntity (CreateCategoryRequest createCategoryRequest);

    @Named("calculatePostCount")
    default long calculatePostCount (List<Post> posts) {
        if (null == posts){
            return 0;
        }

       return posts.stream().filter(post -> PostStatus.PUPLISHED.equals(post.getClass())).count();
    }
}
