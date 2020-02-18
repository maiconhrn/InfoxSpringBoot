package br.com.infox.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User extends Person implements Serializable {

    @Column(length = 30, unique = true, nullable = false)
    private String username;
    @Column(nullable = false)
    private String hashPassword;
    @Column(nullable = false, unique = true)
    private String email;
    @OneToMany(mappedBy = "technician", cascade = CascadeType.ALL)
    private List<ServiceOrder> serviceOrders;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", nullable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", nullable = false)
            })
    private List<Role> roles;
    @Column(nullable = false)
    private Boolean retired = false;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    @Override
    public Boolean getRetired() {
        return retired;
    }

    @Override
    public void setRetired(Boolean retired) {
        this.retired = retired;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(email, user.email) &&
                Objects.equals(serviceOrders, user.serviceOrders) &&
                Objects.equals(roles, user.roles) &&
                Objects.equals(retired, user.retired);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, email, serviceOrders, roles, retired);
    }
}