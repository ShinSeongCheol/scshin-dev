package kr.scshin.scshin_dev.image.application.service;

import kr.scshin.scshin_dev.image.application.port.in.PostImageDeleteUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageDeleteRequest;
import kr.scshin.scshin_dev.image.application.port.out.PostImageDeletePort;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageDeleteRecordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostImageDeleteService implements PostImageDeleteUseCase {

    private final PostImageDeletePort postImageDeletePort;

    @Override
    public void deletePostImage(PostImageDeleteRequest postImageDeleteRequest) {
        postImageDeletePort.deletePostImage(PostImageDeleteRecordCommand.builder().postId(postImageDeleteRequest.postId()).fileNames(postImageDeleteRequest.fileNames()).build());
    }
}
