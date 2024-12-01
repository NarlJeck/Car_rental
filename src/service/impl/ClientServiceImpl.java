package service.impl;

import dao.clientDao.ClientDao;
import dao.impl.clientImpl.ClientDaoImpl;
import dto.clientDto.ClientDto;
import entity.client.Client;
import mapper.ClientMapper;
import mapper.impl.ClientMapperImpl;
import service.ClientService;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class ClientServiceImpl implements ClientService {

    private static ClientServiceImpl INSTANCE;

    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private final ClientMapper clientMapper = ClientMapperImpl.getINSTANCE();

    public static synchronized ClientServiceImpl getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new ClientServiceImpl();
        }
        return INSTANCE;
    }

    private ClientServiceImpl() {
    }

    @Override
    public ClientDto findById(Long id) {
        return clientDao.findById(id)
                .map(clientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find client by id"));
    }

    @Override
    public List<ClientDto> findAll() {

        return clientDao.findAll()
                .stream()
                .map(clientMapper::toDto)
                .collect(toList());
    }

    @Override
    public void update(ClientDto clientDto) {
        clientDao.update(clientMapper.toEntity(clientDto));

    }

    @Override
    public boolean delete(ClientDto clientDto) {
      return clientDao.delete(clientDto.getClientId());
    }

    @Override
    public ClientDto create(ClientDto clientDto) {
        Client client = clientDao.create(clientMapper.toEntity(clientDto));
        return clientMapper.toDto(client);


    }
}
