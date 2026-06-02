package kr.scshin.scshin_dev.blog.adapter.out.persistence;

import kr.scshin.scshin_dev.blog.application.port.out.CategoryPostCreatePort;
import kr.scshin.scshin_dev.blog.application.port.out.dto.request.CategoryPostCreateRecordCommand;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class CategoryPostAdapter implements CategoryPostCreatePort {
    private final CategoryPostRepository categoryPostRepository;

    @Override
    public void createCategoryPost(List<CategoryPostCreateRecordCommand> categoryPostCreateRecordCommandList) {
        log.info("createCategoryPost: {}", categoryPostCreateRecordCommandList);
        List<CategoryPostJpaEntity> categoryPostJpaEntityList = categoryPostCreateRecordCommandList.stream().map(categoryPostCreateRecordCommand -> new CategoryPostJpaEntity(categoryPostCreateRecordCommand.categoryId(), categoryPostCreateRecordCommand.postId())).toList();
        categoryPostRepository.saveAll(categoryPostJpaEntityList);
    }
}
