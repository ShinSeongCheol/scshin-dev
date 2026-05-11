package kr.scshin.scshin_dev.blog.application.service;

import kr.scshin.scshin_dev.blog.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import kr.scshin.scshin_dev.blog.application.port.out.MarkdownParsePort;
import kr.scshin.scshin_dev.blog.application.port.out.PostReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service(value = "BlogPostReadService")
@RequiredArgsConstructor
public class PostReadService implements PostReadUseCase {

    private final PostReadPort postReadPort;
    private final MarkdownParsePort markdownParsePort;

    @Override
    public List<PostReadResponse> readPostList() {
        return postReadPort.readPostList().stream().map(post -> new PostReadResponse(post.id(), post.title(), markdownParsePort.renderAsSummary(post.content()), post.authorId(), post.createdAt(), post.updatedAt())).toList();
    }

    @Override
    public PostReadResponse readPost(PostReadQuery postReadQuery) {
        PostReadRecord postReadRecord = postReadPort.readPost(new PostReadRecordQuery(postReadQuery.postId()));
        return new PostReadResponse(postReadRecord.id(), postReadRecord.title(), postReadRecord.content(), postReadRecord.authorId(), postReadRecord.createdAt(), postReadRecord.updatedAt());
    }
}
