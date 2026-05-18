package kr.scshin.scshin_dev.backoffice.application.port.in.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostReadResponse(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long views
) {}
