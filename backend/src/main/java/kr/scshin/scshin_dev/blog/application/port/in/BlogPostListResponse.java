package kr.scshin.scshin_dev.blog.application.port.in;

import java.time.LocalDateTime;

public record BlogPostListResponse(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
