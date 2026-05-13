package kr.scshin.scshin_dev.image.application.port.in.dto.response;

import kr.scshin.scshin_dev.image.domain.ImageStatus;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record PostImageReadResponse(
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
