package dao.clientDao;

import dao.BaseCrudDao;
import entity.client.Client;

import java.util.Optional;

public interface ClientDao extends BaseCrudDao<Long, Client> {
    Client createRegistration(Client entity);
    Long findByNameRole(String nameRole);
       Optional<Client> findByEmailAndPassword(String email, String password);

}
