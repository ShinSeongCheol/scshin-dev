package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "category_post")
@IdClass(CategoryPostId.class)
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CategoryPostJpaEntity implements Persistable<CategoryPostId> {
    @Id
    @Column(name = "category_id")
    private Long categoryId;
    @Id
    @Column(name = "post_id")
    private Long postId;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @Transient
    private boolean isNew = true;

    public CategoryPostJpaEntity(Long categoryId, Long postId) {
        this.categoryId = categoryId;
        this.postId = postId;
    }

    @Override
    public @Nullable CategoryPostId getId() {
        return new CategoryPostId(this.categoryId, this.postId);
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostPersist
    @PostLoad
    protected void load() {
        this.isNew = false;
    }
}
