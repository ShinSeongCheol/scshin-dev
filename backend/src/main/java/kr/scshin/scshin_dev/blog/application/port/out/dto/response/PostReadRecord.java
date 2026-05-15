package kr.scshin.scshin_dev.blog.application.port.out.dto.response;

import java.time.LocalDateTime;

public record PostReadRecord(
    Long id,
    String title,
    String content,
    Long authorId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {
}
