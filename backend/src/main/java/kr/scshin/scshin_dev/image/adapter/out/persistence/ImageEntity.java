package kr.scshin.scshin_dev.image.adapter.out.persistence;

import jakarta.persistence.*;
import kr.scshin.scshin_dev.image.domain.ImageStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "image")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class ImageEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String originName;
    @Column(nullable = false, unique = true)
    private String storedName;
    @Column(nullable = false)
    private String filePath;
    @Column(length = 8)
    private String extension;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ImageStatus status = ImageStatus.TEMPORARY;
    private Long fileSize;

    private Long postId;
    @Column(nullable = false)
    private Long userId;
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
