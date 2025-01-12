package ssd.uz.llc2.db.domain.customDto.student;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public interface StudentHomeworkDto {
    UUID getTaskId();

    BigDecimal getMaxBall();

    Date getDeadline();

    String getTaskFileName();
    String getTaskFileId();
    String getTaskName();

    Long getTaskFileSize();

    String getTaskPathUrl();

    UUID getHomeworkId();

    BigDecimal getHomeworkBall();

    String getDescription();

    String getHomeworkFileName();

    String getHomeworkPathUrl();
    String getHomeworkFileId();

    Long getHomeworkFileSize();

}
