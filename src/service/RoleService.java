package service;

import dto.clientDto.RoleDto;
import dto.orderDto.OrderRentalDto;

import java.util.List;

public interface RoleService {
    RoleDto findById(Long id);

    List<RoleDto> findAll();

    void update(RoleDto roleDto);

    boolean delete(RoleDto roleDto);

    RoleDto create(RoleDto roleDto);

}
