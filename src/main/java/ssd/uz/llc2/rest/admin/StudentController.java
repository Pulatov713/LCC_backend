////package ssd.uz.llc.rest.admin;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
////import org.springframework.web.bind.annotation.RequestMapping;
////import org.springframework.web.bind.annotation.RestController;
////import org.springframework.web.bind.annotation.*;
////import ssd.uz.llc.dto.form.CreateStudentForm;
////import ssd.uz.llc.service.student.StudentService;
////
////@RestController
////@RequestMapping("/admin/student")
////public class StudentController {
////    @Autowired
////    private final StudentService studentService;
////
////    public StudentController(StudentService studentService) {
////        this.studentService = studentService;
////    }
////
////    @PostMapping("create")
////    public ResponseEntity createStudent(@RequestBody CreateStudentForm form) throws Exception {
////        return ResponseEntity.ok(studentService.save(form));
////    }
////
////    @PutMapping("/edit/{id}")
////    public ResponseEntity editStudent(@PathVariable Long id, @RequestBody CreateStudentForm form) throws Exception {
////        return ResponseEntity.ok(studentService.edit(id, form));
////    }
////
////    @DeleteMapping("/delete/{id}")
////    public ResponseEntity deleteStudent(@PathVariable Long id) throws Exception {
////        return ResponseEntity.ok(studentService.delete(id));
////    }
////
////    @GetMapping("/list")
////    public ResponseEntity getStudentList(@RequestParam(required = false, defaultValue = "0") int page, @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
////        return ResponseEntity.ok(studentService.findAll(page, size));
////    }
////
////    @GetMapping("/search")
////    public ResponseEntity getSearchStudentList(@RequestParam(name = "searching") String keyword,
////                                               @RequestParam(required = false, defaultValue = "0") int page,
////                                               @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
////        return ResponseEntity.ok(studentService.search(keyword, page, size));
////    }
////}
////
////
////
//
//
//
//
//package ssd.uz.llc.rest.admin;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import ssd.uz.llc.dto.form.CreateStudentForm;
//import ssd.uz.llc.service.student.StudentService;
//
//@RestController
//@RequestMapping("/admin/student")
//public class StudentController {
//
//    private final StudentService studentService;
//
//    @Autowired
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @PostMapping("/create")
//    public ResponseEntity<?> createStudent(@RequestBody CreateStudentForm form) throws Exception {
//        return ResponseEntity.ok(studentService.save(form));
//    }
//
//    @PutMapping("/edit/{id}")
//    public ResponseEntity<?> editStudent(@PathVariable Long id, @RequestBody CreateStudentForm form) throws Exception {
//        return ResponseEntity.ok(studentService.edit(id, form));
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<?> deleteStudent(@PathVariable Long id) throws Exception {
//        return ResponseEntity.ok(studentService.delete(id));
//    }
//
//    @GetMapping("/list")
//    public ResponseEntity<?> getStudentList(@RequestParam(required = false, defaultValue = "0") int page,
//                                            @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
//        return ResponseEntity.ok(studentService.findAll(page, size));
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<?> getSearchStudentList(@RequestParam(name = "searching") String keyword,
//                                                  @RequestParam(required = false, defaultValue = "0") int page,
//                                                  @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
//        return ResponseEntity.ok(studentService.search(keyword, page, size));
//    }
//}


package ssd.uz.llc2.rest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssd.uz.llc2.dto.form.user.CreateUserForm;
import ssd.uz.llc2.service.admin.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/admin/student")
public class StudentController {

    private final UserService userService;

    @Autowired
    public StudentController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody CreateUserForm form) throws Exception {
        return ResponseEntity.ok(userService.save(form));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editStudent(@PathVariable UUID id, @RequestBody CreateUserForm form) throws Exception {
        return ResponseEntity.ok(userService.edit(id, form));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable UUID id) throws Exception {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/list")
    public ResponseEntity<?> getStudentList(@RequestParam(required = false, defaultValue = "0") int page,
                                            @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
        return ResponseEntity.ok(userService.findAll(page, size));
    }

//    @GetMapping("/list")
//    public ResponseEntity<?> getStudentList(
//            @RequestParam(required = false, defaultValue = "0") int page,
//            @RequestParam(required = false, defaultValue = "20") int size) {
//        try {
//            return ResponseEntity.ok(userService.findAll(page, size));
//        } catch (Exception e) {
//            // Qo'shimcha xatolikni qayta ishlash
//            return ResponseEntity.status(500).body("Xato yuz berdi: " + e.getMessage());
//        }
//    }

//    @GetMapping("/list")
//    public ResponseEntity<?> getStudentList(@RequestParam(required = false, defaultValue = "0") int page,
//                                            @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
//        // Talabalarni sahifalash va chegaralash bilan olish uchun studentService.findAll metodini chaqiradi
//        return ResponseEntity.ok(userService.findAll(page, size));
//    }

    @GetMapping("/search")
    public ResponseEntity<?> getSearchStudentList(@RequestParam(name="searching") String keyword,
                                               @RequestParam(required = false, defaultValue = "0") int page,
                                               @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
        return ResponseEntity.ok(userService.search(keyword, page, size));
    }
//    @GetMapping("/search")
//    public ResponseEntity<?> getSearchStudentList(@RequestParam(name = "searching") String keyword,
//                                                  @RequestParam(required = false, defaultValue = "0") int page,
//                                                  @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
//        // Kalit so'z asosida talabalarni qidirish uchun studentService.search metodini chaqiradi
//        return ResponseEntity.ok(studentService.search(keyword, page, size));
//    }
}

