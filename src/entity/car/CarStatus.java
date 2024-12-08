package entity.car;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

public class CarStatus {

    private Long statusCarId;
    private String status;

}
