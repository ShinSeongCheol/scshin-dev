package kr.scshin.scshin_dev.backoffice.application.port.in.dto.response;

import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryReadRecord;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoryReadResponse(
        Long id,
        Long parentCategoryId,
        String categoryName,
        String slug,
        String description,
        int sortOrder,
        int depth,
        char useYn,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static CategoryReadResponse from(CategoryReadRecord categoryReadRecord) {
        return CategoryReadResponse.builder()
                .id(categoryReadRecord.id())
                .parentCategoryId(categoryReadRecord.parentCategoryId())
                .categoryName(categoryReadRecord.categoryName())
                .slug(categoryReadRecord.slug())
                .description(categoryReadRecord.description())
                .sortOrder(categoryReadRecord.sortOrder())
                .depth(categoryReadRecord.depth())
                .useYn(categoryReadRecord.useYn())
                .createdAt(categoryReadRecord.createdAt())
                .updatedAt(categoryReadRecord.updatedAt())
                .build();
    }
}
