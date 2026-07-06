package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.out.CategoryPostCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryPostDeletePort;
import kr.scshin.scshin_dev.blog.application.port.out.CategoryPostReadPort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostCreateRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostDeleteRecordCommand;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostReadRecordQuery;
import kr.scshin.scshin_dev.blog.application.port.out.dto.response.CategoryPostReadRecord;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryPostAdapter implements CategoryPostCreatePort, CategoryPostReadPort, CategoryPostDeletePort {
    private final CategoryPostRepository categoryPostRepository;

    @Override
    public void createCategoryPost(List<CategoryPostCreateRecordCommand> categoryPostCreateRecordCommandList) {
        log.info("createCategoryPost: {}", categoryPostCreateRecordCommandList);
        List<CategoryPostJpaEntity> categoryPostJpaEntityList = categoryPostCreateRecordCommandList.stream().map(categoryPostCreateRecordCommand -> new CategoryPostJpaEntity(categoryPostCreateRecordCommand.categoryId(), categoryPostCreateRecordCommand.postId())).toList();
        categoryPostRepository.saveAll(categoryPostJpaEntityList);
    }

    @Override
    public List<CategoryPostReadRecord> readCategoryPost(CategoryPostReadRecordQuery categoryPostReadRecordQuery) {
        log.info("readCategoryPost: {}", categoryPostReadRecordQuery);

        List<CategoryPostJpaEntity> categoryPostJpaEntityList = categoryPostRepository.findAllByPostId(categoryPostReadRecordQuery.postId());
        return categoryPostJpaEntityList.stream().map(
                categoryPostJpaEntity -> CategoryPostReadRecord.builder()
                        .categoryId(categoryPostJpaEntity.getCategoryId())
                        .postId(categoryPostJpaEntity.getPostId())
                        .createdAt(categoryPostJpaEntity.getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public void deleteCategoryPostByPostId(Long postId) {
        log.info("deleteCategoryPost: {}", postId);

        categoryPostRepository.deleteByPostId(postId);
    }
}
