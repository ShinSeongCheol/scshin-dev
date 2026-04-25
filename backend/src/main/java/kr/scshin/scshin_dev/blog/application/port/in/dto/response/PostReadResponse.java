package kr.scshin.scshin_dev.blog.application.port.in.dto.response;

import java.time.LocalDateTime;

public record PostReadResponse(
    Long id,
    String title,
    String content,
    Long authorId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) { }
