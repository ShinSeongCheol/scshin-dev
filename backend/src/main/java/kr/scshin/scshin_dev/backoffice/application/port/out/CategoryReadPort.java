package kr.scshin.scshin_dev.backoffice.application.port.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryReadRecordQuery;
import kr.scshin.scshin_dev.backoffice.application.port.out.dto.response.CategoryReadRecord;

import java.util.List;

public interface CategoryReadPort {
    List<CategoryReadRecord> readCategories(CategoryReadRecordQuery categoryReadRecordQuery);
}
