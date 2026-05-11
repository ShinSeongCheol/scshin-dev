package kr.scshin.scshin_dev.blog.application.port.out.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostCreateRecord(Long id, String title, String content, Long authorId, LocalDateTime createdAt, LocalDateTime updatedAt) {
}
