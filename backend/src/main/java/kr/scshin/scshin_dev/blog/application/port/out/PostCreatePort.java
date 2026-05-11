package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostCreateRecord;
import kr.scshin.scshin_dev.blog.domain.Post;

public interface PostCreatePort {
    PostCreateRecord createPost(PostCreateRecordCommand postCreateRecordCommand);
}
