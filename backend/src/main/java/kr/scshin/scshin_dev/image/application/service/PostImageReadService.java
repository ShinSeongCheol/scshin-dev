package kr.scshin.scshin_dev.image.application.service;

import kr.scshin.scshin_dev.image.application.port.in.PostImageReadUseCase;
import kr.scshin.scshin_dev.image.application.port.in.dto.request.PostImageReadRequest;
import kr.scshin.scshin_dev.image.application.port.in.dto.response.PostImageReadResponse;
import kr.scshin.scshin_dev.image.application.port.out.PostImageReadPort;
import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageReadRecordQuery;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.PostImageReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostImageReadService implements PostImageReadUseCase {

    private final PostImageReadPort postImageReadPort;

    @Override
    public List<List<PostImageReadResponse>> readPostImageLists(PostImageReadRequest postImageReadRequest) {

        List<List<PostImageReadRecord>> postImageReadRecordLists = postImageReadPort.readPostImageLists(PostImageReadRecordQuery.builder().postIds(postImageReadRequest.postIds()).build());
        return postImageReadRecordLists.stream().map(postImageReadRecordList ->
                    postImageReadRecordList.stream().map(postImageReadRecord ->
                        PostImageReadResponse.builder()
                                .id(postImageReadRecord.id())
                                .originName(postImageReadRecord.originName())
                                .storedName(postImageReadRecord.storedName())
                                .filePath(postImageReadRecord.filePath())
                                .extension(postImageReadRecord.extension())
                                .status(postImageReadRecord.status())
                                .fileSize(postImageReadRecord.fileSize())
                                .postId(postImageReadRecord.postId())
                                .userId(postImageReadRecord.userId())
                                .createdAt(postImageReadRecord.createdAt())
                                .updatedAt(postImageReadRecord.updatedAt())
                                .build()
                    ).toList()
                ).toList();
    }

    @Override
    public List<PostImageReadResponse> readPostImageList(PostImageReadRequest postImageReadRequest) {
        return postImageReadPort.readPostImageList(PostImageReadRecordQuery.builder().postId(postImageReadRequest.postIds().get(0)).build()).stream().map(postImageReadRecord ->
                PostImageReadResponse.builder()
                        .id(postImageReadRecord.id())
                        .originName(postImageReadRecord.originName())
                        .storedName(postImageReadRecord.storedName())
                        .filePath(postImageReadRecord.filePath())
                        .extension(postImageReadRecord.extension())
                        .status(postImageReadRecord.status())
                        .postId(postImageReadRecord.postId())
                        .userId(postImageReadRecord.userId())
                        .fileSize(postImageReadRecord.fileSize())
                        .createdAt(postImageReadRecord.createdAt())
                        .updatedAt(postImageReadRecord.updatedAt())
                        .build()
        ).toList();
    }
}
