package kr.scshin.scshin_dev.image.application.port.out;


import kr.scshin.scshin_dev.image.application.port.out.dto.request.PostImageUpdateRecordCommand;

public interface PostImageUpdatePort {
    void updateImage(PostImageUpdateRecordCommand postImageUpdateRecordCommand);
}
