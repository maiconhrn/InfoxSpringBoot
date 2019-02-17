package br.com.infox.manipulators.builders;

import br.com.infox.entities.Role;
import br.com.infox.entities.ServiceOrder;
import br.com.infox.entities.User;

import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public final class UserBuilder {
    private String cpf;
    private Long id;
    private String firstName;
    private String login;
    private String lastName;
    private String password;
    private String email;
    private Date birtyDate;
    private List<ServiceOrder> serviceOrders;
    private List<Role> roles;

    private UserBuilder() {
    }

    public static UserBuilder anUser() {
        return new UserBuilder();
    }

    public UserBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public UserBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public UserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withBirtyDate(Date birtyDate) {
        this.birtyDate = birtyDate;
        return this;
    }

    public UserBuilder withServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
        return this;
    }

    public UserBuilder withRoles(List<Role> roles) {
        this.roles = roles;
        return this;
    }

    public User build() {
        User user = new User();
        user.setCpf(cpf);
        user.setId(id);
        user.setFirstName(firstName);
        user.setLogin(login);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setEmail(email);
        user.setBirtyDate(birtyDate);
        user.setServiceOrders(serviceOrders);
        user.setRoles(roles);
        return user;
    }
}
