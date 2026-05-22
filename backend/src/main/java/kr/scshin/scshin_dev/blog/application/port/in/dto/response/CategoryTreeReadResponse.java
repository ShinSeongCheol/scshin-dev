package kr.scshin.scshin_dev.blog.application.port.in.dto.response;

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
}
