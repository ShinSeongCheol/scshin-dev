package kr.scshin.scshin_dev.backoffice.application.port.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostUpdateRecordCommand;

public interface PostUpdatePort {
    void updatePost(PostUpdateRecordCommand postUpdateRecordCommand);
}
