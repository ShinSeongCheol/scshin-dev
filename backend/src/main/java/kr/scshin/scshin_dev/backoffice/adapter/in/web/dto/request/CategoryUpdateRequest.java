package kr.scshin.scshin_dev.backoffice.adapter.in.web.dto.request;

public record CategoryUpdateRequest(Long parentCategoryId, String categoryName, String slug, String description, char useYn) {
}
