package entity.order;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder

public class StatusOrder {

    private Long statusOrderId;
    private String statusOrder;
}
