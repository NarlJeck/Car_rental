package service.impl;

import dao.clientDao.RoleDao;
import dao.impl.clientImpl.RoleDaoImpl;
import dto.clientDto.RoleDto;
import entity.client.Role;
import mapper.RoleMapper;
import mapper.impl.RoleMapperImpl;
import service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

public class RoleServiceImpl implements RoleService {

    private static RoleServiceImpl INSTANCE;

    private RoleServiceImpl() {
    }

    private final RoleDao roleDao = RoleDaoImpl.getInstance();
    private final RoleMapper roleMapper = RoleMapperImpl.getInstance();

    public static synchronized RoleServiceImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleServiceImpl();
        }
        return INSTANCE;
    }

    @Override
    public RoleDto findById(Long id) {
        return roleDao.findById(id)
                .map(roleMapper::toDto)
                .orElseThrow(() -> new RuntimeException("Cam not find role by id"));
    }

    @Override
    public List<RoleDto> findAll() {

        return roleDao.findAll()
                .stream()
                .map(roleMapper::toDto)
                .collect(toList());
    }

    @Override
    public void update(RoleDto roleDto) {

    }

    @Override
    public boolean delete(RoleDto roleDto) {
        return false;
    }

    @Override
    public RoleDto create(RoleDto roleDto) {
        Role role = roleDao.create(roleMapper.toEntity(roleDto));
        return roleMapper.toDto(role);
    }
}
