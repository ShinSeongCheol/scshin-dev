package kr.scshin.scshin_dev.backoffice.application.port.out.dto.request;

import lombok.Builder;

@Builder
public record CategoryCreateRecordCommand(
        Long parentCategoryId,
        String categoryName,
        String slug,
        String description,
        char useYn
) {
}
