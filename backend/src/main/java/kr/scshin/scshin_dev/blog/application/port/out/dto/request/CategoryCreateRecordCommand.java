package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

import lombok.Builder;

@Builder
public record CategoryCreateRecordCommand(
        Long parentCategoryId,
        String categoryName,
        String slug,
        String description,
        char useYn,
        int sortOrder,
        int depth
) {
}
