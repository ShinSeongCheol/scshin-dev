package kr.scshin.scshin_dev.backoffice.application.port.out.dto.response;

import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryReadResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoryReadRecord(
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
    public static CategoryReadRecord from(CategoryReadResponse categoryReadResponse) {
        return CategoryReadRecord.builder()
                .id(categoryReadResponse.id())
                .parentCategoryId(categoryReadResponse.parentCategoryId())
                .categoryName(categoryReadResponse.categoryName())
                .slug(categoryReadResponse.slug())
                .description(categoryReadResponse.description())
                .sortOrder(categoryReadResponse.sortOrder())
                .depth(categoryReadResponse.depth())
                .useYn(categoryReadResponse.useYn())
                .createdAt(categoryReadResponse.createdAt())
                .updatedAt(categoryReadResponse.updatedAt())
                .build();
    }
}
