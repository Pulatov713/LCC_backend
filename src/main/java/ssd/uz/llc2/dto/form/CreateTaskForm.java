package ssd.uz.llc2.dto.form;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTaskForm {
    private String name;
    private Date deadline;
    private BigDecimal maxBall;
    private Long groupId;
}
