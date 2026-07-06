package kr.scshin.scshin_dev.blog.application.port.out.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CategoryPostReadRecord(
        Long categoryId,
        Long postId,
        LocalDateTime createdAt
) {
}
