package kr.scshin.scshin_dev.blog.application.port.out.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoryPostReadRecord(
        String categoryId,
        Long postId,
        LocalDateTime createdAt
) {
}
