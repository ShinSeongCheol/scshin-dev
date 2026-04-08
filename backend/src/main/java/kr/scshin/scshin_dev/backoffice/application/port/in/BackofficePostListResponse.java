package kr.scshin.scshin_dev.backoffice.application.port.in;

import java.time.LocalDateTime;

public record BackofficePostListResponse(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
){}
