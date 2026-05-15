package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageUpdateRecordCommand;

public interface PostImageUpdatePort {
    void updatePostImage(PostImageUpdateRecordCommand postImageUpdateRecordCommand);
    void updatePostIdToNull(PostImageUpdateRecordCommand postImageUpdateRecordCommand);
}
