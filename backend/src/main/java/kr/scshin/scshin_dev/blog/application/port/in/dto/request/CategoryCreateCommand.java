package kr.scshin.scshin_dev.blog.application.port.in.dto.request;

import lombok.Builder;

@Builder
public record CategoryCreateCommand(
        Long parentCategoryId,
        String categoryName,
        String slug,
        String description,
        char useYn
) {
}
