package kr.scshin.scshin_dev.blog.application.port.in;

import kr.scshin.scshin_dev.blog.application.port.in.dto.request.PostCreateCommand;

public interface PostCreateUseCase {
    void createPost(PostCreateCommand postCreateCommand);
}
