package br.com.infox.util;

import br.com.infox.model.dto.RoleDTO;
import br.com.infox.model.entity.Role;

/**
 * @author Maicon
 */
public class RoleUtil {

    private RoleUtil() {
        throw new IllegalStateException("Utility class");
    }

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
