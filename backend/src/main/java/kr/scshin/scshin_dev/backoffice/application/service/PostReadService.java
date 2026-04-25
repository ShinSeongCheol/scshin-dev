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

        return new PostReadResponse(postReadRecord.id(), postReadRecord.title(), postReadRecord.content(), postReadRecord.authorId(), postReadRecord.createdAt(), postReadRecord.updatedAt());
    }

    @Override
    public List<PostReadResponse> readPostList() {
        List<PostReadRecord> postListResult = postReadPort.readPostList();
        return postListResult.stream().map(postResult -> new PostReadResponse(postResult.id(), postResult.title(), postResult.content(), postResult.authorId(), postResult.createdAt(), postResult.updatedAt())).toList();
    }
}
