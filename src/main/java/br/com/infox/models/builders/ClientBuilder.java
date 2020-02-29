package br.com.infox.models.builders;

import br.com.infox.models.Address;
import br.com.infox.models.Client;
import br.com.infox.models.ServiceOrder;

import java.util.Date;
import java.util.List;

/**
 * @author Maicon
 */
public final class ClientBuilder {
    private String phone;
    private Address address;
    private Long id;
    private String firstName;
    private List<ServiceOrder> serviceOrders;
    private String lastName;
    private String cpf;
    private Date birthDate;

    private ClientBuilder() {
    }

    public static ClientBuilder aClient() {
        return new ClientBuilder();
    }

    public ClientBuilder withPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public ClientBuilder withAddress(Address address) {
        this.address = address;
        return this;
    }

    public ClientBuilder withId(Long id) {
        this.id = id;
        return this;
    }

    public ClientBuilder withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public ClientBuilder withServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
        return this;
    }

    public ClientBuilder withLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public ClientBuilder withCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public ClientBuilder withBirthDate(Date birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public Client build() {
        Client client = new Client();
        client.setPhone(phone);
        client.setAddress(address);
        client.setId(id);
        client.setFirstName(firstName);
        client.setServiceOrders(serviceOrders);
        client.setLastName(lastName);
        client.setCpf(cpf);
        client.setBirthDate(birthDate);
        return client;
    }
}
