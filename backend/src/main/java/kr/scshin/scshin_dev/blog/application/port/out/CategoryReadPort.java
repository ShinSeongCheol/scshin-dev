package kr.scshin.scshin_dev.blog.application.port.out;

import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryTreeReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryTreeReadRecord;

import java.util.List;

public interface CategoryReadPort {
    CategoryReadRecord readCategory(CategoryReadRecordQuery categoryReadRecordQuery);
    List<CategoryReadRecord> readCategories(CategoryReadRecordQuery categoryReadRecordQuery);
    CategoryReadRecord readCategoryByParentCategoryId(CategoryReadRecordQuery categoryReadRecordQuery);
    List<CategoryTreeReadRecord> readTreeCategories(CategoryTreeReadRecordQuery categoryTreeReadRecordQuery);
}
