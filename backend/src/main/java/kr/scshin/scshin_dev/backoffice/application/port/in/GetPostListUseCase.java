package kr.scshin.scshin_dev.backoffice.application.port.in;

import kr.scshin.scshin_dev.backoffice.domain.Post;

import java.util.List;

public interface GetPostListUseCase {
    List<Post> getPostList();
}
