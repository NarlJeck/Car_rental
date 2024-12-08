package entity.client;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class DriverLicense {

    private Long DriverLicenseId;
    private String serialNumber;
    private LocalDateTime expiredData;

}
