package ssd.uz.llc2.rest.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssd.uz.llc2.dto.form.user.CreateUserForm;
import ssd.uz.llc2.service.admin.UserService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
public class AdminRestController {

    @Autowired
    UserService userService;

    @PutMapping("/edit/{id}")
    public ResponseEntity edit(@PathVariable UUID id, @RequestBody CreateUserForm form) throws Exception {
        return ResponseEntity.ok(userService.edit(id, form));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable UUID id) throws Exception {
        return ResponseEntity.ok(userService.delete(id));
    }

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody CreateUserForm form) throws Exception {
        return ResponseEntity.ok(userService.save(form));
    }

    @GetMapping("/list/{roleId}")
    public ResponseEntity findAllByRoleId(@PathVariable("roleId") Long roleId,
                                          @RequestParam("activity") String activity,
                                          @RequestParam(required = false, defaultValue = "0") int page,
                                          @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
        return ResponseEntity.ok(userService.findAllByRoleId(roleId, activity, page, size));
    }

    @GetMapping("/teacher-id-and-username")
    public ResponseEntity findTeachersForSelect() {
        return ResponseEntity.ok(userService.findTeachersForSelect());
    }

//    @GetMapping("/teacher-id-and-username")
//    public ResponseEntity<List<String>> findTeachersForSelect() {
//        List<String> teacherUsernames = userService.findTeachersForSelect()
//                .stream()
//                .filter(user -> user.getRoleId() == 2)
//                .map(User::getUsername)
//                .collect(Collectors.toList());
//        return ResponseEntity.ok(teacherUsernames);
//    }



    @GetMapping("/search/{roleId}")
    public ResponseEntity search(@PathVariable("roleId") Long roleId,
                                 @RequestParam("searching") String searching,
                                 @RequestParam("activity") String isActive,
                                 @RequestParam(required = false, defaultValue = "0") int page,
                                 @RequestParam(required = false, defaultValue = "10") int size) throws Exception {
        return ResponseEntity.ok(userService.search(roleId,isActive, searching, page, size));
    }
}
