package kr.scshin.scshin_dev.backoffice.application.port.out.dto.response;

import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryTreeReadResponse;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CategoryTreeReadRecord(
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
        List<CategoryTreeReadRecord> childrenList
) {
    public static CategoryTreeReadRecord from(CategoryTreeReadResponse categoryTreeReadResponse) {
        return CategoryTreeReadRecord.builder()
                .id(categoryTreeReadResponse.id())
                .parentCategoryId(categoryTreeReadResponse.parentCategoryId())
                .categoryName(categoryTreeReadResponse.categoryName())
                .slug(categoryTreeReadResponse.slug())
                .description(categoryTreeReadResponse.description())
                .sortOrder(categoryTreeReadResponse.sortOrder())
                .depth(categoryTreeReadResponse.depth())
                .useYn(categoryTreeReadResponse.useYn())
                .createdAt(categoryTreeReadResponse.createdAt())
                .updatedAt(categoryTreeReadResponse.updatedAt())
                .childrenList(categoryTreeReadResponse.childrenList().stream().map(CategoryTreeReadRecord::from).toList())
                .build();
    }
}
