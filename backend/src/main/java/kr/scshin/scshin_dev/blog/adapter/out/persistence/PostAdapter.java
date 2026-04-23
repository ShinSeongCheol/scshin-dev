package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.out.BlogLoadPostListPort;
import kr.scshin.scshin_dev.blog.application.port.out.BlogLoadPostListResult;
import kr.scshin.scshin_dev.blog.application.port.out.PostCreatePort;
import kr.scshin.scshin_dev.blog.domain.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PostAdapter implements BlogLoadPostListPort, PostCreatePort {

    private final PostJpaRepository postJpaRepository;

    @Override
    public List<BlogLoadPostListResult> loadPostList() {
        List<PostJpaEntity> postJpaEntityList = postJpaRepository.findAll();
        return postJpaEntityList.stream().map(postJpaEntity -> new BlogLoadPostListResult(postJpaEntity.getId(), postJpaEntity.getTitle(), postJpaEntity.getContent(), postJpaEntity.getAuthorId(), postJpaEntity.getCreatedAt(), postJpaEntity.getUpdatedAt())).toList();
    }

    @Override
    public void createPost(Post post) {
        postJpaRepository.save(PostMapper.toEntity(post));
    }
}
