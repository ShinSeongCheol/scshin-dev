package kr.scshin.scshin_dev.backoffice.adapter.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostReadPort;
import kr.scshin.scshin_dev.backoffice.application.port.out.PostUpdatePort;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostCreateRecordCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostUpdateRecordCommand;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.PostReadRecord;
import kr.scshin.scshin_dev.blog.application.port.in.PostCreateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.PostReadUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.PostUpdateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostCreateCommand;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostUpdateCommand;
import kr.scshin.scshin_dev.blog.application.port.in.dto.response.PostReadResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BlogServiceAdapter implements PostCreatePort, PostReadPort, PostUpdatePort {

    private final PostCreateUseCase postCreateUseCase;
    private final PostReadUseCase postReadUseCase;
    private final PostUpdateUseCase postUpdateUseCase;

    @Override
    public void createPost(PostCreateRecordCommand postCreateRecordCommand) {
        PostCreateCommand postCreateCommand = new PostCreateCommand(postCreateRecordCommand.title(), postCreateRecordCommand.content(), postCreateRecordCommand.authorId());
        postCreateUseCase.createPost(postCreateCommand);
    }

    @Override
    public List<PostReadRecord> readPostList() {
        List<PostReadResponse> postListResponse = postReadUseCase.readPostList();
        return postListResponse.stream().map(post -> new PostReadRecord(post.id(), post.title(), post.content(), post.authorId(), post.createdAt(), post.updatedAt())).toList();
    }

    @Override
    public PostReadRecord readPost(PostReadRecordQuery postReadRecordQuery) {
        PostReadQuery postReadQuery = new PostReadQuery(postReadRecordQuery.postId());
        PostReadResponse postReadResponse = postReadUseCase.readPost(postReadQuery);

        return new PostReadRecord(postReadResponse.id(), postReadResponse.title(), postReadResponse.content(), postReadResponse.authorId(), postReadResponse.createdAt(), postReadResponse.updatedAt());
    }

    @Override
    public void updatePost(PostUpdateRecordCommand postUpdateRecordCommand) {
        postUpdateUseCase.updatePost(new PostUpdateCommand(postUpdateRecordCommand.id(), postUpdateRecordCommand.title(), postUpdateRecordCommand.content()));
    }
}
