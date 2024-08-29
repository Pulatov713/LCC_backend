package ssd.uz.llc2.rest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssd.uz.llc2.dto.form.user.CreateUserForm;
import ssd.uz.llc2.service.admin.UserService;

import java.util.UUID;

@RestController
@RequestMapping("/admin/teacher")
public class TeacherController {

    private final UserService userService;

    @Autowired
    public TeacherController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTeacher(@RequestBody CreateUserForm form) throws Exception {
        return ResponseEntity.ok(userService.save(form));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<?> editTeacher(@PathVariable UUID id, @RequestBody CreateUserForm form) throws Exception {
        return ResponseEntity.ok(userService.edit(id, form));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTeacher(@PathVariable UUID id) throws Exception {
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/list")
//    public ResponseEntity<?> getTeacherList(@RequestParam(required = false, defaultValue = "0") int page,
//                                            @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
//        return ResponseEntity.ok(userService.findAll(page, size));
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<?> getSearchTeacherList(@RequestParam(name = "searching") String keyword,
//                                                  @RequestParam(required = false, defaultValue = "0") int page,
//                                                  @RequestParam(required = false, defaultValue = "20") int size) throws Exception {
//        return ResponseEntity.ok(userService.search(keyword, page, size));
//    }

    @GetMapping("/list")
    public ResponseEntity<?> getTeacherList(@RequestParam(required = false, defaultValue = "0") int pageNumber,
                                            @RequestParam(required = false, defaultValue = "20") int pageSize) throws Exception {
        return ResponseEntity.ok(userService.findAllTeacher(pageNumber, pageSize));
    }

    @GetMapping("/search")
    public ResponseEntity<?> getSearchTeacherList(@RequestParam(name = "searching") String keyword,
                                                  @RequestParam(required = false, defaultValue = "0") int pageNumber,
                                                  @RequestParam(required = false, defaultValue = "20") int pageSize) throws Exception {
        return ResponseEntity.ok(userService.searchTeacher(keyword, pageNumber, pageSize));
    }
}


