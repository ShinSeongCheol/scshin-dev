package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryPostRepository extends JpaRepository<CategoryPostJpaEntity, CategoryPostId> {
}
