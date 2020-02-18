package br.com.infox.api.dto;

import org.springframework.hateoas.core.Relation;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Maicon
 */
@Relation(collectionRelation = "clients")
public class ClientDTO extends @Valid PersonDTO {
    private String phone;
    @NotNull @Valid
    private AddressDTO address;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }
}
