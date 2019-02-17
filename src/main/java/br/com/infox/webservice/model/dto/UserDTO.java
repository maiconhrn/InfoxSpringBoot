package br.com.infox.webservice.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Maicon
 */
public class UserDTO extends PersonDTO {

    private String login;
    @JsonIgnore
    private String password;
    private String email;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
