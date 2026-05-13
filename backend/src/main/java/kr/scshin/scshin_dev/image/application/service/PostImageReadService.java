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
    public List<List<PostImageReadResponse>> readPostImages(PostImageReadRequest postImageReadRequest) {

        List<List<PostImageReadRecord>> postImageReadRecordLists = postImageReadPort.readPostImages(PostImageReadRecordQuery.builder().postIds(postImageReadRequest.postIds()).build());
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
}
