package kr.scshin.scshin_dev.image.application.port.out;

import kr.scshin.scshin_dev.image.application.port.out.dto.request.StorageStoreRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.StorageStoreRecord;
import org.springframework.web.multipart.MultipartFile;

public interface StoragePort {
    StorageStoreRecord store(StorageStoreRecordCommand storageStoreRecordCommand);
}
