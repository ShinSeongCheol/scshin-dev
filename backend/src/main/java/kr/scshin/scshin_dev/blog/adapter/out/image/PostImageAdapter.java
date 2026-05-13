package kr.scshin.scshin_dev.blog.adapter.out.image;

import kr.scshin.scshin_dev.blog.application.port.out.PostImageDeletePort;
import kr.scshin.scshin_dev.blog.application.port.out.PostImageReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.PostImageUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageDeleteRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageUpdateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostImageReadRecord;
import kr.scshin.scshin_dev.image.application.port.in.PostImageDeleteUseCase;
import kr.scshin.scshin_dev.image.application.port.in.PostImageReadUseCase;
import kr.scshin.scshin_dev.image.application.port.in.PostImageUpdateUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageDeleteRequest;
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
public class PostImageAdapter implements PostImageUpdatePort, PostImageReadPort, PostImageDeletePort {

    private final PostImageUpdateUseCase postImageUpdateUseCase;
    private final PostImageReadUseCase postImageReadUseCase;
    private final PostImageDeleteUseCase postImageDeleteUseCase;

    @Override
    public void updatePostImage(PostImageUpdateRecordCommand postImageUpdateRecordCommand) {
        PostImageUpdateRequest postImageUpdateRequest = PostImageUpdateRequest.builder()
                .postId(postImageUpdateRecordCommand.postId())
                .fileNames(postImageUpdateRecordCommand.fileNames())
                .build();

        postImageUpdateUseCase.updateImage(postImageUpdateRequest);
    }

    @Override
    public void updatePostIdToNull(PostImageUpdateRecordCommand postImageUpdateRecordCommand) {
        postImageUpdateUseCase.updatePostIdToNull(PostImageUpdateRequest.builder()
                .postId(postImageUpdateRecordCommand.postId())
                .fileNames(postImageUpdateRecordCommand.fileNames())
                .build()
        );
    }

    @Override
    public List<List<PostImageReadRecord>> readPostImageLists(PostImageReadRecordQuery postImageReadRecordQuery) {
        List<List<PostImageReadResponse>> postImageReadResponseLists = postImageReadUseCase.readPostImageLists(PostImageReadRequest.builder().postIds(postImageReadRecordQuery.postIds()).build());
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

    @Override
    public List<PostImageReadRecord> readPostImageList(PostImageReadRecordQuery postImageReadRecordQuery) {
        List<PostImageReadResponse> postImageReadResponseList = postImageReadUseCase.readPostImageList(PostImageReadRequest.builder().postIds(postImageReadRecordQuery.postIds()).build());
        return postImageReadResponseList.stream().map(postImageReadResponse -> PostImageReadRecord.builder()
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
        ).toList();
    }

    @Override
    public void deletePostImage(PostImageDeleteRecordCommand postImageDeleteRecordCommand) {
        postImageDeleteUseCase.deletePostImage(PostImageDeleteRequest.builder().postId(postImageDeleteRecordCommand.postId()).fileNames(postImageDeleteRecordCommand.fileNames()).build());
    }
}
