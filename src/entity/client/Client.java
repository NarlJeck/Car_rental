package entity.client;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class Client {
    private Long clientId;
    private String fullName;
    private Integer phoneNumber;
    private String email;
    private String residentialAddress;
    private Long bankCardId;
    private Long driverLicenseId;
    private Long passportId;
    private Long roleId;

}
