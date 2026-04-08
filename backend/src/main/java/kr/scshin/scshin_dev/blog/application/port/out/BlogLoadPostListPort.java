package kr.scshin.scshin_dev.blog.application.port.out;

import java.util.List;

public interface BlogLoadPostListPort {
    List<BlogLoadPostListResult> loadPostList();
}
