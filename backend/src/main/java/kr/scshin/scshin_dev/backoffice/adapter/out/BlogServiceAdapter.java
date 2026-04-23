package kr.scshin.scshin_dev.backoffice.adapter.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.BackofficeLoadPostListPort;
import kr.scshin.scshin_dev.backoffice.application.port.out.BackofficePostListResult;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.backoffice.domain.Post;
import kr.scshin.scshin_dev.blog.application.port.in.BlogGetPostListUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.BlogPostListResponse;
import kr.scshin.scshin_dev.blog.application.port.in.PostCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostCreateCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceAdapter implements BackofficeLoadPostListPort, PostCreatePort {

    private final BlogGetPostListUseCase blogGetPostListUseCase;
    private final PostCreateUseCase postCreateUseCase;

    @Override
    public List<BackofficePostListResult> loadPostList() {
        List<BlogPostListResponse> postListResponse = blogGetPostListUseCase.getPostList();
        return postListResponse.stream().map(post -> new BackofficePostListResult(post.id(), post.title(), post.content(), post.authorId(), post.createdAt(), post.updatedAt())).toList();
    }

    @Override
    public void createPost(Post post) {
        PostCreateCommand postCreateCommand = new PostCreateCommand(post.title(), post.content(), post.authorId());
        postCreateUseCase.createPost(postCreateCommand);
    }
}
