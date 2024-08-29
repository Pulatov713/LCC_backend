package ssd.uz.llc2.service.admin;

import org.springframework.data.domain.Page;
import ssd.uz.llc2.db.domain.User;
import ssd.uz.llc2.dto.ResponseDto;
import ssd.uz.llc2.dto.form.LoginForm;
import ssd.uz.llc2.dto.form.user.CreateUserForm;

import java.util.UUID;

public interface UserService {
    ResponseDto signin(LoginForm form) throws Exception;

    ResponseDto currentUserInfo() throws Exception;

    ResponseDto save(CreateUserForm userForm) throws Exception;

    ResponseDto edit(UUID id, CreateUserForm userForm) throws Exception;

    ResponseDto delete(UUID id) throws Exception;

    ResponseDto findAllByRoleId(Long roleIds, String isActive, int page, int size) throws Exception;

    ResponseDto findTeachersForSelect();

    ResponseDto searchStudent(String username) throws Exception;

    ResponseDto getStudentsOfGroup(Long groupId, int page, int size) throws Exception;

    ResponseDto search(Long roleId, String isActive, String searching, int page, int size) throws Exception;

    //Student role uchun

//    ResponseDto save(CreateUserForm form);
//
//    ResponseDto edit(UUID id, CreateUserForm form);
//
//    ResponseDto delete(UUID id);
    ResponseDto findAll(int page, int size);

    ResponseDto search(String search, int page, int size) throws Exception;

    //Teacher role uchun

    // Teacherlar ro'yxatini olish
    ResponseDto findAllTeacher(int pageNumber, int pageSize);

    // Teacherlar ro'yxatida qidiruv
    ResponseDto searchTeacher(String keyword, int pageNumber,int pageSize);

}
