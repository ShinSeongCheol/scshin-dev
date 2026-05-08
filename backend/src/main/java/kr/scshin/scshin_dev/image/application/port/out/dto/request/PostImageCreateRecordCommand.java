package kr.scshin.scshin_dev.image.application.port.out.dto.request;

import kr.scshin.scshin_dev.image.domain.ImageStatus;
import lombok.Builder;

@Builder
public record PostImageCreateRecordCommand(
        String orignName,
        String storedName,
        String filePath,
        String extension,
        ImageStatus status,
        Long fileSize,
        Long userId
) {
}
