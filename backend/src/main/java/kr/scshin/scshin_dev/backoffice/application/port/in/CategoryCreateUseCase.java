package kr.scshin.scshin_dev.backoffice.application.port.in;

import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.CategoryCreateCommand;

public interface CategoryCreateUseCase {
    void createCategory(CategoryCreateCommand categoryCreateCommand);
}
