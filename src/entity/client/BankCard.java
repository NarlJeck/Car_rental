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
public class BankCard {

    private Long bankCardId;
    private String serialNumber;
    private LocalDateTime expiredData;


}
