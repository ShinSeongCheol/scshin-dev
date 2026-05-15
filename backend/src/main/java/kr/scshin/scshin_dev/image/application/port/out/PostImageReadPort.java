package kr.scshin.scshin_dev.image.application.port.out;

import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageReadRecordQuery;
import kr.scshin.scshin_dev.image.application.port.out.dto.response.PostImageReadRecord;

import java.util.List;

public interface PostImageReadPort {
    List<List<PostImageReadRecord>> readPostImageLists(PostImageReadRecordQuery postImageReadRecordQuery);
    List<PostImageReadRecord> readPostImageList(PostImageReadRecordQuery postImageReadRecordQuery);
}
