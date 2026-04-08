package kr.scshin.scshin_dev.backoffice.adapter.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.BackofficeLoadPostListPort;
import kr.scshin.scshin_dev.backoffice.application.port.out.BackofficePostListResult;
import kr.scshin.scshin_dev.blog.application.port.in.BlogGetPostListUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.BlogPostListResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceAdapter implements BackofficeLoadPostListPort {

    private final BlogGetPostListUseCase blogGetPostListUseCase;

    @Override
    public List<BackofficePostListResult> loadPostList() {
        List<BlogPostListResponse> postListResponse = blogGetPostListUseCase.getPostList();
        return postListResponse.stream().map(post -> new BackofficePostListResult(post.id(), post.title(), post.content(), post.authorId(), post.createdAt(), post.updatedAt())).toList();
    }
}
