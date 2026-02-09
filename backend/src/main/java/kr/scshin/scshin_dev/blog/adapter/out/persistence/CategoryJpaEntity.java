package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "categories")
@EntityListeners(AuditingEntityListener.class)
public class CategoryJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true, length = 128)
    private String slug;

    @Column(length = 64)
    private String icon;

    @Column(name = "theme_color", length = 32)
    private String themeColor;

    @Column(length = 255)
    private String description;

    @Column(name = "sort_order")
    @ColumnDefault("0")
    private int sortOrder;

    @Column(name = "is_visible")
    @ColumnDefault("true")
    private boolean isVisible = true;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private CategoryJpaEntity parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    private List<CategoryJpaEntity> children = new ArrayList<>();
}
