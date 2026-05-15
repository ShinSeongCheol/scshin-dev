package kr.scshin.scshin_dev.blog.adapter.in.web.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostResponse(
        Long id,
        String title,
        String content,
        LocalDateTime createdAt,
        String thumbnailUrl
) {
}
