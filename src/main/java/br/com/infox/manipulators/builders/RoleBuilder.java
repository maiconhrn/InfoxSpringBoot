package br.com.infox.manipulators.builders;

import br.com.infox.entities.Role;
import br.com.infox.entities.User;

import java.util.List;

/**
 * @author Maicon
 */
public final class RoleBuilder {
    private Long id;
    private String name;
    private String description;
    private List<User> users;

    private RoleBuilder() {
    }

    public static RoleBuilder aRole() {
        return new RoleBuilder();
    }

    public RoleBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public RoleBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public RoleBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public RoleBuilder withUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Role build() {
        Role role = new Role();
        role.setId(id);
        role.setName(name);
        role.setDescription(description);
        role.setUsers(users);
        return role;
    }
}
