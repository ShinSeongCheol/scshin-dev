package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostJpaRepository extends JpaRepository<PostJpaEntity, Long> {
    @Modifying
    @Query("""
        UPDATE PostJpaEntity pje SET pje.views = pje.views + 1 WHERE pje.id = :postId
    """)
    void increasePostViewCount(@Param("postId") Long postId);
}
