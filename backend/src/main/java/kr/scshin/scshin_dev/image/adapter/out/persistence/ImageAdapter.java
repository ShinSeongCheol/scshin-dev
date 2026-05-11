package kr.scshin.scshin_dev.image.adapter.out.persistence;

import kr.scshin.scshin_dev.image.application.port.out.PostImageCreatePort;
import kr.scshin.scshin_dev.image.application.port.out.PostImageUpdatePort;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageCreateRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageUpdateRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.PostImageCreateRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageAdapter implements PostImageCreatePort, PostImageUpdatePort {

    private final ImageJpaRepository imageJpaRepository;

    @Override
    public PostImageCreateRecord createPostImage(PostImageCreateRecordCommand postImageCreateRecordCommand) {

        ImageEntity postImageEntity = ImageEntity.builder()
                .originName(postImageCreateRecordCommand.orignName())
                .storedName(postImageCreateRecordCommand.storedName())
                .filePath(postImageCreateRecordCommand.filePath())
                .extension(postImageCreateRecordCommand.extension())
                .status(postImageCreateRecordCommand.status())
                .fileSize(postImageCreateRecordCommand.fileSize())
                .userId(postImageCreateRecordCommand.userId())
                .build();

        ImageEntity savedImageEntity = imageJpaRepository.save(postImageEntity);

        return PostImageCreateRecord.builder()
                .id(savedImageEntity.getId())
                .originName(savedImageEntity.getOriginName())
                .storedName(savedImageEntity.getStoredName())
                .filePath(savedImageEntity.getFilePath())
                .extension(savedImageEntity.getExtension())
                .status(savedImageEntity.getStatus())
                .fileSize(savedImageEntity.getFileSize())
                .postId(savedImageEntity.getPostId())
                .userId(savedImageEntity.getUserId())
                .createdAt(savedImageEntity.getCreatedAt())
                .updatedAt(savedImageEntity.getUpdatedAt())
                .build();
    }

    @Override
    public void updateImage(PostImageUpdateRecordCommand postImageUpdateRecordCommand) {
        imageJpaRepository.updateImageByFileNames(postImageUpdateRecordCommand.postId(), postImageUpdateRecordCommand.fileNames());
    }
}
