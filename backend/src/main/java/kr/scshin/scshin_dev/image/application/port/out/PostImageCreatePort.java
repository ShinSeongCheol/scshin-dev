package kr.scshin.scshin_dev.image.application.port.out;

import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageCreateRecordCommand;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.PostImageCreateRecord;

public interface PostImageCreatePort {
    PostImageCreateRecord createPostImage(PostImageCreateRecordCommand postImageCreateRecordCommand);
}
