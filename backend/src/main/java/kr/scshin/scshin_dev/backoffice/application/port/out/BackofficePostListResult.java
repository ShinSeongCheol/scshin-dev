package kr.scshin.scshin_dev.backoffice.application.port.out;

import java.time.LocalDateTime;

public record BackofficePostListResult(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
