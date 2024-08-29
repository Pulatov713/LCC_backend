package ssd.uz.llc2.service.admin;


import ssd.uz.llc2.dto.ResponseDto;
import ssd.uz.llc2.dto.form.CreateCourseForm;

public interface CourseService {
    ResponseDto save(CreateCourseForm form);

    ResponseDto edit(Long id, CreateCourseForm form);

    ResponseDto delete(Long id);

    ResponseDto findAll(int page, int size);

    ResponseDto findCoursesForSelect();

    ResponseDto search(String searching, int page, int size) throws Exception;

}

