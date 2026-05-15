package kr.scshin.scshin_dev.image.application.service;

import jakarta.transaction.Transactional;
import kr.scshin.scshin_dev.image.application.port.in.PostImageUpdateUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageUpdateRequest;
import kr.scshin.scshin_dev.image.application.port.out.PostImageUpdatePort;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageUpdateRecordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostImageUpdateService implements PostImageUpdateUseCase {

    private final PostImageUpdatePort postImageUpdatePort;

    @Override
    @Transactional
    public void updateImage(PostImageUpdateRequest postImageUpdateRequest) {
        PostImageUpdateRecordCommand postImageUpdateRecordCommand = PostImageUpdateRecordCommand.builder()
                .postId(postImageUpdateRequest.postId())
                .fileNames(postImageUpdateRequest.fileNames())
                .build();

        postImageUpdatePort.updateImage(postImageUpdateRecordCommand);
    }

    @Override
    @Transactional
    public void updatePostIdToNull(PostImageUpdateRequest postImageUpdateRequest) {
        postImageUpdatePort.updatePostIdToNull(PostImageUpdateRecordCommand.builder()
                .postId(postImageUpdateRequest.postId())
                .fileNames(postImageUpdateRequest.fileNames())
                .build()
        );
    }
}
