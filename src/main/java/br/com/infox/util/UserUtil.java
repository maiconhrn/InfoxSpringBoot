package br.com.infox.util;

import br.com.infox.models.Role;
import br.com.infox.models.User;
import br.com.infox.webservice.dto.RoleDTO;
import br.com.infox.webservice.dto.UserDTO;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Maicon
 */
public class UserUtil {

    public static User createUsuario() {
        return new User();
    }

    public static UserDTO createUsuarioDTO() {
        return new UserDTO();
    }

    private static List<RoleDTO> toDto(List<Role> roles) {
        List<RoleDTO> rolesDTO = new ArrayList<>();

        roles.forEach(role -> {
            rolesDTO.add(RoleUtil.toDto(role));
        });

        return rolesDTO;
    }

    private static List<Role> fill(List<RoleDTO> rolesDTO) {
        List<Role> roles = new ArrayList<>();

        rolesDTO.forEach(roleDTO -> {
            roles.add(RoleUtil.fill(roleDTO));
        });

        return roles;
    }

    public static UserDTO toDto(User user) {
        UserDTO userDTO = new UserDTO();

        userDTO.setCpf(user.getCpf());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setBirthDate(user.getBirthDate());

        return userDTO;
    }

    public static User fill(UserDTO userDTO) {
        User user = new User();

        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());

        return user;
    }
}