package mapper.impl;

import dto.clientDto.RoleDto;
import entity.client.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import mapper.RoleMapper;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMapperImpl implements RoleMapper {

    protected static RoleMapperImpl INSTANCE;

    public static synchronized RoleMapperImpl getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RoleMapperImpl();
        }
        return INSTANCE;
    }

    @Override
    public Role toEntity(RoleDto dto) {
        return Role
                .builder()
                .role(dto.getRole())
                .build();
    }

    @Override
    public RoleDto toDto(Role entity) {
        return RoleDto
                .builder()
                .role(entity.getRole())
                .build();
    }


    @Override
    public String toStringRole(RoleDto dto) {
        return dto.getRole();
    }

    @Override
    public Role toRole(String entity) {
        return Role.builder().role(entity).build();
    }
}
