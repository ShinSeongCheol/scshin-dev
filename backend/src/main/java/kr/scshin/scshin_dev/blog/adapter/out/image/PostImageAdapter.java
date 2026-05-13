package kr.scshin.scshin_dev.blog.adapter.out.image;

import kr.scshin.scshin_dev.blog.application.port.out.PostImageReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.PostImageUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageUpdateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostImageReadRecord;
import kr.scshin.scshin_dev.image.application.port.in.PostImageReadUseCase;
import kr.scshin.scshin_dev.image.application.port.in.PostImageUpdateUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageReadRequest;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageUpdateRequest;
import kr.scshin.scshin_dev.image.application.port.in.dto.response.PostImageReadResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostImageAdapter implements PostImageUpdatePort, PostImageReadPort {

    private final PostImageUpdateUseCase postImageUpdateUseCase;
    private final PostImageReadUseCase postImageReadUseCase;

    @Override
    public void updatePostImage(PostImageUpdateRecordCommand postImageUpdateRecordCommand) {
        PostImageUpdateRequest postImageUpdateRequest = PostImageUpdateRequest.builder()
                .postId(postImageUpdateRecordCommand.postId())
                .fileNames(postImageUpdateRecordCommand.fileNames())
                .build();

        postImageUpdateUseCase.updateImage(postImageUpdateRequest);
    }

    @Override
    public List<List<PostImageReadRecord>> readPostImages(PostImageReadRecordQuery postImageReadRecordQuery) {
        List<List<PostImageReadResponse>> postImageReadResponseLists = postImageReadUseCase.readPostImages(PostImageReadRequest.builder().postIds(postImageReadRecordQuery.postIds()).build());
        return postImageReadResponseLists.stream().map(
                postImageReadResponseList -> postImageReadResponseList.stream().map(
                        postImageReadResponse -> PostImageReadRecord.builder()
                                .id(postImageReadResponse.id())
                                .originName(postImageReadResponse.originName())
                                .storedName(postImageReadResponse.storedName())
                                .filePath(postImageReadResponse.filePath())
                                .extension(postImageReadResponse.extension())
                                .status(postImageReadResponse.status())
                                .fileSize(postImageReadResponse.fileSize())
                                .postId(postImageReadResponse.postId())
                                .userId(postImageReadResponse.userId())
                                .createdAt(postImageReadResponse.createdAt())
                                .updatedAt(postImageReadResponse.updatedAt())
                                .build()
                ).toList()
        ).toList();
    }
}
