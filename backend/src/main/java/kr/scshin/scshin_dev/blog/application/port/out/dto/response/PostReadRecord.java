package kr.scshin.scshin_dev.blog.application.port.out.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostReadRecord(
    Long id,
    String title,
    String content,
    Long authorId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    Long views
) {
}
