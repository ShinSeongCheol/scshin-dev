package kr.scshin.scshin_dev.blog.adapter.out.image;

import kr.scshin.scshin_dev.blog.application.port.out.PostImageUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageUpdateRecordCommand;
import kr.scshin.scshin_dev.image.application.port.in.PostImageUpdateUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostImageAdapter implements PostImageUpdatePort {

    private final PostImageUpdateUseCase postImageUpdateUseCase;

    @Override
    public void updatePostImage(PostImageUpdateRecordCommand postImageUpdateRecordCommand) {
        PostImageUpdateRequest postImageUpdateRequest = PostImageUpdateRequest.builder()
                .postId(postImageUpdateRecordCommand.postId())
                .fileNames(postImageUpdateRecordCommand.fileNames())
                .build();

        postImageUpdateUseCase.updateImage(postImageUpdateRequest);
    }
}
