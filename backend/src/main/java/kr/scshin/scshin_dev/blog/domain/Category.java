package kr.scshin.scshin_dev.blog.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Builder
public class Category {
    private Long id;
    private Long parentCategoryId;
    private String categoryName;
    private String slug;
    private String description;
    private int sortOrder;
    private int depth;
    private char useYn;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    public void update(Long parentCategoryId, String categoryName, String slug, String description, int depth, char useYn) {
        this.parentCategoryId = parentCategoryId;
        this.categoryName = categoryName;
        this.slug = slug;
        this.description = description;
        this.depth = depth;
        this.useYn = useYn;
    }
}
