package br.com.infox.model.builder;

import br.com.infox.model.entity.Role;
import br.com.infox.model.entity.ServiceOrder;
import br.com.infox.model.entity.User;

import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public final class UserBuilder {
    private String cpf;
    private Long id;
    private String firstName;
    private String username;
    private String hashPassword;
    private String lastName;
    private String email;
    private Date birthDate;
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

    public UserBuilder withUsername(String username) {
        this.username = username;
        return this;
    }


    public UserBuilder withHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
        return this;
    }

    public UserBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public UserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public UserBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
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
        user.setUsername(username);
        user.setHashPassword(hashPassword);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setBirthDate(birthDate);
        user.setServiceOrders(serviceOrders);
        user.setRoles(roles);
        return user;
    }
}
