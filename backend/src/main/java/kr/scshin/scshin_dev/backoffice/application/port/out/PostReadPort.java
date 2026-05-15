package kr.scshin.scshin_dev.backoffice.application.port.out;

import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.PostReadQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.PostReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.PostReadRecord;

import java.util.List;

public interface PostReadPort {
    List<PostReadRecord> readPostList();
    PostReadRecord readPost(PostReadRecordQuery postReadRecordQuery);
}
