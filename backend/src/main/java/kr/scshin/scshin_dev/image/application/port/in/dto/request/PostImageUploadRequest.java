package kr.scshin.scshin_dev.image.application.port.in.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record PostImageUploadRequest(MultipartFile multipartFile, Long userId) {
}
