package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryPostReadRecord;

public interface CategoryPostReadPort {
    CategoryPostReadRecord readCategoryPost(CategoryPostReadRecordQuery categoryPostReadRecordQuery);
}
