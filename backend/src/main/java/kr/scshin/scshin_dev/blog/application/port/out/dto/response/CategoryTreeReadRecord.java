package kr.scshin.scshin_dev.blog.application.port.out.dto.response;

import kr.scshin.scshin_dev.blog.adapter.out.persistence.CategoryJpaEntity;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.CategoryTreeReadResponse;
import lombok.Builder;

import java.time.LocalDateTime;

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
        LocalDateTime updatedAt
) {
    public static CategoryTreeReadRecord from(CategoryJpaEntity categoryJpaEntity) {
        return CategoryTreeReadRecord.builder()
                .id(categoryJpaEntity.getId())
                .parentCategoryId(categoryJpaEntity.getParentCategoryId())
                .categoryName(categoryJpaEntity.getCategoryName())
                .slug(categoryJpaEntity.getSlug())
                .description(categoryJpaEntity.getDescription())
                .sortOrder(categoryJpaEntity.getSortOrder())
                .depth(categoryJpaEntity.getDepth())
                .useYn(categoryJpaEntity.getUseYn())
                .createdAt(categoryJpaEntity.getCreatedAt())
                .updatedAt(categoryJpaEntity.getUpdatedAt())
                .build();
    }
}
