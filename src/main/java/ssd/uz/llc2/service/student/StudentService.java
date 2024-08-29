//package ssd.uz.llc.service.student;
//
//import ssd.uz.llc.dto.ResponseDto;
//import ssd.uz.llc.dto.form.CreateStudentForm;
//
//public interface StudentService {
//    ResponseDto getGroupsOfStudent();
//
//    ResponseDto getHomeworksOfStudent(Long groupId);
//
//    ResponseDto getHomeworkCount();
//
//    ResponseDto save(CreateStudentForm form);
//
//    ResponseDto edit(Long id, CreateStudentForm form);
//
//    ResponseDto delete(Long id);
//
//    ResponseDto findAll(int page, int size);
//
//    ResponseDto search(String searching, int page, int size) throws Exception;
//
//
//
//}


//package ssd.uz.llc.service.student;
//
//import ssd.uz.llc.dto.ResponseDto;
//import ssd.uz.llc.dto.form.CreateStudentForm;
//import org.springframework.data.domain.Pageable;
//
//public interface StudentService {
//
//    // Talabaning guruhlarini olish
//    ResponseDto getGroupsOfStudent();
//
//    // Guruh ID orqali talabaning uy vazifalarini olish
//    ResponseDto getHomeworksOfStudent(Long groupId);
//
//    // Uy vazifalarining umumiy sonini olish
//    ResponseDto getHomeworkCount();
//
//    // Talaba qo'shish
//    ResponseDto save(CreateStudentForm form);
//
//    // Talabani tahrirlash
//    ResponseDto edit(Long id, CreateStudentForm form);
//
//    // Talabani o'chirish
//    ResponseDto delete(Long id);
//
//    // Sahifalangan talaba ro'yxatini olish
//    ResponseDto findAll(Pageable pageable);
//
//    // Sahifalangan va qidiruv so'rovi bo'yicha talaba ro'yxatini olish
//    ResponseDto search(String searching, Pageable pageable) throws Exception;
//}


package ssd.uz.llc2.service.student;

import ssd.uz.llc2.dto.ResponseDto;


public interface StudentService {

    // Talabaning guruhlarini olish
    ResponseDto getGroupsOfStudent();

    // Guruh ID orqali talabaning uy vazifalarini olish
    ResponseDto getHomeworksOfStudent(Long groupId);

    // Uy vazifalarining umumiy sonini olish
    ResponseDto getHomeworkCount();

}

