package mapper.impl;

import dto.ClientDto;
import entity.client.Client;
import mapper.ClientMapper;

public class ClientMapperImpl implements ClientMapper {
    private static ClientMapperImpl INSTANCE;

    private ClientMapperImpl() {
    }

    public static synchronized ClientMapperImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ClientMapperImpl();
        }
        return INSTANCE;
    }

    @Override
    public Client toEntity(ClientDto dto) {
        return Client
                .builder()
                .clientId(dto.getClientId())
                .fullName(dto.getFullName())
                .phoneNumber(dto.getPhoneNumber())
                .email(dto.getEmail())
                .residentialAddress(dto.getResidentialAddress())
                .role(dto.getRole())
                .passport(dto.getPassport())
                .driverLicense(dto.getDriverLicense())
                .bankCard(dto.getBankCard())
                .build();
    }

    @Override
    public ClientDto toDto(Client entity) {
        return ClientDto
                .builder()
                .clientId(entity.getClientId())
                .fullName(entity.getFullName())
                .phoneNumber(entity.getPhoneNumber())
                .email(entity.getEmail())
                .residentialAddress(entity.getResidentialAddress())
                .role(entity.getRole())
                .passport(entity.getPassport())
                .driverLicense(entity.getDriverLicense())
                .bankCard(entity.getBankCard())
                .build();
    }
}
