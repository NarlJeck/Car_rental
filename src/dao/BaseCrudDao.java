package dao;

import java.util.List;
import java.util.Optional;

public interface BaseCrudDao<K, E> {

    boolean delete(K id);

    E create(E entity);

    void update(E entity);

    Optional<E> findById(K id);

    List<E> findAll();
}
