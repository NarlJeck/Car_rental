package dto.clientDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class BankCardDto {

    private Long bankCardId;
    private String serialNumber;
    private LocalDateTime expiredData;
}
