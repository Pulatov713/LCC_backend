package ssd.uz.llc2.dto.teacherDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IdAndUsernameResponseDto {
    private UUID id;
    private String username;
}
