package ssd.uz.llc2.db.domain.customDto.teacher;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public interface StudentCustomDtoForTeacher {
    UUID getTaskId();
    Date getDeadline();
    BigDecimal getMaxBall();
    String getTaskName();
    String getTaskFileName();
    Long getTaskFileSize();
    String getTaskFileId();
    String getTaskPathUrl();
    UUID getStudentId();
    String getFirstName();
    String getLastName();
    String getEmail();
    String getPhone();
    UUID getHomeworkId();
    BigDecimal getHomeworkBall();
    String getHomeworkFileId();
    String getDescription();
    String getHomeworkName();
    Long getHomeworkSize();
    String getHomeworkPathUrl();
}
