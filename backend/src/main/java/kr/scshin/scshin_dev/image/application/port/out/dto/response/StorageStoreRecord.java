package kr.scshin.scshin_dev.image.application.port.out.dto.response;

import lombok.Builder;

@Builder
public record StorageStoreRecord(String storedName, String relativePath, String serverPath) {
}
