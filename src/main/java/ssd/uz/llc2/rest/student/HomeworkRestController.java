package ssd.uz.llc2.rest.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ssd.uz.llc2.service.student.HomeworkService;

@RestController
@RequestMapping("/student/homework")
public class HomeworkRestController {
    @Autowired
    private final HomeworkService homeworkService;

    public HomeworkRestController(HomeworkService homeworkService) {
        this.homeworkService = homeworkService;
    }

}
