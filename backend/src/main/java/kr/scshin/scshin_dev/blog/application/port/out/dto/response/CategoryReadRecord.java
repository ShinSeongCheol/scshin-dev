package kr.scshin.scshin_dev.blog.application.port.out.dto.response;

import kr.scshin.scshin_dev.blog.adapter.out.persistence.CategoryJpaEntity;
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
    public static CategoryReadRecord from(CategoryJpaEntity categoryJpaEntity) {
        return CategoryReadRecord.builder()
                .id(categoryJpaEntity.getId())
                .parentCategoryId(categoryJpaEntity.getParentCategoryId())
                .categoryName(categoryJpaEntity.getCategoryName())
                .description(categoryJpaEntity.getDescription())
                .slug(categoryJpaEntity.getSlug())
                .useYn(categoryJpaEntity.getUseYn())
                .sortOrder(categoryJpaEntity.getSortOrder())
                .depth(categoryJpaEntity.getDepth())
                .createdAt(categoryJpaEntity.getCreatedAt())
                .updatedAt(categoryJpaEntity.getUpdatedAt())
                .build();
    }
}
