package kr.scshin.scshin_dev.backoffice.application.port.in;

import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostUpdateCommand;

public interface PostUpdateUseCase {
    void updatePost(PostUpdateCommand postUpdateCommand);
}
