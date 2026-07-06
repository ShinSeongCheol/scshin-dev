package kr.scshin.scshin_dev.backoffice.application.port.in.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostReadDetailResponse(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        Long views,
        List<Long> categoryIds
) {}
