package ssd.uz.llc2.rest.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssd.uz.llc2.service.admin.GroupService;
import ssd.uz.llc2.service.admin.UserService;

@RestController
@RequestMapping("/student/group")
public class StudentGroupRestController {
    @Autowired
    private UserService userService;

    @Autowired
    private GroupService groupService;


    @GetMapping("/students-of-group/{groupId}")
    public ResponseEntity studentsOfGroup(@PathVariable Long groupId,
                                          @RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "6") int size) throws Exception {

        return ResponseEntity.ok(userService.getStudentsOfGroup(groupId, page, size));
    }
}
