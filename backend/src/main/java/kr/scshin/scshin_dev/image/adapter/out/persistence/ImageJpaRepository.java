package kr.scshin.scshin_dev.image.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ImageJpaRepository extends JpaRepository<ImageEntity, Long> {
    @Query("""
       UPDATE ImageEntity ie SET ie.status=COMMITED, ie.postId=:postId WHERE ie.storedName IN :fileNames
    """)
    @Modifying(clearAutomatically = true)
    @Transactional
    void updateImageByFileNames(@Param("postId") Long postId, @Param("fileNames") List<String> fileNames);
}
