package service;

import dto.clientDto.ClientDto;
import dto.clientDto.CreateRegistrationClientDto;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    CreateRegistrationClientDto findById(Long id);

    List<CreateRegistrationClientDto> findAll();

    void update(CreateRegistrationClientDto clientDto);

    boolean delete(CreateRegistrationClientDto clientDto);

    Long create(CreateRegistrationClientDto clientDto);

    Optional<ClientDto> login(String email,String password);
}
