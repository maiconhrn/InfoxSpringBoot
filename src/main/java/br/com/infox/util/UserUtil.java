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

        if (user != null) {
            userDTO.setCpf(user.getCpf());
            userDTO.setEmail(user.getEmail());
            userDTO.setId(user.getId());
            userDTO.setLogin(user.getUsername());
            userDTO.setPassword(user.getPassword());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setBirthDate(user.getBirthDate());
        }

        return userDTO;
    }

    public static User fill(UserDTO userDTO) {
        User user = new User();

        user.setCpf(userDTO.getCpf());
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());
        user.setHashPassword(userDTO.getPassword());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setBirthDate(userDTO.getBirthDate());

        return user;
    }

    public static User merge(UserDTO userDTO, User user) {
        if (userDTO != null && user != null) {
            user.setCpf(userDTO.getCpf() != null ? userDTO.getCpf() : user.getCpf());
            user.setEmail(userDTO.getEmail() != null ? userDTO.getEmail() : user.getEmail());
            user.setUsername(userDTO.getLogin() != null ? userDTO.getLogin() : user.getUsername());
            user.setPassword(userDTO.getPassword() != null ? userDTO.getPassword() : user.getPassword());
            user.setFirstName(userDTO.getFirstName() != null ? userDTO.getFirstName() : user.getFirstName());
            user.setLastName(userDTO.getLastName() != null ? userDTO.getLastName() : user.getLastName());
            user.setBirthDate(userDTO.getBirthDate() != null ? userDTO.getBirthDate() : user.getBirthDate());
        }

        return user;
    }
}
