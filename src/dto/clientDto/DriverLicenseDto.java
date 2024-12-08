package dto.clientDto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DriverLicenseDto {

    private Long DriverLicenseId;
    private String serialNumber;
    private LocalDateTime expiredData;
}
