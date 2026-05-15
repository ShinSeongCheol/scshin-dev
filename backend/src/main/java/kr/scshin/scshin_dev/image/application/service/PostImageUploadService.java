package kr.scshin.scshin_dev.image.application.service;

import kr.scshin.scshin_dev.image.application.port.in.PostImageUploadUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageUploadRequest;
import kr.scshin.scshin_dev.image.application.port.in.dto.response.PostImageUploadResponse;
import kr.scshin.scshin_dev.image.application.port.out.PostImageCreatePort;
import kr.scshin.scshin_dev.image.application.port.out.StoragePort;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageCreateRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.StorageStoreRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.PostImageCreateRecord;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.StorageStoreRecord;
import kr.scshin.scshin_dev.image.domain.Image;
import kr.scshin.scshin_dev.image.domain.ImageStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostImageUploadService implements PostImageUploadUseCase{

    private final PostImageCreatePort postImageCreatePort;
    private final StoragePort storagePort;

    @Override
    public PostImageUploadResponse uploadImage(PostImageUploadRequest postImageUploadRequest) {
        MultipartFile multipartFile = postImageUploadRequest.multipartFile();
        String originName = multipartFile.getOriginalFilename();
        String extension = originName.substring(originName.lastIndexOf("."));
        String storedName = UUID.randomUUID() + extension;
        Long fileSize = multipartFile.getSize();

        Long userId = postImageUploadRequest.userId();

        // 파일 저장
        String datePath = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        StorageStoreRecordCommand storageStoreRecordCommand = StorageStoreRecordCommand.builder()
                .multipartFile(multipartFile)
                .storedName(storedName)
                .relativePath(datePath)
                .build();

        StorageStoreRecord storageStoreRecord = storagePort.store(storageStoreRecordCommand);

        // 도메인 객체 생성
        Image image = Image.builder()
                .originName(originName)
                .storedName(storedName)
                .filePath(storageStoreRecord.relativePath())
                .extension(extension)
                .status(ImageStatus.TEMPORARY)
                .fileSize(fileSize)
                .userId(userId)
                .build();

        // 저장 요청 생성
        PostImageCreateRecordCommand postImageCreateRecordCommand = PostImageCreateRecordCommand.builder()
                .orignName(image.originName())
                .storedName(image.storedName())
                .filePath(image.filePath())
                .extension(image.extension())
                .status(image.status())
                .fileSize(image.fileSize())
                .userId(userId)
                .build();

        PostImageCreateRecord postImageCreateRecord = postImageCreatePort.createPostImage(postImageCreateRecordCommand);
        return PostImageUploadResponse.builder()
                .id(postImageCreateRecord.id())
                .originName(postImageCreateRecord.originName())
                .storedName(postImageCreateRecord.storedName())
                .filePath(postImageCreateRecord.filePath())
                .extension(postImageCreateRecord.extension())
                .status(postImageCreateRecord.status())
                .fileSize(postImageCreateRecord.fileSize())
                .postId(postImageCreateRecord.postId())
                .userId(postImageCreateRecord.userId())
                .createdAt(postImageCreateRecord.createdAt())
                .updatedAt(postImageCreateRecord.updatedAt())
                .build();
    }
}
