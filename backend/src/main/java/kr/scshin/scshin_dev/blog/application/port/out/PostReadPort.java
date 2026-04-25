package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostReadRecord;

import java.util.List;
import java.util.Optional;

public interface PostReadPort {
    List<PostReadRecord> readPostList();
    PostReadRecord readPost(PostReadRecordQuery postReadRecordQuery);
}
