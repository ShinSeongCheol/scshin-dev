package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.domain.Post;

public class PostMapper {

    public static PostJpaEntity toEntity(Post post) {
        return PostJpaEntity.builder().title(post.title()).content(post.content()).authorId(post.authorId()).build();
    }
}
