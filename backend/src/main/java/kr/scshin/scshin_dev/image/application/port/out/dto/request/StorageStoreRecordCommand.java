package kr.scshin.scshin_dev.image.application.port.out.dto.request;

import lombok.Builder;
import org.springframework.web.multipart.MultipartFile;

@Builder
public record StorageStoreRecordCommand(MultipartFile multipartFile, String storedName, String relativePath) {
}
