package kr.scshin.scshin_dev.blog.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Post(
        Long id,
        String title,
        String content,
        Long authorId,
        LocalDateTime createdAt,
        LocalDateTime updateAt
) {
}
