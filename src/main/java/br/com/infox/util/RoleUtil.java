package br.com.infox.util;

import br.com.infox.models.Role;
import br.com.infox.api.dto.RoleDTO;

/**
 * @author Maicon
 */
public class RoleUtil {

    public static RoleDTO toDto(Role role) {
        RoleDTO dto = new RoleDTO();

        dto.setName(role.getName());
        dto.setDescription(role.getDescription());
        dto.setId(role.getId());

        return dto;
    }

    public static Role fill(RoleDTO dto) {
        Role role = new Role();

        role.setName(dto.getName());
        role.setDescription(dto.getDescription());
        role.setId(dto.getId());

        return role;
    }
}
