package ssd.uz.llc2.service.teacher.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ssd.uz.llc2.db.domain.Group;
import ssd.uz.llc2.db.domain.User;
import ssd.uz.llc2.db.domain.customDto.teacher.GroupCustomForTeacher;
import ssd.uz.llc2.db.domain.customDto.teacher.StudentCustomDtoForTeacher;
import ssd.uz.llc2.db.domain.tasks.Homework;
import ssd.uz.llc2.db.repository.admin.GroupRepository;
import ssd.uz.llc2.db.repository.student.HomeworkRepository;
import ssd.uz.llc2.db.repository.teacher.TeacherRepository;
import ssd.uz.llc2.dto.ResponseDto;
import ssd.uz.llc2.dto.form.EvaluateHomework;
import ssd.uz.llc2.helper.SecurityHelper;
import ssd.uz.llc2.service.teacher.TeacherService;

import java.util.Optional;
import java.util.UUID;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private HomeworkRepository homeworkRepository;

    @Override
    public ResponseDto getGroupsByCourseId(String courseId, int page, int size) throws Exception {
        try {
            User currentUser = SecurityHelper.getCurrentUser();
            if (currentUser.isActive() && currentUser.getRole().getName().equals("ROLE_TEACHER")) {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                Page<GroupCustomForTeacher> optionalGroup;
                if (courseId.equals("all")) {
                    optionalGroup = teacherRepository.getGroup(currentUser.getId(), pageable);
                } else {
                    Long courseID = Long.parseLong(courseId);
                    optionalGroup = teacherRepository.getGroupsByCourseIdAndTeacherId(courseID, currentUser.getId(), pageable);
                }

                return new ResponseDto<>(true, "sss", optionalGroup);
            }
            return new ResponseDto<>(false, "Group ro'yxatini olishga ruxsat yo'q!!!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, e.getMessage());
        }
    }

    @Override
    public ResponseDto getGroups(int page, int size) throws Exception {
        try {

            User currentUser = SecurityHelper.getCurrentUser();
            if (currentUser.isActive() && currentUser.getRole().getName().equals("ROLE_TEACHER")) {
                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                Page<GroupCustomForTeacher> optionalGroup = teacherRepository.getGroup(currentUser.getId(), pageable);

                return new ResponseDto<>(true, "dwdwdwdwdwdwd", optionalGroup);

            }
            return new ResponseDto<>(false, "Group ro'yxatini olishga ruxsat yo'q!!!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, e.getMessage());
        }
    }

    @Override
    public ResponseDto getStudentOfGroup(Long groupId, String taskId, int page, int size) throws Exception {
        try {
            User user = SecurityHelper.getCurrentUser();
            Optional<Group> optionalGroup = groupRepository.findById(groupId);
            if (optionalGroup.isEmpty()) {
                return new ResponseDto<>(false, "bunday group mavjud emas");
            }
            if (user.getId().equals(optionalGroup.get().getTeacher().getId())) {

                Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
                Page<StudentCustomDtoForTeacher> custom;
                UUID taskID;
                if (taskId == null || taskId.equals("null")) {
                    taskID = UUID.fromString("5a864d1f-48a6-4157-bf8d-76ba1b777b5c");
                }else {
                    taskID = UUID.fromString(taskId);
                }
                custom = teacherRepository.getStudentOfGroup(groupId, taskID, pageable);


                return new ResponseDto<>(true, "oededek", custom);

            }
            return new ResponseDto<>(false, "Group studentlarini olishga ruxmat yo'q!!!");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseDto<>(false, e.getMessage());
        }
    }

    @Override
    public ResponseDto evaluateHomework(UUID homeworkId, EvaluateHomework evaluateHomework) {
        try {
            Optional<Homework> optionalHomework = homeworkRepository.findById(homeworkId);
            if (optionalHomework.isPresent()) {
                Homework homework = optionalHomework.get();
                homework.setBall(evaluateHomework.getHomeworkBall());
                homework.setDescription(evaluateHomework.getDescription());
                homework = homeworkRepository.save(homework);
                if (homework.getBall() != null) {
                    return new ResponseDto<>(true, "Homework evaluated successfully");
                }
                throw new Exception("An error occurred while evaluating the homework");
            }
            throw new Exception("Homework doesn't exist");
        } catch (Exception e) {
            return new ResponseDto<>(false, e.getMessage());
        }
    }
}
