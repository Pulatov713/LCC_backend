package ssd.uz.llc2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupResponseDto {
private Long id;
private String name;
private String description;
private String courseName;
private String teacherUsername;
private Date createAt;
private Date updateAt;
}
