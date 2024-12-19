package service.impl;

import dao.clientDao.ClientDao;
import dao.impl.clientImpl.ClientDaoImpl;
import dto.clientDto.ClientDto;
import dto.clientDto.CreateRegistrationClientDto;
import entity.client.Client;
import exception.ValidationException;
import mapper.ClientMapper;
import mapper.CreateClientMapper;
import mapper.impl.ClientMapperImpl;
import mapper.impl.CreateClientMapperImpl;
import service.ClientService;
import validator.CreateClientValidator;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

public class ClientServiceImpl implements ClientService {

    private static ClientServiceImpl INSTANCE;

    private final ClientDao clientDao = ClientDaoImpl.getInstance();
    private final CreateClientMapper createClientMapper = CreateClientMapperImpl.getINSTANCE();
    private final ClientMapper clientMapper = ClientMapperImpl.getInstance();
    private final CreateClientValidator createClientValidator = CreateClientValidator.getInstance();

    public static synchronized ClientServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ClientServiceImpl();
        }
        return INSTANCE;
    }

    private ClientServiceImpl() {
    }

    @Override
    public CreateRegistrationClientDto findById(Long id) {
        return clientDao.findById(id)
                .map(createClientMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Can not find client by id"));
    }

    @Override
    public List<CreateRegistrationClientDto> findAll() {

        return clientDao.findAll()
                .stream()
                .map(createClientMapper::toDto)
                .collect(toList());
    }

    @Override
    public void update(CreateRegistrationClientDto clientDto) {
        clientDao.update(createClientMapper.toEntity(clientDto));

    }

    @Override
    public boolean delete(CreateRegistrationClientDto clientDto) {
        return false;
    }

    @Override
    public Long create(CreateRegistrationClientDto clientDto) {
        var validationResult = createClientValidator.isValid(clientDto);
        if(!validationResult.isValid()){
            throw new ValidationException(validationResult.getErrors());
        }
        Client client = createClientMapper.toEntity(clientDto);
        clientDao.createRegistration(client);

        return client.getClientId();

    }

    @Override
    public Optional<ClientDto> login(String email, String password) {
        return clientDao.findByEmailAndPassword(email,password)
                .map(clientMapper::toDto);
    }
}
