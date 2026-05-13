package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageDeleteRecordCommand;

public interface PostImageDeletePort {
    void deletePostImage(PostImageDeleteRecordCommand postImageDeleteRecordCommand);
}
