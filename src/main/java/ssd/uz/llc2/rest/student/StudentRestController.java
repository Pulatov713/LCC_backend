//package ssd.uz.llc.rest.student;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//import ssd.uz.llc.service.student.HomeworkService;
//import ssd.uz.llc.service.student.StudentService;
//
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/student")
//public class StudentRestController {
//    @Autowired
//    private StudentService studentService;
//    @Autowired
//    private HomeworkService homeworkService;
//
//
//    @GetMapping("/my-groups")
//    public ResponseEntity getGroupsOfStudent() {
//        return ResponseEntity.ok(studentService.getGroupsOfStudent());
//    }
//
//    @GetMapping("/homework/{groupId}")
//    public ResponseEntity getHomeworks(@PathVariable Long groupId) {
//        return ResponseEntity.ok(studentService.getHomeworksOfStudent(groupId));
//    }
//
//    @PostMapping("/upload-homework/{taskId}")
//    public ResponseEntity saveHomework(@PathVariable UUID taskId,
//                                       @RequestParam("file") MultipartFile file) throws Exception {
//        return ResponseEntity.ok(homeworkService.saveHomework(taskId, null, file));
//    }
//
//    @PutMapping("/re-upload-homework/{taskId}")
//    public ResponseEntity updateHomework(@PathVariable UUID taskId,
//                                         @RequestParam(value = "homeworkId", required = false) UUID homeworkId,
//                                         @RequestParam("file") MultipartFile file) throws Exception {
//        return ResponseEntity.ok(homeworkService.saveHomework(taskId, homeworkId, file));
//    }
//
//    @GetMapping("/get-count")
//    public ResponseEntity getCount() {
//        return ResponseEntity.ok(studentService.getHomeworkCount());
//    }
//
//}
//

package ssd.uz.llc2.rest.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ssd.uz.llc2.service.student.HomeworkService;
import ssd.uz.llc2.service.student.StudentService;

import java.util.UUID;

@RestController
@RequestMapping("/student")
public class StudentRestController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private HomeworkService homeworkService;

    @GetMapping("/my-groups")
    public ResponseEntity<?> getGroupsOfStudent() {
        return ResponseEntity.ok(studentService.getGroupsOfStudent());
    }

    @GetMapping("/homework/{groupId}")
    public ResponseEntity<?> getHomeworks(@PathVariable Long groupId) {
        return ResponseEntity.ok(studentService.getHomeworksOfStudent(groupId));
    }

    @PostMapping("/upload-homework/{taskId}")
    public ResponseEntity<?> saveHomework(@PathVariable UUID taskId,
                                          @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(homeworkService.saveHomework(taskId, null, file));
    }

    @PutMapping("/re-upload-homework/{taskId}")
    public ResponseEntity<?> updateHomework(@PathVariable UUID taskId,
                                            @RequestParam(value = "homeworkId", required = false) UUID homeworkId,
                                            @RequestParam("file") MultipartFile file) throws Exception {
        return ResponseEntity.ok(homeworkService.saveHomework(taskId, homeworkId, file));
    }

    @GetMapping("/get-count")
    public ResponseEntity<?> getCount() {
        return ResponseEntity.ok(studentService.getHomeworkCount());
    }

}

