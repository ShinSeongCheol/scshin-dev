package kr.scshin.scshin_dev.image.adapter.out.storage;

import kr.scshin.scshin_dev.image.application.port.out.StoragePort;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.StorageStoreRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.StorageStoreRecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@Component
public class LocalStorageAdapter implements StoragePort {

    @Value("${file.upload-dir}")
    private String uploadDir;

    @Override
    public StorageStoreRecord store(StorageStoreRecordCommand storageStoreRecordCommand) {
        try {
            String relativePath = storageStoreRecordCommand.relativePath();
            String storedName = storageStoreRecordCommand.storedName();
            MultipartFile multipartFile = storageStoreRecordCommand.multipartFile();

            // 1. 전체 경로 생성 (baseDir + 날짜경로)
            Path baseRoot = Paths.get(uploadDir).toAbsolutePath().normalize();
            Path directoryPath = baseRoot.resolve(relativePath).normalize();

            // 2. 폴더가 없으면 생성
            if (!Files.exists(directoryPath)) {
                Files.createDirectories(directoryPath);
            }

            // 3. 파일 저장
            Path filePath = directoryPath.resolve(storedName);
            multipartFile.transferTo(filePath.toFile());

            return StorageStoreRecord.builder()
                    .storedName(storedName)
                    .relativePath(relativePath)
                    .serverPath(directoryPath.toString())
                    .build();

        } catch (IOException e) {
            throw new RuntimeException("파일 저장 중 오류가 발생했습니다.", e);
        }
    }
}
