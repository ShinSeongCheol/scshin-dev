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
public class PostImageAdapter implements PostImageCreatePort, PostImageUpdatePort {

    private final ImageJpaRepository imageJpaRepository;

    @Override
    public PostImageCreateRecord createPostImage(PostImageCreateRecordCommand postImageCreateRecordCommand) {

        PostImageEntity postImageEntity = PostImageEntity.builder()
                .originName(postImageCreateRecordCommand.orignName())
                .storedName(postImageCreateRecordCommand.storedName())
                .filePath(postImageCreateRecordCommand.filePath())
                .extension(postImageCreateRecordCommand.extension())
                .status(postImageCreateRecordCommand.status())
                .fileSize(postImageCreateRecordCommand.fileSize())
                .userId(postImageCreateRecordCommand.userId())
                .build();

        PostImageEntity savedPostImageEntity = imageJpaRepository.save(postImageEntity);

        return PostImageCreateRecord.builder()
                .id(savedPostImageEntity.getId())
                .originName(savedPostImageEntity.getOriginName())
                .storedName(savedPostImageEntity.getStoredName())
                .filePath(savedPostImageEntity.getFilePath())
                .extension(savedPostImageEntity.getExtension())
                .status(savedPostImageEntity.getStatus())
                .fileSize(savedPostImageEntity.getFileSize())
                .postId(savedPostImageEntity.getPostId())
                .userId(savedPostImageEntity.getUserId())
                .createdAt(savedPostImageEntity.getCreatedAt())
                .updatedAt(savedPostImageEntity.getUpdatedAt())
                .build();
    }

    @Override
    public void updateImage(PostImageUpdateRecordCommand postImageUpdateRecordCommand) {
        
    }
}
