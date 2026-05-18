package kr.scshin.scshin_dev.blog.application.port.in.dto.response;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record PostReadResponse(
    Long id,
    String title,
    String content,
    Long authorId,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<String> imageUrls,
    Long views
) { }
