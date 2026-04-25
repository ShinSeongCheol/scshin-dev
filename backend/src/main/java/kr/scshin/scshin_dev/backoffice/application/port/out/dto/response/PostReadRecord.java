package kr.scshin.scshin_dev.backoffice.application.port.out.dto.response;

import java.time.LocalDateTime;

public record PostReadRecord(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) { }
