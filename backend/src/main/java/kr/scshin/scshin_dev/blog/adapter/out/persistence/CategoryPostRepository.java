package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryPostRepository extends JpaRepository<CategoryPostJpaEntity, CategoryPostId> {
    List<CategoryPostJpaEntity> findAllByPostId(Long postId);
}
