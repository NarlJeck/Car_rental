package entity.client;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder

public class Client {
    private Long clientId;
    private String fullName;
    private Integer phoneNumber;
    private String email;
    private String residentialAddress;
    private Role role;
    private Passport passport;
    private DriverLicense driverLicense;
    private BankCard bankCard;
    private String password;




}
