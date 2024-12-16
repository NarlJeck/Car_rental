package mapper;

import dto.clientDto.RoleDto;
import entity.client.Role;

public interface RoleMapper extends BaseMapper<Role, RoleDto> {
     String toStringRole(RoleDto dto);
    Role toRole(String entity);

}
