package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.PostReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.PostUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostUpdateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostReadRecord;
import kr.scshin.scshin_dev.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostAdapter implements PostCreatePort, PostReadPort, PostUpdatePort {

    private final PostJpaRepository postJpaRepository;

    @Override
    public void createPost(PostCreateRecordCommand postCreateRecordCommand) {
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .title(postCreateRecordCommand.title())
                .content(postCreateRecordCommand.content())
                .authorId(postCreateRecordCommand.authorId())
                .build();

        postJpaRepository.save(postJpaEntity);
    }

    @Override
    public List<PostReadRecord> readPostList() {
        List<PostJpaEntity> postJpaEntityList = postJpaRepository.findAll();
        return postJpaEntityList.stream().map(postJpaEntity -> new PostReadRecord(postJpaEntity.getId(), postJpaEntity.getTitle(), postJpaEntity.getContent(), postJpaEntity.getAuthorId(), postJpaEntity.getCreatedAt(), postJpaEntity.getUpdatedAt())).toList();
    }

    @Override
    public PostReadRecord readPost(PostReadRecordQuery postReadRecordQuery) {
        PostJpaEntity postJpaEntity = postJpaRepository.findById(postReadRecordQuery.postId()).orElseThrow();
        return new PostReadRecord(postJpaEntity.getId(), postJpaEntity.getTitle(), postJpaEntity.getContent(), postJpaEntity.getAuthorId(), postJpaEntity.getCreatedAt(), postJpaEntity.getUpdatedAt());
    }

    @Override
    public void updatePost(PostUpdateRecordCommand postUpdateRecordCommand) {
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .id(postUpdateRecordCommand.id())
                .title(postUpdateRecordCommand.title())
                .content(postUpdateRecordCommand.content())
                .authorId(postUpdateRecordCommand.authorId())
                .build();

        postJpaRepository.save(postJpaEntity);
    }
}
