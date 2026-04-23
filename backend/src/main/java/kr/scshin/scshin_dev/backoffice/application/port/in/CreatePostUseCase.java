package kr.scshin.scshin_dev.backoffice.application.port.in;


import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostCreateCommand;

public interface CreatePostUseCase {
    void createPost(PostCreateCommand postCreateCommand);
}
