package service;

import dto.clientDto.ClientDto;

import java.util.List;

public interface ClientService {

    ClientDto findById(Long id);

    List<ClientDto> findAll();

    void update(ClientDto clientDto);

    boolean delete(ClientDto clientDto);

    ClientDto create(ClientDto clientDto);
}
