package ssd.uz.llc2.db.domain.customDto.teacher;

import ssd.uz.llc2.db.domain.tasks.costomDto.TaskCustomDtoForTeacher;

import java.util.UUID;

public interface GroupsOfTeacher {
    UUID getId ();
    String getName();
    String getCourseName();
    String getUsername();
    TaskCustomDtoForTeacher getTask();
}
