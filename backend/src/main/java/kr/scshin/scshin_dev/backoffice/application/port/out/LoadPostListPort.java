package kr.scshin.scshin_dev.backoffice.application.port.out;

import kr.scshin.scshin_dev.backoffice.domain.Post;

import java.util.List;

public interface LoadPostListPort {
    List<Post> loadPostList();
}
