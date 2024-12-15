package dto.carDto;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class CarModelDto {
    private Long modelCarId;
    private String model;
}
