package ssd.uz.llc2.db.domain.tasks.costomDto;

import ssd.uz.llc2.db.domain.attachment.Attachment;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.UUID;

public class TaskCustomDtoForTeacher {
    private UUID id;
    private String name;
    private UUID teacherId;
    private Date deadline;
    private BigDecimal maxBall;
    private Attachment attachment;
}
