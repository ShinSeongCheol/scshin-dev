package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.PostReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.PostUpdatePort;
import kr.scshin.scshin_dev.blog.application.port.out.PostViewIncreasePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostUpdateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostCreateRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class PostAdapter implements PostCreatePort, PostReadPort, PostUpdatePort, PostViewIncreasePort {

    private final PostJpaRepository postJpaRepository;

    @Override
    public PostCreateRecord createPost(PostCreateRecordCommand postCreateRecordCommand) {
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .title(postCreateRecordCommand.title())
                .content(postCreateRecordCommand.content())
                .authorId(postCreateRecordCommand.authorId())
                .views(postCreateRecordCommand.views())
                .build();

        PostJpaEntity savedPostJpaEntity = postJpaRepository.save(postJpaEntity);
        return PostCreateRecord.builder()
                .id(savedPostJpaEntity.getId())
                .title(savedPostJpaEntity.getTitle())
                .content(savedPostJpaEntity.getContent())
                .authorId(savedPostJpaEntity.getAuthorId())
                .createdAt(savedPostJpaEntity.getCreatedAt())
                .updatedAt(savedPostJpaEntity.getUpdatedAt())
                .build();
    }

    @Override
    public List<PostReadRecord> readPostList() {
        Pageable pageable = PageRequest.of(0, 10, Sort.by("createdAt").descending());
        Page<PostJpaEntity> postJpaEntityList = postJpaRepository.findAll(pageable);
        return postJpaEntityList.stream().map(postJpaEntity -> PostReadRecord.builder()
                .id(postJpaEntity.getId())
                .title(postJpaEntity.getTitle())
                .content(postJpaEntity.getContent())
                .authorId(postJpaEntity.getAuthorId())
                .createdAt(postJpaEntity.getCreatedAt())
                .updatedAt(postJpaEntity.getUpdatedAt())
                .views(postJpaEntity.getViews())
                .build()
        ).toList();
    }

    @Override
    public PostReadRecord readPost(PostReadRecordQuery postReadRecordQuery) {
        PostJpaEntity postJpaEntity = postJpaRepository.findById(postReadRecordQuery.postId()).orElseThrow();
        return PostReadRecord.builder()
                .id(postJpaEntity.getId())
                .title(postJpaEntity.getTitle())
                .content(postJpaEntity.getContent())
                .authorId(postJpaEntity.getAuthorId())
                .createdAt(postJpaEntity.getCreatedAt())
                .updatedAt(postJpaEntity.getUpdatedAt())
                .views(postJpaEntity.getViews())
                .build();
    }

    @Override
    public void updatePost(PostUpdateRecordCommand postUpdateRecordCommand) {
        PostJpaEntity postJpaEntity = PostJpaEntity.builder()
                .id(postUpdateRecordCommand.id())
                .title(postUpdateRecordCommand.title())
                .content(postUpdateRecordCommand.content())
                .authorId(postUpdateRecordCommand.authorId())
                .views(postUpdateRecordCommand.views())
                .build();

        postJpaRepository.save(postJpaEntity);
    }

    @Override
    public void increasePostViewCount(Long postId) {
        postJpaRepository.increasePostViewCount(postId);
    }
}
