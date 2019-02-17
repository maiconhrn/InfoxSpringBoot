package br.com.infox.webservice.model.dto;

import java.util.Date;

/**
 * @author Maicon
 */
public class PersonDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    private Date birtyDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getBirtyDate() {
        return birtyDate;
    }

    public void setBirtyDate(Date birtyDate) {
        this.birtyDate = birtyDate;
    }
}
