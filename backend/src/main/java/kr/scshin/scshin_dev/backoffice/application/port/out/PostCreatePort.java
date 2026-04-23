package kr.scshin.scshin_dev.backoffice.application.port.out;

import kr.scshin.scshin_dev.backoffice.domain.Post;

public interface PostCreatePort {
    void createPost(Post post);
}
