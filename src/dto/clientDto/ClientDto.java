package dto.clientDto;

import entity.client.BankCard;
import entity.client.DriverLicense;
import entity.client.Passport;
import entity.client.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ClientDto {
    private Long clientId;
    private String fullName;
    private Integer phoneNumber;
    private String email;
    private String residentialAddress;
    private Role role;
    private Passport passport;
    private DriverLicense driverLicense;
    private BankCard bankCard;
}
