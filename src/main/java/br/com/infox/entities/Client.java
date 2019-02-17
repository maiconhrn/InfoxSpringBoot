package br.com.infox.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "client")
public class Client extends Person implements Serializable {

    @Column(length = 25, nullable = false)
    private String phone;
    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(nullable = false, name = "endereco_id")
    private Address address;
    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    private List<ServiceOrder> serviceOrders;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Client client = (Client) o;
        return Objects.equals(phone, client.phone) &&
                Objects.equals(address, client.address) &&
                Objects.equals(serviceOrders, client.serviceOrders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), phone, address, serviceOrders);
    }
}