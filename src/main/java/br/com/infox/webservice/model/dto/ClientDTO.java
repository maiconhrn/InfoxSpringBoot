package br.com.infox.webservice.model.dto;

/**
 * @author Maicon
 */
public class ClientDTO extends PersonDTO {
    private String phone;
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
