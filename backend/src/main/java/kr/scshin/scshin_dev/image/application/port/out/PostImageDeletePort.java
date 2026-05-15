package kr.scshin.scshin_dev.image.application.port.out;

import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageDeleteRecordCommand;

public interface PostImageDeletePort {
    void deletePostImage(PostImageDeleteRecordCommand postImageDeleteRecordCommand);
}
