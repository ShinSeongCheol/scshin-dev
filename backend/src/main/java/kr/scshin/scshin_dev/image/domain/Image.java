package kr.scshin.scshin_dev.image.domain;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record Image(
        Long id,
        String originName,
        String storedName,
        String filePath,
        String extension,
        ImageStatus status,
        Long fileSize,
        Long postId,
        Long userId,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

}
