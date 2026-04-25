package kr.scshin.scshin_dev.blog.application.service;


import kr.scshin.scshin_dev.blog.application.port.in.PostUpdateUseCase;
import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostUpdateCommand;
import kr.scshin.scshin_dev.blog.application.port.out.PostReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.PostUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostUpdateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostReadRecord;
import kr.scshin.scshin_dev.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service(value = "BlogPostUpdateService")
@RequiredArgsConstructor
public class PostUpdateService implements PostUpdateUseCase {

    private final PostReadPort postReadPort;
    private final PostUpdatePort postUpdatePort;

    @Override
    @Transactional
    public void updatePost(PostUpdateCommand postUpdateCommand) {
        PostReadRecord postReadRecord = postReadPort.readPost(new PostReadRecordQuery(postUpdateCommand.id()));

        Post post = Post.builder()
                .id(postReadRecord.id())
                .title(postReadRecord.title())
                .content(postReadRecord.content())
                .authorId(postReadRecord.authorId())
                .createdAt(postReadRecord.createdAt())
                .updateAt(postReadRecord.updatedAt())
                .build();
        post.update(postUpdateCommand.title(), postUpdateCommand.content());

        postUpdatePort.updatePost(new PostUpdateRecordCommand(post.getId(), post.getTitle(), post.getContent(), post.getAuthorId()));
    }
}
