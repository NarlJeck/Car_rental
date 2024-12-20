package entity.client;

import lombok.*;

import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Passport {
    private Long passportId;
    private String serialNumber;
    private LocalDateTime expiredData;

}
