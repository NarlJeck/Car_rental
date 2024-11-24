package service;

import dto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    void update(ClientDto clientDto);

    void delete(ClientDto clientDto);

    ClientDto create(ClientDto clientDto);
}
