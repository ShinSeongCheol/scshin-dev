package kr.scshin.scshin_dev.image.adapter.out.persistence;

import kr.scshin.scshin_dev.image.application.port.out.PostImageCreatePort;
import kr.scshin.scshin_dev.image.application.port.out.PostImageReadPort;
import kr.scshin.scshin_dev.image.application.port.out.PostImageUpdatePort;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageCreateRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageReadRecordQuery;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageUpdateRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.PostImageCreateRecord;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.PostImageReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImageAdapter implements PostImageCreatePort, PostImageUpdatePort, PostImageReadPort {

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

    @Override
    public List<List<PostImageReadRecord>> readPostImages(PostImageReadRecordQuery postImageReadRecordQuery) {

        List<List<ImageEntity>> imageEntityLists = postImageReadRecordQuery.postIds().stream().map(imageJpaRepository::findAllByPostId).toList();

        return imageEntityLists.stream().map(imageEntityList ->
            imageEntityList.stream().map(imageEntity ->
                PostImageReadRecord.builder()
                        .id(imageEntity.getId())
                        .originName(imageEntity.getOriginName())
                        .storedName(imageEntity.getStoredName())
                        .filePath(imageEntity.getFilePath())
                        .extension(imageEntity.getExtension())
                        .status(imageEntity.getStatus())
                        .fileSize(imageEntity.getFileSize())
                        .postId(imageEntity.getPostId())
                        .userId(imageEntity.getUserId())
                        .createdAt(imageEntity.getCreatedAt())
                        .updatedAt(imageEntity.getUpdatedAt())
                        .build()
            ).toList()
        ).toList();
    }
}
