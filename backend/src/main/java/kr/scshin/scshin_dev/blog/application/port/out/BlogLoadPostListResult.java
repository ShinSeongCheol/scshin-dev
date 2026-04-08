package kr.scshin.scshin_dev.blog.application.port.out;

import java.time.LocalDateTime;

public record BlogLoadPostListResult(
    Long id,
    String title,
    String content,
    Long authorId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
