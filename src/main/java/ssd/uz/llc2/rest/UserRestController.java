package ssd.uz.llc2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssd.uz.llc2.service.admin.UserService;

@RestController
@RequestMapping("/user")
public class UserRestController {

    @Autowired
    private UserService userService;


    @GetMapping("/get-user-info")
    public ResponseEntity getUserInfo() throws Exception {
        return ResponseEntity.ok(userService.currentUserInfo());
    }




}
