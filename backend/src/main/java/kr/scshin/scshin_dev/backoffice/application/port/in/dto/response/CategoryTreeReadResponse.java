package kr.scshin.scshin_dev.backoffice.application.port.in.dto.response;

import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryTreeReadRecord;
import lombok.Builder;

import java.time.LocalDateTime;
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
                .slug(categoryTreeReadRecord.slug())
                .description(categoryTreeReadRecord.description())
                .sortOrder(categoryTreeReadRecord.sortOrder())
                .depth(categoryTreeReadRecord.depth())
                .useYn(categoryTreeReadRecord.useYn())
                .createdAt(categoryTreeReadRecord.createdAt())
                .updatedAt(categoryTreeReadRecord.updatedAt())
                .childrenList(categoryTreeReadRecord.childrenList().stream().map(CategoryTreeReadResponse::from).toList())
                .build();
    }
}
