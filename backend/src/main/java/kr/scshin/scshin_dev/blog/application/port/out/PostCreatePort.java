package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.domain.Post;

public interface PostCreatePort {
    void createPost(Post post);
}
