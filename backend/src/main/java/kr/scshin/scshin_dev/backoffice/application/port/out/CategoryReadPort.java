package kr.scshin.scshin_dev.backoffice.application.port.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryTreeReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryReadRecord;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryTreeReadRecord;

import java.util.List;

public interface CategoryReadPort {
    List<CategoryReadRecord> readCategories(CategoryReadRecordQuery categoryReadRecordQuery);
    List<CategoryTreeReadRecord> readTreeCategories(CategoryTreeReadRecordQuery categoryTreeReadRecordQuery);
}
