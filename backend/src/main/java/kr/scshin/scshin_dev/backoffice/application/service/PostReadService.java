package kr.scshin.scshin_dev.backoffice.application.service;

import kr.scshin.scshin_dev.backoffice.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.backoffice.application.port.in.dto.response.PostReadResponse;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostReadPort;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.PostReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostReadService implements PostReadUseCase {

    private final PostReadPort postReadPort;

    @Override
    public PostReadResponse readPost(PostReadQuery postReadQuery) {
        PostReadRecordQuery postReadRecordQuery = new PostReadRecordQuery(postReadQuery.postId());
        PostReadRecord postReadRecord = postReadPort.readPost(postReadRecordQuery);

        return PostReadResponse.builder()
                .id(postReadRecord.id())
                .title(postReadRecord.title())
                .content(postReadRecord.content())
                .createdAt(postReadRecord.createdAt())
                .updatedAt(postReadRecord.updatedAt())
                .authorId(postReadRecord.authorId())
                .views(postReadRecord.views())
                .build();
    }

    @Override
    public List<PostReadResponse> readPostList() {
        List<PostReadRecord> postListResult = postReadPort.readPostList();
        return postListResult.stream().map(postResult -> PostReadResponse.builder()
                .id(postResult.id())
                .title(postResult.title())
                .content(postResult.content())
                .createdAt(postResult.createdAt())
                .updatedAt(postResult.updatedAt())
                .authorId(postResult.authorId())
                .views(postResult.views())
                .build()
        ).toList();
    }
}
