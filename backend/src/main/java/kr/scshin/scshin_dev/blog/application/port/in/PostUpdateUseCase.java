package kr.scshin.scshin_dev.blog.application.port.in;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostUpdateCommand;

public interface PostUpdateUseCase {
    void updatePost(PostUpdateCommand postUpdateCommand);
}
