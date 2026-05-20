package kr.scshin.scshin_dev.backoffice.application.port.out.dto.response;

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
}
