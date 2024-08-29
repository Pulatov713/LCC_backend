package ssd.uz.llc2.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LoginResponseDto {
   private String access_token;
   private String refresh_token;
   private Long expireDate;
}
