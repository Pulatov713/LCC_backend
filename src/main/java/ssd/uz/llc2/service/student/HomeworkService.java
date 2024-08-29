package ssd.uz.llc2.service.student;

import org.springframework.web.multipart.MultipartFile;
import ssd.uz.llc2.dto.ResponseDto;

import java.util.UUID;

public interface HomeworkService {
    ResponseDto saveHomework(UUID taskId, UUID homeworkId, MultipartFile file) throws Exception;
}
