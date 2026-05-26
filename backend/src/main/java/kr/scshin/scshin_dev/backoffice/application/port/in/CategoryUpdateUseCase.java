package kr.scshin.scshin_dev.backoffice.application.port.in;

import kr.scshin.scshin_dev.backoffice.application.port.in.dto.request.CategoryUpdateCommand;

public interface CategoryUpdateUseCase {
    void updateCategory(CategoryUpdateCommand command);
}
