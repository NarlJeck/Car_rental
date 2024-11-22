package entity.car;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class CarTyp {

    private Long typeCarId;
    private String type;

}
