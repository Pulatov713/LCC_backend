package ssd.uz.llc2.service.student.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ssd.uz.llc2.db.domain.User;

import ssd.uz.llc2.db.repository.student.StudentRoleRepository;
import ssd.uz.llc2.dto.ResponseDto;
import ssd.uz.llc2.helper.SecurityHelper;
import ssd.uz.llc2.service.student.StudentService;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRoleRepository studentRoleRepository;


    @Override
    public ResponseDto getGroupsOfStudent() {
        User currentUser = SecurityHelper.getCurrentUser();
        if (currentUser != null &&
                currentUser.getRole().getName().equals("ROLE_STUDENT") &&
                currentUser.isActive()) {
            return new ResponseDto<>(true, "ok", studentRoleRepository.getGroupsOfStudent(currentUser.getId()));
        } else {
            return new ResponseDto<>(false, "Sizga ruxsat berilmagan");
        }

    }

    @Override
    public ResponseDto getHomeworksOfStudent(Long groupId) {
        User currentUser = SecurityHelper.getCurrentUser();
        if (currentUser != null && currentUser.isActive()) {
            return new ResponseDto<>(true, "ok", studentRoleRepository.getHomeworksOfStudent(groupId, currentUser.getId()));
        } else {
            return new ResponseDto<>(false, "Urinma foydasi yo'q!");
        }
    }

    @Override
    public ResponseDto getHomeworkCount() {
        User student = SecurityHelper.getCurrentUser();
        if (student != null) {
            return new ResponseDto<>(true, "Bajarilmagan topshiriqlar soni", studentRoleRepository.getHomeworkCount(student.getId()));
        } else {
            return new ResponseDto<>(false, "Error occurred");
        }
    }


}
