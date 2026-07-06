package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryPostReadRecord;

import java.util.List;

public interface CategoryPostReadPort {
    List<CategoryPostReadRecord> readCategoryPost(CategoryPostReadRecordQuery categoryPostReadRecordQuery);
}
