package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.PostImageReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.PostImageReadRecord;

import java.util.List;

public interface PostImageReadPort {
    List<List<PostImageReadRecord>> readPostImages(PostImageReadRecordQuery postImageReadRecordQuery);
}
