package ssd.uz.llc2.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ssd.uz.llc2.dto.form.LoginForm;
import ssd.uz.llc2.service.admin.UserService;

@RestController
@RequestMapping("/auth")

@CrossOrigin(origins = "http://localhost:8080/login")//http://example.com
public class AuthRestController {
   @Autowired
    private UserService userService;

   @PostMapping("/signin")
   public ResponseEntity signin(@RequestBody LoginForm form) throws Exception{
       return ResponseEntity.ok(userService.signin(form));
   }

//    @PostMapping("/test")
//    public ResponseEntity test(@RequestParam MultipartFile file){
//      return ResponseEntity.ok(fileService.save(file));
//    }
}