package kr.scshin.scshin_dev.blog.application.port.in.dto.response;

import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryTreeReadRecord;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder
public record CategoryTreeReadResponse(
        Long id,
        Long parentCategoryId,
        String categoryName,
        String slug,
        String description,
        int sortOrder,
        int depth,
        char useYn,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<CategoryTreeReadResponse> childrenList
) {
    public static CategoryTreeReadResponse from(CategoryTreeReadRecord categoryTreeReadRecord) {
        return CategoryTreeReadResponse.builder()
                .id(categoryTreeReadRecord.id())
                .parentCategoryId(categoryTreeReadRecord.parentCategoryId())
                .categoryName(categoryTreeReadRecord.categoryName())
                .description(categoryTreeReadRecord.description())
                .slug(categoryTreeReadRecord.slug())
                .sortOrder(categoryTreeReadRecord.sortOrder())
                .depth(categoryTreeReadRecord.depth())
                .useYn(categoryTreeReadRecord.useYn())
                .createdAt(categoryTreeReadRecord.createdAt())
                .updatedAt(categoryTreeReadRecord.updatedAt())
                .childrenList(new ArrayList<>())
                .build();
    }
}
