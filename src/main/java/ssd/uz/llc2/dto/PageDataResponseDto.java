package ssd.uz.llc2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class  PageDataResponseDto<T> {
    private T content;
    private Long totalElements;
// private int page;
// private int size;
}

