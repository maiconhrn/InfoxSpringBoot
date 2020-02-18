package br.com.infox.api.dto;

import org.springframework.hateoas.core.Relation;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
@Relation(collectionRelation = "users")
public class UserDTO extends @Valid PersonDTO {
    @NotNull
    private String username;
    @NotNull
    @Email
    private String email;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
