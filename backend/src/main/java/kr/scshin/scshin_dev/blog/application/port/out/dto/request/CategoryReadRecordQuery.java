package kr.scshin.scshin_dev.blog.application.port.out.dto.request;

import lombok.Builder;

@Builder
public record CategoryReadRecordQuery(
        Long parentCategoryId
) {
}
