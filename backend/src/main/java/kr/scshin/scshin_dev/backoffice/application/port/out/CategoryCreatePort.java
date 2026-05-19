package kr.scshin.scshin_dev.backoffice.application.port.out;

import kr.scshin.scshin_dev.backoffice.application.port.out.dto.request.CategoryCreateRecordCommand;

public interface CategoryCreatePort {
    void createCategory(CategoryCreateRecordCommand categoryCreateRecordCommand);
}
