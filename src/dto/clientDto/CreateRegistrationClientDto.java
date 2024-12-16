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

public class CreateRegistrationClientDto {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String residentialAddress;
    private String role;
    private String passport;
    private String driverLicense;
    private String bankCard;
    private String password;



}
