package mapper.impl;

import dao.clientDao.RoleDao;
import dao.impl.clientImpl.RoleDaoImpl;
import dto.clientDto.CreateRegistrationClientDto;
import entity.client.Client;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.CreateClientMapper;
import mapper.RoleMapper;
import service.RoleService;
import service.impl.RoleServiceImpl;
import util.EmailFormatter;

@NoArgsConstructor(access = AccessLevel.PRIVATE)

public class CreateClientMapperImpl implements CreateClientMapper {

    private final Long ROLE_USER_DEFAULT = 1L;

    private static CreateClientMapperImpl INSTANCE;

    private final RoleDao roleDao = RoleDaoImpl.getInstance();
    private final RoleService roleService = RoleServiceImpl.getInstance();
    private final RoleMapper roleMapper = RoleMapperImpl.getInstance();



    public static synchronized CreateClientMapperImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new CreateClientMapperImpl();
        }
        return INSTANCE;
    }

    @Override
    public Client toEntity(CreateRegistrationClientDto dto) {
        return Client
                .builder()
                .fullName(dto.getFullName())
                .phoneNumber(Integer.valueOf(dto.getPhoneNumber()))
                .email((dto.getEmail()))
                .residentialAddress(dto.getResidentialAddress())
                .role(roleMapper.toRole(dto.getRole()))
                .bankCard(null)
                .driverLicense(null)
                .passport(null)
                .password(dto.getPassword())
                .build();
    }

    @Override
    public CreateRegistrationClientDto toDto(Client entity) {
        return CreateRegistrationClientDto
                .builder()
                .fullName(entity.getFullName())
                .phoneNumber(String.valueOf(entity.getPhoneNumber()))
                .email(entity.getEmail())
                .residentialAddress(entity.getResidentialAddress())
                .role(entity.getRole().getRole())
                .bankCard(String.valueOf(entity.getBankCard()))
                .driverLicense(String.valueOf(entity.getDriverLicense()))
                .passport(String.valueOf(entity.getPassport()))
                .password(entity.getPassword())
                .build();
    }
}
